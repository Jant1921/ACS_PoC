import React from 'react';
import { Link } from 'react-router';
export default class pageTwo extends React.Component{
	render(){
		return(
			<div className="page_two__container">
				<h1 > Page 2 </h1>
				<Link to={'/'}>Back to home page</Link>
			</div>
		);
	}
}