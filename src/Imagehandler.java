import org.opencv.core.Mat;
import org.opencv.core.Scalar;
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
		Mat img = Imgcodecs.imread(dir+imgname,0);
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
	 * calcula el  mean squared error
	 * @param img  Mat de la imagen original
	 * @param ruido Mat de la imagen con ruido
	 * @return el mse calculado en base a las 2 imagenes
	 */
	public double getmse(Mat img, Mat ruido){
		double mse;
		int sum=0;
		double[] val1=new double[1];
		double[] val2=new double[1];
		int w = img.width();
		int h = img.height();
		
		for (int i=0; i<h; i++){
			for (int j=0; j<w;j++){
				 val1[0]= img.get(i,j)[0];
				 val2[0]= ruido.get(i,j)[0];
				 double error= val1[0]-val2[0];
				 sum += (error*error);
				
			}
			
		}
		mse= sum/(h*w);
		return mse;
	
	}
	
	/**
	 * calcula el peak signal to noise ratio
	 * @param mse es el mean quared error
	 * @return un double con el valor del psnr
	 */
	public double getpsnr(double mse){
	int max=255;
	double psnr=(20* Math.log10(max)) - (10* Math.log10(mse));
	
	return psnr;
	};
	
	
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
	
	/**
	 * 
	 * @param img
	 * @param ksizer
	 * @param ksizec
	 * @param desv
	 * @return
	 */
	public Mat filtrogaus(Mat img,int ksizer,int ksizec,double desv){
		Mat result=img;
		double[] val1=new double[1];
		int alfa=0;
		int w;
		int h;
		int valor;
		double valormat;
		double[][] matriz=new double[1][1];
		
		w = img.width();
		h = img.height();
		
		for(int i=0; i<h;i++){
			for(int j=0; j<w;j++){
				
				valormat=img.get(i, j)[0];
				result=gaussauxiliar(i,j,ksizer,ksizec,desv,valormat,result);
		
			}
		}
		 
		/*
		System.out.println("antes de alfa");
		
		//se llama a la funcion para obtener el valor de alfa
			
		
		w = result.width();
		h = result.height();
		System.out.println("entra a alfa");
		alfa=getalfa(result,h,w);	
		//se multiplica la matriz por alfa
		for (int i=0; i<h; i++){
			for (int j=0; j<w;j++){
				 val1[0]= result.get(i,j)[0];	 
				 double[] data= img.get(i, j);
				 data[0]=  val1[0]*(1/alfa);
				 result.put(i, j, data);
				 System.out.println("  "+ data[0]+"  "+val1[0]+" "+alfa);
				 
			}
			
			
		}
		*/
		 System.out.println(result.get(1,1)[0]);
		return result;
		
	}
	
	public Mat gaussauxiliar(int x, int y, int ksizer,int ksizec,double desv,double valormat,Mat img){
		
		int umin=(x+1)-((ksizer-1)/2);
		int umax=(x+1)+((ksizer-1)/2);		
		int vmax=(y+1)+((ksizec-1)/2);
		int vmin=(y+1)-((ksizec-1)/2);
		double[] val1=new double[1];
		Mat resultado=img;
		double suma=0;
		double alfa=0;
		
		for(int i=umin; i<umax;i++){
			for(int j=vmin; j<vmax;j++){
				
				if(x-i<0 || y-j<0 || i<0 || j<0){
					suma+=0;
				}
				else{
					double kernelval=getkernel(x-i,y-j,desv);
					alfa+= kernelval;
					val1[0]= img.get(i,j)[0];
					suma+=kernelval*val1[0];
				}
				
				
			}
		}
		
		double[] data= img.get(x, y);
		
		data[0]= suma*(1/alfa);
		
		
		resultado.put(x,y,data);
		
		return resultado;
	}
	
	
	public double getkernel(int x,int y,double desv){
		double val=Math.exp(-1* (((x*x)+(y*y)) / (2*(desv*desv))) );
		return val;
	}
	
	public int getalfa(Mat matriz,int h, int w){
		int alfa=0;
		double[] val1= new double[1];
		for (int i=0; i<h; i++){
			for (int j=0; j<w;j++){
				val1[0]= matriz.get(i,j)[0]; 
				alfa+=val1[0];
				 	
			}
			
		}
		return alfa;
		
	}	
	
	
	
	
	
	
	
}
