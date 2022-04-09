package ucan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LlistaCookies extends HttpServlet 
{

	protected void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");

		try
		{
			PrintWriter out = response.getWriter();
			Cookie[] arrCookies = request.getCookies();

			if (request.getParameter("botoL").equals("Llista llibres amb Cookies"))
			{
			    out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet Consulta Llista Cookies</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("Llista de Cookies: \n");
				//out.println("<h3>L'usuari: " + sessio.getAttribute("Usuari") + " ha obtingut la seg�ent consulta de llibres:</h3>");
				for (int i = 0; i < arrCookies.length; i++) {
					 
					if (!arrCookies[i].getName().equals("JSESSIONID"))
						out.write("<h3>" + arrCookies[i].getName() + " : " + arrCookies[i].getValue() + "</h3>" + "\n");
				}
			}
			else
			{
			    out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet Error de bot�</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("Error de bot� incorrecte.");
			}
			out.println("</body>");
			out.println("</html>");
			
		} catch (IOException e)
		{
			//e.printStackTrace();
			RequestDispatcher Mi_ERROR;
            Mi_ERROR = request.getRequestDispatcher("/GestionErroresServlet");
            String Error = e.getMessage();
            request.setAttribute("NEXO_UNION", Error);
            Mi_ERROR.forward(request, response);

		}
	}
	
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
