import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Imagehandler {
	private String dir = "F:\\eclipseworkspace\\workspace\\OpenCV Testing\\";
	private String imgname= "prueba2.tif";
	
	
	
	
	
	public void guardarimg(Mat img, String dir, String imgname){
		Imgcodecs.imwrite(dir+imgname, img);
	}
	
	public void guardarimg(Mat img){
		Imgcodecs.imwrite(dir+imgname, img);
	}

	public Mat cargarimg(String dir,String imgname){
		Mat img = Imgcodecs.imread(dir+imgname);
		return img;
	}
	
	public Mat cargarimg(){
		Mat img = Imgcodecs.imread(dir+imgname);
		return img;
	}
	
	public Mat clahe(Mat img){
		Mat resultado= new Mat();
		Imgproc.createCLAHE().apply(img, resultado);
		return resultado;
	}
	
	/**
	 * Retorna una matriz con la imagen transformada a escala de grises
	 * 
	 * @param  img  corresponde a la matriz de la imagen  
	 * @return      la matriz de la imagen en formato de escala de grises
	 */
	public Mat imgtograyscale(Mat img){
		Mat resultado= new Mat();
		Imgproc.cvtColor(img, resultado, Imgproc.COLOR_RGB2GRAY);
		return resultado;
		
	}
	
	
	
	public String getDir() {
		return dir;
	}


	public void setDir(String dir) {
		this.dir = dir;
	}


	public String getImgname() {
		return imgname;
	}


	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	
}
