/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import basedatos.BaseDatos;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "GestionLibrosServlet", urlPatterns = {"/GestionLibrosServlet"})
public class GestionLibrosServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");  	
	String boton = request.getParameter("submit");        	       
	BaseDatos bd = new BaseDatos();	 	 
        if (boton.equals("Enviar Consulta")){
	    String usuario = request.getParameter("usuario");        	       
	}else if (boton.equals("Insertar Libro")){
	    String id = request.getParameter("id");        	       
	    String titulo = request.getParameter("titulo");        	       
	    String autor = request.getParameter("autor");        	       
	    String editorial = request.getParameter("editorial");        	       
	    String fecha = request.getParameter("fecha");        	       
	    String categoria = request.getParameter("categoria");        	       
	    String novedad = request.getParameter("novedad"); 
	    bd.insertarLibro(id, titulo, autor, editorial , fecha, categoria, novedad);    
	
	}else if (boton.equals("Eliminar Libros")){
	    String[] ids = request.getParameterValues("llista");  
	    for(String id:ids){
		bd.eliminarLibro(id);
	    }
	}else if (boton.equals("Recuperar Libro")){
	    String[] ids = request.getParameterValues("llista2");  
	    
	    String libro=bd.recuperarLibro(ids[0]);
	    request.setAttribute("libro",libro );
	    request.setAttribute("FlagModificar", 1);  	    
	}else if(boton.equals("Actualiza Libro")){
	    String id = request.getParameter("id");        	       
	    String titulo = request.getParameter("titulo");        	       
	    String autor = request.getParameter("autor");        	       
	    String editorial = request.getParameter("editorial");        	       
	    String fecha = request.getParameter("fecha");        	       
	    String categoria = request.getParameter("categoria");        	       
	    String novedad = request.getParameter("novedad"); 
	    bd.modificarLibro(id, titulo, autor, editorial , fecha, categoria, novedad);    
	}

	ArrayList<String> lista = bd.consultaLibros();	
	request.setAttribute("lista", lista);   
	getServletContext().getRequestDispatcher("/MantenimientoLibros.jsp").forward(request,response);
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

