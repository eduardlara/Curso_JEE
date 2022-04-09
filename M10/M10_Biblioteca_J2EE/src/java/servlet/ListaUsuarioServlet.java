/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eduardo
 */

public class ListaUsuarioServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ListaUsuarioServlet</title>");            
        out.println("</head>");
        out.println("<body>");
        HttpSession sesion = request.getSession();
        
        if (request.getParameter("boton").equals("Selecciona")){
            String []lista = request.getParameterValues("lista");
            sesion.setAttribute("lista",lista);
	    sesion.setAttribute("librosSel", lista.length);
            //String usuario = sesion.getAttribute("Usuario").toString();
            //String password = sesion.getAttribute("Password").toString();
            //out.println("<h2>Usuario: "+usuario+"  Password: "+password+"</h2>");
            //for(String item:lista){                
            //    out.println("<h3>"+item+"</h3>");
            //}
            response.sendRedirect("seleccionado.jsp");
        }else{
            String usuario = sesion.getAttribute("Usuario").toString();
            String password = sesion.getAttribute("Password").toString();
            sesion.invalidate();
            out.println("<h2>Usuario: "+usuario+"  Password: "+password+"</h2>");
            out.println("Session cerrada");
            //usuario = sesion.getAttribute("Usuario").toString();
            //password = sesion.getAttribute("Password").toString();
        }
        out.println("</body>");
        out.println("</html>");
    }

    
    /* 
    
            //request.getRequestDispatcher("seleccionado.jsp").forward(request, response);
            
    

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
