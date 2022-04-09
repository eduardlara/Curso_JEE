/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eduardo
 */

@WebServlet(name = "GestionErroresServlet", urlPatterns = {"/GestionErroresServlet"})
public class GestionErroresServlet extends HttpServlet {    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        //response.setContentType("text/plain;charset=UTF-8");
        //response.setContentType("text/html;charset=UTF-8");  
        /*PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Servlet GestionErroresServlet</title></head>");
        out.println("<body>");
        out.println("<h1>SERVLET GESTION DE ERRORES</h1>");
        out.println("<h2>Usuario: "+ request.getAttribute("usuario")+"</h2>");
        out.println("<h2>Password: "+request.getAttribute("password")+"</h2>");
        out.println("<h2>Mensaje: "+request.getAttribute("msg_error")+"</h2>");
        out.println("</body>");
        out.println("</html>");  */
        //response.setHeader("Refresh", "10; URL=msg_error.jsp");
	
	HttpSession sesion = request.getSession();
	sesion.setAttribute("librosSel",0);
	
        String url="5;URL=msg_error.jsp?usuario="+request.getAttribute("usuario");
        url += "&password="+request.getAttribute("password");
        url += "&msg_error="+request.getAttribute("msg_error");
        response.setHeader("Refresh", url);  
        //request.getRequestDispatcher("msg_error.jsp").forward(request, response);
    }
          

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
