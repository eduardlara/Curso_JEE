package ucan;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GestionErroresServlet
 */
@WebServlet("/GestionErroresServlet")
public class GestionErroresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionErroresServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter html = response.getWriter();
        html.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        html.println("<html>");
        html.println("<head>");
        html.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        html.println("<title>ERROR</title>");
        html.println("</head>");
        html.println("<body>");
        html.println("<p>");
        html.println("SERVLET DE ERRORES <BR>");
//        html.println("MISSATGE INVENTAT PER EL SERVLET");
        html.println(request.getAttribute("NEXO_UNION"));
        html.println("</p>");
        html.println("</body>");
        html.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
	*/
	
	/* Al definir els m�todes doGet i doPost cridant a un altre m�tode, per exemple executar,
	 * s'aconsegueix que el html o jsp del Client podra cridar a aquest Servlet tant amb un m�tode GET com amb un POST
	 * ja que ambdos casos, qualsevol dels dos m�todes, executaran el m�tode "executar".
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		executar(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		executar(request, response);
	}

}
