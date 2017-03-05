import React from 'react';
//import Router from 'react-router/lib/Router';
//import browserHistory from 'react-router/lib/browserHistory';
//import routes from './routes'; // importing the routes we just created in /src/routes.js!
import {Router, Route, IndexRoute, browserHistory } from 'react-router';

import App from './components/App';
import Home from './components/Home';
import pageTwo from './components/pageTwo';

/**
 * Router takes in the browserHitsory and all the routes we 
 * created in './routes/'. This is the highest level component
 * of our application. 
 * learn more: https://github.com/ReactTraining/react-router/blob/master/docs/API.md#router
 */
const Routes = () => (
	<Router history={browserHistory} >
		<Route path="/" component={App}>
			<IndexRoute component={Home}/>
		    <Route path="page_two" component={pageTwo}/>
	  	</Route>
	</Router>
);

export default Routes;