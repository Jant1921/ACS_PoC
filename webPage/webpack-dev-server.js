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
const storage = multer.diskStorage({
	destination: function(req, file, cb){
		cb(null,'imgs')
	},
	filename: function(req, file, cb){
		cb(null, Date.now() + file.originalname);
	}
});

const upload = multer({storage:storage});

app.get('/',function(req, res, next){
	res.render('index',{title: 'Express'});
});

app.post('/',upload.any(),function(req,res,next){
	console.log(req.files);
	res.send(req.files);
});
/*
app.post('/', function (req, res) {
      res.sendFile(path.join(__dirname +'/jose.html'));
});*/

//app.use('/*', proxy(url.parse('http://localhost:3000/assets')));

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