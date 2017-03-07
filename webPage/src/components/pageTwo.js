import React from 'react';
import { Link } from 'react-router';
export default class pageTwo extends React.Component{
	
	/**
	 * Retrieved from 
	 * http://www.netlobo.com/url_query_string_javascript.html
	 */
	gup( name ){
	  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	  let regexS = "[\\?&]"+name+"=([^&#]*)";
	  let regex = new RegExp( regexS );
	  let results = regex.exec( window.location.href );
	  if( results == null )
	    return null;
	  else
	    return results[1];
	}

	getImages(){
		const images = JSON.parse("["+decodeURI(this.gup('images'))+ "]");
		console.log(images);
		let imgTags = images.map((route,key)=>{
			return (
				<div key={key}>
					<div>
						<img className="uploaded_image"src={route} />
						<img className="uploaded_image"src={route} />
					</div>
					<div>
						<p> Timestamp: {Date.now()}</p>
						<p> Info 2</p>
						<p> Info 3</p>
					</div>
				</div>
			)
		});
		return imgTags;
	}

	render(){
		return(
			<div className="page_two__container">
				<h1 > Imágenes cargadas </h1>
				<h2>Id de la prueba: {this.gup('token')}</h2>
				
				<div className="images_container">
					{this.getImages()}
				</div>
				
				<br />

				<br />

				<br />

				<Link to={'/'}>Regresar a la página principal</Link>
				
			</div>
		);
	}
}
