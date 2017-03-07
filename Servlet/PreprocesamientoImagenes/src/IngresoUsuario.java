

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IngresoUsuario
 */
@WebServlet("/IngresoUsuario")
public class IngresoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngresoUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 PrintWriter out = response.getWriter();

         out.println("<html>");
         out.println("<head></head>");         
         out.println("<body>");

         out.println("Estos datos se acaban de obtener de un formulario");
         out.println("<br>");
         out.println("Usuario:");
         String usu=request.getParameter("usuario");
         out.println(usu);
         out.println("<br>");
         out.println("Clave:");         
         String cla=request.getParameter("clave");
         out.println(cla);
         out.println("<br>");
         out.println("<a href='http://localhost:8080/PreprocesamientoImagenes/IngresoUsuario.html'>Ingreso Usuario</a>");
         out.println("</body>");
         out.println("</html>");   
	}

}
