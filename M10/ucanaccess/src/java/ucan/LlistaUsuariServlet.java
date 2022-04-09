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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * Aquest servlet controla quin bot� a premut el client:
 * si a demanat "llista" mostra el par�metre "llista" de sessi� amb tots els llibres del resultat de la consulta
 * i tanca la sessi�.
 * Si a sel.leccionat "desconnectar", tamb� tanca la sessi�.
 */

public class LlistaUsuariServlet extends HttpServlet 
{

	protected void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Guardar la llista de llibres dins la sessio:
		HttpSession sessio = request.getSession();

		response.setContentType("text/html;charset=UTF-8");

		try
		{
			PrintWriter out = response.getWriter();

			if (request.getParameter("boto").equals("Llista"))
			{
			    out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet Consulta Llista llibres</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h3>L'usuari: " + sessio.getAttribute("Usuari") + " ha obtingut la seg�ent consulta de llibres:</h3>");
				out.println("\n");
				out.println(sessio.getAttribute("llista"));
				sessio.invalidate();
			}
			else
			{
				sessio.invalidate();
			    out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet Tancar Sessi�</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("Sessi� tancada.");
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
