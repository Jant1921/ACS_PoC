/**
 * Webpack Dev Server
 * This file is used to run our local enviroment in development
 * mode. Production build does not go through dev server.
 */
const webpack = require('webpack');
const WebpackDevServer = require('webpack-dev-server');
const webpackConfig = require('./webpack.config'); // haven't created this yet. No sweat.
const path = require('path');
const express = require('express');
const app = express();
const proxy = require('proxy-middleware');
const url = require('url');
const router = express.Router();
const multer = require('multer');
const fs = require('fs');
const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
const tokenLength = 4;
let testName = 'test001';
let uploadedImagesRoute = [];

/**
 * Generates a random token
 * @param  {integer} pLength - token length
 * @return {string} generatedToken - a new random token
 */
function generateRandomToken(pLength){
    let generatedToken = '';
    let selectedChar = '';
    for(let i=0;i<pLength;i++){
        selectedChar = Math.floor(Math.random() * (chars.length));
        generatedToken += chars.charAt(selectedChar);
    }
    return generatedToken;
};

testName = generateRandomToken(tokenLength);

const storage = multer.diskStorage({
	destination: function(req, file, cb){
		const dir = '../uploaded_images/'+testName;
		if (!fs.existsSync(dir)){
			fs.mkdirSync(dir);
		}
		cb(null,dir)
	},
	filename: function(req, file, cb){
		cb(null, testName +'_'+ file.originalname);
	}
});

const upload = multer({storage:storage});

app.post('/',upload.any(),function(req,res,next){
	//console.log(req);
	//res.send('finalizado');
	//testName = req.body.myName;
	//console.log(req.files);
	
	uploadedImagesRoute = req.files.map(function(image){
		return '"../../'+image.path+'"';
	});
	console.log(uploadedImagesRoute);
	const lastUsedToken = testName;
	testName = generateRandomToken(tokenLength); 
	//res.send(lastUsedToken);
	res.redirect('http://localhost:3000/page_two?token='+lastUsedToken
		+'&images='+encodeURI( uploadedImagesRoute )
	);
},upload.any());


app.get('/img_routes',function(req, res){
	res.setHeader('Content-Type', 'application/json');
    res.send(JSON.stringify({ a: 1 }));
    /*
    res.setHeader('Content-Type', 'application/json');
    res.json({ a: 1 });
    */
});

// always dev enviroment when running webpack dev server
const env = { dev: process.env.NODE_ENV };

const devServerConfig = {
  contentBase: path.join(__dirname, '../src/'),
  // Need historyApiFallback to be able to refresh on dynamic route
  historyApiFallback: { disableDotRule: true },
};

/**
 * Creating the server to listen to. We are passing in our webpack config
 * that we will setup at webpack/webpack.config.js. We are also passing in
 * the server configuration object that we created above.
 */
const server = new WebpackDevServer(webpack(webpackConfig(env)), devServerConfig);

// will be live at http://localhost:3000/
server.listen(3000, '0.0.0.0');
app.listen(3001);