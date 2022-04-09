/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConsultaLibrosServlet extends HttpServlet {     
    public String ruta_libros;    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ruta_libros = getInitParameter("path");
    }    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String titulo = request.getParameter("titulo");    
        String usuario = request.getParameter("usuario");
        File f = new File(ruta_libros);
        BufferedReader entrada = new BufferedReader(new FileReader(f));        
        
        StringBuffer sb = new StringBuffer();
	ArrayList<String> lista = new ArrayList<String>();
                        
        while (entrada.ready()){
            String linea=entrada.readLine();
            boolean presencia = linea.contains(titulo);
            if (presencia){ 
                lista.add(linea);
		sb.append(linea+"\r\n");        
            }
        } 
	HttpSession sesion = request.getSession();
	sesion.setAttribute("librosSel",lista.size());
	
        ///////Para el procesado del post-filtro/////////////
        request.setAttribute("consulta", sb.toString()); 
        request.setAttribute("usuario", usuario);
        request.setAttribute("titulo", titulo);
	
	request.setAttribute("lista", lista);   
	getServletContext().getRequestDispatcher("/ListaLibros.jsp").forward(request,response);
    }

    
        
    /*  RequestDispatcher Mi_ERROR;
        Mi_ERROR = request.getRequestDispatcher("/GestionErroresServlet");
        String Error = e.getMessage();
        request.setAttribute("NEXO_UNION", Error);
        Mi_ERROR.forward(request, response);
        */
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
