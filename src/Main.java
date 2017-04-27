import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.*;
import org.opencv.core.CvType;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args){
		
		//se carga la libreria para evitar errores 
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
				
		//instancia la clase de manejo de imagenes
		Imagehandler imgh= new Imagehandler();
		//instancias de variables base a utilizar
		String direc="";
		String imgname="";
		Mat img= new Mat();
		Mat prueba= new Mat();
		
		//define el directorio donde guardara las imagenes	
		direc="F:\\eclipseworkspace\\workspace\\OpenCV Testing\\";
		imgname="01.png";
		
		
		//carga matriz de imagen en img
		img = imgh.cargarimg(direc, imgname);
	
	 
		 //escribe la imagen recien leida
		 imgh.setDir(direc);
		 imgh.setImgname("imagtest1.tif");
		 imgh.guardarimg(img);
		 
		 
		
		
		 //escribre la imagen en escala de grises
		//
		 Imgproc.cvtColor(img, prueba, Imgproc.COLOR_RGB2GRAY);
		 
		 prueba=img.clone();
		 
		 prueba=imgh.imgtograyscale(prueba);
		 imgname= "pruebagrayscale.tif";	 

		 imgh.setDir(direc);
		 imgh.setImgname(imgname);
		 imgh.guardarimg(prueba);

		 
		 
		 //copia el estado actual de variable prueba(matriz en escala de grises)
		 Mat respaldo = prueba.clone();
		 Mat respaldo2 = prueba.clone();		 
		 Mat respaldo3 = prueba.clone();
		 Mat respaldo4 = prueba.clone();
		 Mat respaldo5 = prueba.clone();
		 Mat respaldo6 = prueba.clone();
		 Mat respaldo7 = prueba.clone();

		 System.out.println("antes de gaus"+ prueba.width());
		 //aplica el filtro gaussiano a la imagen
		 Mat gaus=imgh.filtrogaus(respaldo2, 8, 8, 2);
		 imgname= "pruebagauss1.tif";	 
		 imgh.setDir(direc);
		 imgh.setImgname(imgname);
		 imgh.guardarimg(gaus);
		 
		 
		
		 
		 System.out.println("antes de bilateral"+ prueba.width());
		 //aplica el filtro bilateral a la imagen
		 Mat bilateral=imgh.filtrobilateral(respaldo3, 10, 10, 15, 8);
		 imgname= "pruebabilateral1.tif";	 
		 imgh.setDir(direc);
		 imgh.setImgname(imgname);
		 imgh.guardarimg(bilateral);	
			
		 Imgproc.bilateralFilter ( respaldo4, respaldo5, 10, 15, 8 );
		 imgname= "pruebabilateral2.tif";	 
		 imgh.setDir(direc);
		 imgh.setImgname(imgname);
		 imgh.guardarimg(respaldo5);
		 
		
				 
		 System.out.println("despues de gaus");
		 //crea una matriz para apoyo
		 Mat img2= prueba;
		 
		 
		 //utilizacion del algoritmo clahe
		 img2=imgh.clahe(bilateral);
		
		
		 
		 //guardar la imagen modificada por clahe
		 imgname="pruebaclahe2.tif";
		 imgh.setImgname(imgname);
		 imgh.guardarimg(img2);
		 
		 

		
		 //llamado de la funcion para calcular el mse
		 double mse=imgh.getmse(respaldo, img2);
		 
		 System.out.println("mse "+mse);
		 
		 //llamado de la funcion para calcular el psnr
		 double psnr = imgh.getpsnr(mse);
		 
		 System.out.println("psnr "+psnr);
		 
		//llamado de la funcion para calcular el filtro gaussiano
		 
		 
		
	       
		
	}
		
	
}
