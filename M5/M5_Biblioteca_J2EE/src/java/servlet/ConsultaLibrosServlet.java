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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eduardo
 */

public class ConsultaLibrosServlet extends HttpServlet {     
    public String ruta_libros;    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ruta_libros = getInitParameter("path");
        System.out.println(ruta_libros);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String titulo = request.getParameter("titulo");    
        String usuario = request.getParameter("usuario");
        //String path = getServletContext().getRealPath("/");
        //File f = new File(path + "libros.txt");
        File f = new File(ruta_libros);
        BufferedReader entrada = new BufferedReader(new FileReader(f));
        
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Servlet ConsultaLibrosServlet</title></head>");
        out.println("<body>");
        StringBuffer sb = new StringBuffer();
        while (entrada.ready()){
            String linea=entrada.readLine();
            boolean presencia = linea.contains(titulo);
            if (presencia){ 
                out.println("<h3>"+linea+"</h3>");
                sb.append(linea+"\r\n");
            }
        }        
        out.println("</body>");
        out.println("</html>");
        request.setAttribute("consulta", sb.toString()); 
        request.setAttribute("usuario", usuario);
        request.setAttribute("titulo", titulo);
    }

      /*HttpSession session;
        session = request.getSession(true);
        String Usuario = session.getAttribute("Usuario").toString();  
        request.setAttribute("Usuario", Usuario);
        session.setAttribute("Usuario", Usuario);*/
      
        
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
