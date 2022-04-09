/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucan;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eduardo
 */
public class servlet1 extends HttpServlet {        	
	private String Ruta_UsuCon = "";
	private boolean JaIniciat = false;
	
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
	
	protected void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String Usuari = request.getParameter("Usuari");
		String Password = request.getParameter("Password");

		/*
		 * Crear una Sesi� per guardar informaci�
		 */
		HttpSession sessio = request.getSession();
		/*
		 * Guardar com a "usucon" la ruta i fitxer on s'enregistrara m�s endavant el numero
		 * d'usuaris connectats:
		 */
		sessio.setAttribute("usucon", Ruta_UsuCon);
		/*
		 * Guardar com a "Usuari" el nom d'usuari que s'ha rebut des del navegador amb el client Inici_Sessio.html:
		 */
		sessio.setAttribute("Usuari", request.getParameter("Usuari"));
		
		if (Usuari.toLowerCase().equals("ed") && Password.toLowerCase().equals("ed")) 
		{
			/*
			 * Si s'ha informat l'Usuari i Password correctes, es mostra el jsp Client_Sessio.jsp:
			 */
			response.sendRedirect("Client_Sessio.jsp?usuari="+Usuari+"&bd="+JaIniciat);
			if (JaIniciat == false) 
			{
				JaIniciat = true;
			}
		}
		else
		{
//			response.sendRedirect("pagina_error.html");
			response.sendRedirect("pagina_error.jsp?Usuari=" + Usuari);
		}
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		Ruta_UsuCon = context.getInitParameter("Ruta_Usu_Con");
	}	

}
