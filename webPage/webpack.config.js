/*
    ./webpack.config.js
    Code based on 
    https://scotch.io/tutorials/setup-a-react-environment-using-webpack-and-babel
    and
    https://brotzky.co/blog/code-splitting-react-router-webpack-2/
*/

const webpack = require('webpack');
const path = require('path');

const HtmlWebpackPlugin = require('html-webpack-plugin');

/**
* HtmlWebpackPlugin will make sure out JavaScript files are being called
* from within our index.html
*/
const HtmlWebpackPluginConfig = new HtmlWebpackPlugin({
  template: path.join(__dirname, './src/index.html'),
  filename: 'index.html',
  inject: 'body'
});

const CommonsChunkPluginConfig = new webpack.optimize.CommonsChunkPlugin({
  name: 'vendor',
  minChunks: Infinity,
  filename: '[name].[hash].js',
});


module.exports = env =>{

  return{
      // entry tells webpack where to start looking
      entry: {
        app: path.join(__dirname, './src/'),
        vendor: ['react', 'react-dom', 'react-router'],
      },
      /**
         * output tells webpack where to dump the files it has processed.
         * [name].[hash].js will output something like app.3531f6aad069a0e8dc0e.js
         */
      output: {
        path: path.join(__dirname, './dist/'),
        filename: '[name].[hash].js',
      },
      module: {
        loaders: [
          { 
            test: /\.js$/,
            loader: 'babel-loader',
            exclude: /node_modules/ 
          },
          { 
            test: /\.jsx$/,
            loader: 'babel-loader',
            exclude: /node_modules/ 
          },
          { 
            test: /\.scss$/,
            loader: 'style-loader!css-loader!sass-loader'
          }
        ]
      },

      plugins: [CommonsChunkPluginConfig, HtmlWebpackPluginConfig],
  };
};
