import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

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
		imgname="imagtest.tif";
		
		
		//carga matriz de imagen en img
		img = imgh.cargarimg(direc, imgname);
	
	 
		 //escribe la imagen recien leida
		 imgh.setDir(direc);
		 imgh.setImgname("prueba1.tif");
		 imgh.guardarimg(img);
		 
		 
		 //escribre la imagen en escala de grises
		 prueba=imgh.imgtograyscale(img);
		 imgname= "pruebagrayscale.tif";	 
		 imgh.setDir(direc);
		 imgh.setImgname(imgname);
		 imgh.guardarimg(prueba);
		 
		 //copia el estado actual de variable prueba(matriz en escala de grises)
		 Mat respaldo = prueba;
		 

		 
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
	        
	}
	
}
