import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Imagehandler {
	
	private String dir = "F:\\eclipseworkspace\\workspace\\OpenCV Testing\\";
	private String imgname= "prueba2.tif";
	
	
	
	
	/**
	 * guarda la matriz como una imagen
	 * @param img matriz que contiene la informacion de los pixeles de la imagen
	 * @param dir string referente a la direccion del folder
	 * @param imgname string referente al nombre de la imagen
	 */
	public void guardarimg(Mat img, String dir, String imgname){
		Imgcodecs.imwrite(dir+imgname, img);
	}
	
	/**
	 * guarda la matriz como una imagen
	 * @param img matriz que contiene la informacion de los pixeles de la imagen
	 */
	public void guardarimg(Mat img){
		Imgcodecs.imwrite(dir+imgname, img);
	}

		/**
		 * genera la matriz de la imagen ubicada segun el folder y nombre
		 * @param dir string referente a la direccion del folder
		 * @param imgname string referente al nombre de la imagen
		 * @return la matriz referente a la imagen cargada
		 */
	public Mat cargarimg(String dir,String imgname){
		Mat img = Imgcodecs.imread(dir+imgname);
		return img;
	}
	
	/**
	 * genera la matriz de la imagen ubicada segun el folder y nombre
	 * @return la matriz referente a la imagen cargada
	 */
	public Mat cargarimg(){
		Mat img = Imgcodecs.imread(dir+imgname);
		return img;
	}
	
	/**
	 * Aplica Contrast Limited Adaptive Histogram Equalization a la matriz referente a una imagen
	 * @param img matriz de una imagen
	 * @return la matriz de imagen modificada 
	 */
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
	
	
	/**
	 * Retorna el valor de dir (direccion del forlder)
	 * 
	 * @return el string con la direccion del folder
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * setea el parametro de direccion del folder
	 * @param dir string que hace referencia a la ruta del folder
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * retorna el nombre del archivo a crear
	 * @return string con el nombre del archivo a generar
	 */
	public String getImgname() {
		return imgname;
	}

	/**
	 * Setea el valor del nombre de la imagen a crear
	 * @param imgname string para setear el nombre del archivo a crear
	 */
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	
}
