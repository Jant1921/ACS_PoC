/*
    ./client/index.js
    which is the webpack entry file
*/
import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App';
import Routes from './routes'
require('./sass/main.scss');

ReactDOM.render(<Routes />,document.getElementById('root'));