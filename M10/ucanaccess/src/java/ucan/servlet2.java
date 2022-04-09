/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eduardo
 */
public class servlet2 extends HttpServlet {

  	public String Ruta_Llibres = "";
	Connection connexio;
	public String parm_cami = "", parm_driver = "", parm_driver_conn = "";
	public String DBUsuari = "", DBPassword = "";
	
	
	protected 	void executar(HttpServletRequest request,	HttpServletResponse response) 
				throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		try 
		{
			int contador = 0;
			String recerca = (request.getParameter("titol")).toUpperCase();
			ArrayList al = new ArrayList();
			HttpSession sessio = request.getSession();
			
            String driver = "jdbc:ucanaccess:";
            String url = "//C:\\Users\\eduardo\\Desktop\\J2EE Marta\\ucanaccess\\web\\BBDD_Llibres.accdb";
            System.out.println ("driver: "+driver);
	    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            //System.out.println ("url   : "+url);
            connexio = DriverManager.getConnection(driver+url);
            
			/*
			 * Seleccionar les filas de la taula i buscar les que tenen la
			 * busqueda informada a la variable "Titol":
			 */
			
 			String sql = "SELECT * FROM Llibres";
 			Statement stm = connexio.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while (rs.next())
			{
				String Titol = rs.getString(2);
				if (Titol.contains(recerca))
				{
					int ID_Llibre = rs.getInt(1);
					String Autor = rs.getString(3);
					String Editorial = rs.getString(4);
					Date Data = rs.getDate(5);
					String Tema = rs.getString(6);
					// Guardar a l'ArrayList:
					al.add(ID_Llibre + " - " + Titol + " - " +
					Autor + " - " + Editorial + " - " + Data + " - " + Tema);
			        contador++;
				}
			}
			rs.close();
			
			/*
			 * Es pot passar un ArrayList a un JSP com un par�metre normal:
			 */
			request.setAttribute("taula", al);
			request.setAttribute("conta", contador);
			getServletConfig().getServletContext().getRequestDispatcher("/Llista_Llibres.jsp").forward(request, response);
			
		} catch (IOException e)
		{
			RequestDispatcher Mi_ERROR;
            Mi_ERROR = request.getRequestDispatcher("/GestionErroresServlet");
            String Error = e.getMessage();
            request.setAttribute("NEXO_UNION", Error);
            Mi_ERROR.forward(request, response);
		} catch (ClassNotFoundException e) 
		{
			RequestDispatcher Error_DB;
            Error_DB = request.getRequestDispatcher("/GestionErroresServlet");
            String Error = e.getMessage();
            request.setAttribute("NEXO_UNION", Error);
            Error_DB.forward(request, response);
		} catch (SQLException e) 
		{
			RequestDispatcher Error_DB;
            Error_DB = request.getRequestDispatcher("/GestionErroresServlet");
            String Error = e.getMessage();
            request.setAttribute("NEXO_UNION", Error);
            Error_DB.forward(request, response);
		}
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		parm_cami = getInitParameter("cami");
		parm_driver = getInitParameter("driver");
		parm_driver_conn = getInitParameter("driver_conn");
	}
	
	/* Al definir els m�todes doGet i doPost cridant a un altre m�tode, per exemple executar,
	 * s'aconsegueix que el html o jsp del Client podra cridar a aquest Servlet tant amb un m�tode GET com amb un POST
	 * ja que ambdos casos, qualsevol dels dos m�todes, executaran el m�tode "executar".
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		executar(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		executar(request, response);
	}
	
}
