import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class PruebaImagenes {
	
	private String direc="F:\\eclipseworkspace\\workspace\\OpenCV Testing\\";
	
	@Test
	public void probarguardadocarga() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		try{
			Imagehandler imgh= new Imagehandler();
			//define el directorio donde guardara las imagenes	
			
			String imgname="input.tif";		
			Mat img = new Mat(); 
			img=imgh.cargarimg(direc, imgname);
			imgh.setDir(direc);
			imgh.setImgname("testguardado.tif");
			imgh.guardarimg(img);
			Mat img2 = imgh.cargarimg(direc, "testguardado.tif");
			assert( true);
		}catch(Exception e){
			assert(false);
		};
		
	}
	

	
	@Test
	public void pruebamse() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Imagehandler imgh= new Imagehandler();
		
		Mat img2 = Imgcodecs.imread(direc + "pruebagrayscale.tif");
		Mat respaldo = Imgcodecs.imread(direc+"pruebaclahe.tif");
		double mse=imgh.getmse(respaldo, img2);
		int mseint= (int)mse;
		// 1940 es el entero esperado para el mse
		assertEquals(mseint,1940);
		
	}
	
	
	@Test
	public void pruebapsnr() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Imagehandler imgh= new Imagehandler();
		Mat img2 = Imgcodecs.imread(direc+"pruebagrayscale.tif");
		Mat respaldo = Imgcodecs.imread(direc+"pruebaclahe.tif");
		double mse=imgh.getmse(respaldo, img2);
		double psnr = imgh.getpsnr(mse);
		int psnrint= (int)psnr;
		//15 es el entero esperado como respuesta referente al psnr
		assertEquals(psnrint,15);
		
	}
	
	@Test
	public void pruebaescalagrises() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		try{
			Imagehandler imgh= new Imagehandler();
			//define el directorio donde guardara las imagenes	
			
			String imgname="input.tif";		
			Mat img = new Mat(); 
			img=imgh.cargarimg(direc, imgname);
			
			Mat prueba=imgh.imgtograyscale(img);
			
			imgname= "pruebagrayscale.tif";	 
			imgh.setDir(direc);
			imgh.setImgname(imgname);
			imgh.guardarimg(prueba);
		 
		assert( true);
		
		}catch(Exception e){
			assert(false);
		};
		
	}
	
	
	
	
	

}
