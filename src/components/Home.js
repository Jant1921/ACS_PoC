import React from 'react';
import { Link } from 'react-router';

export default class Home extends React.Component{
	
	loadImages(pEvent){
		let files = document.getElementById('files_input').value;
	    if (files) {
	        console.log(files);
	        //console.log("Name: " + file.name + "\n" + "Last Modified Date :" + file.lastModifiedDate);
	    }else{
	    	console.log('no file');
	    }
	}
	
	render(){
		return(
			<div className="home__container">
				<h1> Prepocesamiento de Imágenes </h1>
				<div>
					
					<form action="http://localhost:3001" method="post" encType="multipart/form-data">
					<input type="file" name="myimage1"/><br/>
				<br/>
					<input type="file" name="myimage2"/><br/>
				<br/>
					<input type="submit" name="submit"/>
						
					</form>
				</div>

				<br/>
				
				<Link className="link_to_page_two" to={'/page_two'}>Go to page 2</Link>
			</div>
		);
	}
}