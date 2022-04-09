/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import basedatos.BaseDatos;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GestorBibliotecaServlet extends HttpServlet {    
    private boolean YaIniciado=false;    
    public String ruta_libros;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = getServletContext();
        ruta_libros = context.getInitParameter("pathcontext");
        System.out.println(ruta_libros);
    }

     protected void ejecutar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {          
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");        
        
        /////Paso usuario y password por sesion////////////
        HttpSession sesion = request.getSession();
	sesion.setAttribute("Usuario", usuario);
	sesion.setAttribute("Password", password);
	////////////////////////////////////////////////////	
        
        /////Paso usuario y password por cookies////////////
        /*Cookie ckUsuario   = new Cookie("ckUsuario", usuario);
        Cookie ckPassword = new Cookie("ckPassword", password);

        ckUsuario.setMaxAge(3600);
        ckPassword.setMaxAge(3600);

        response.addCookie(ckUsuario);
        response.addCookie(ckPassword);*/
        /////////////////////////////////////////////////////  
        
	BaseDatos bd = new BaseDatos();
	
        if (bd.compruebaUsuario(usuario,password)){	    
            boolean temp=YaIniciado;
            if (YaIniciado==false)  YaIniciado=true;
            response.sendRedirect("bienvenido.jsp?usuario="+usuario+"&iniciado="+temp);                  
        }else{
            String msg = "Usuario y/o password son erroneos";
            request.setAttribute("usuario", usuario);
            request.setAttribute("password", password);
            request.setAttribute("msg_error", msg);
            request.getRequestDispatcher("/GestionErroresServlet").forward(request, response);     
        } 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ejecutar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ejecutar(request, response);
    }

    
      
        
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
