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
		imgname="imagenprueba.jpg";
		
		
		//carga matriz de imagen en img
		img = imgh.cargarimg(direc, imgname);
	
	 
		 //escribe la imagen recien leida
		 imgh.setDir(direc);
		 imgh.setImgname("prueba1.tif");
		 imgh.guardarimg(img);
		 
		 
		 //test para imagen
		
		/*File input = new File("F:\\eclipseworkspace\\workspace\\OpenCV Testing\\promatte.jpg");
        BufferedImage image:
		try {
			
			image = ImageIO.read(input);
			byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
	        mat.put(0, 0, data);

	        Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
	        Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
	        
	        System.out.println(mat.get(1, 1)[0]+"  "+mat1.get(1, 1)[0]);
	        mat1.s
		
		*/
        
	//	 Mat mat = new Mat(img.height(), img.width(), CvType.CV_8UC3);
		

		
		
		 //escribre la imagen en escala de grises
		//
		 Imgproc.cvtColor(img, prueba, Imgproc.COLOR_RGB2HSV);
		 
		 prueba=img.clone();
		 prueba=imgh.imgtograyscale(prueba);
		 imgname= "pruebagrayscale.tif";	 
	/*	 
		 for(int i=0;i<200;i++){
			 for(int j=0;j<200;j++){
				 double[] data= prueba.get(2, 2);
				 double variable=125.0;
				 data[0]= variable;
				 System.out.println(prueba.channels()+" canales  "+ " "+ data+ " ");
				 prueba.put(i,j,data);
			 }
		 }*/
		 imgh.setDir(direc);
		 imgh.setImgname(imgname);
		 imgh.guardarimg(prueba);

		 
		 
		 //copia el estado actual de variable prueba(matriz en escala de grises)
		 Mat respaldo = prueba.clone();
		 Mat respaldo2=prueba.clone();
		 Mat respaldo3 = respaldo;

		 System.out.println("antes de gaus"+ prueba.width());
		 //aplica el filtro gaussiano a la imagen
		 Mat gaus=imgh.filtrogaus(prueba, 5, 5, 1.1);
		 imgname= "pruebagauss2.tif";	 
		 imgh.setDir(direc);
		 imgh.setImgname(imgname);
		 imgh.guardarimg(gaus);
		 
			
			

				
				 
		 System.out.println("despues de gaus");
		 //crea una matriz para apoyo
		 Mat img2= prueba;
		 
		 
		 //utilizacion del algoritmo clahe
		 img2=imgh.clahe(prueba);
		
		
		 
		 //guardar la imagen modificada por clahe
		 imgname="pruebaclahe.tif";
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
