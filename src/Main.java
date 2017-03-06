import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {
	public static void main(String[] args){
		
		//se carga la libreria para evitar errores 
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
				
		//instancia la clase de manejo de imagenes
		Imagehandler imgh= new Imagehandler();
		//instancias de variables a utilizar
		String direc="";
		String imgname="";
		Mat img= new Mat();
		Mat prueba= new Mat();
		
		
		
		
		//carga la imagen en una matriz		
		direc="F:\\eclipseworkspace\\workspace\\OpenCV Testing\\";
		imgname="imagtest.tif";
		
		//carga matriz de imagen en img
		img = imgh.cargarimg(direc, imgname);
	
	 
		 //escribe la imagen recien leida
		 Imgcodecs.imwrite("F:\\eclipseworkspace\\workspace\\OpenCV Testing\\prueba1.tif", img);
		 
		 
		 
		 //escribre la imagen en escala de grises

		 prueba=imgh.imgtograyscale(img);
		 
		 
		 direc = "F:\\eclipseworkspace\\workspace\\OpenCV Testing\\";
		 imgname= "pruebagrayscale.tif";
		 
		 imgh.setDir(direc);
		 imgh.setImgname(imgname);
		 imgh.guardarimg(prueba);
		 
		 //crea una matriz para apoyo
		 Mat img2= prueba;
		 
		 //algoritmo de ecualizacion de histogramas
		 Imgproc.equalizeHist(prueba, img);
		 Imgcodecs.imwrite("F:\\eclipseworkspace\\workspace\\OpenCV Testing\\prueba3.tif", img);
		 
		 //utilizacion del algoritmo clahe
		 img2=imgh.clahe(prueba);
		 //guardar la imagen modificada por clahe
		 imgname="prueba4.tif";
		 imgh.setImgname(imgname);
		 imgh.guardarimg(img2);
		
	}
	
}
