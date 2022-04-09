/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");       
        if (usuario.toLowerCase().equals("eduard") && 
                password.toLowerCase().equals("eduard")){           
            boolean temp=YaIniciado;
            if (YaIniciado==false)  YaIniciado=true;
            response.sendRedirect("bienvenido.jsp?usuario="+usuario+"&iniciado="+temp);                  
        }else{
            //response.sendRedirect("error.jsp?usuario="+usuario);                
            String msg = "Usuario y/o password son erróneos";
            request.setAttribute("usuario", usuario);
            request.setAttribute("password", password);
            request.setAttribute("msg_error", msg);
            request.getRequestDispatcher("/GestionErroresServlet").forward(request, response);     
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");             
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");   
        if (usuario.toLowerCase().equals("eduard") && 
                password.toLowerCase().equals("eduard")){         
            boolean temp=YaIniciado;
            if (YaIniciado==false) YaIniciado=true;            
            response.sendRedirect("bienvenido.jsp?usuario="+usuario+"&iniciado="+temp);
        }else{
            //response.sendRedirect("error.jsp?usuario="+usuario);
            String msg = "Usuario y/o password son erróneos";
            request.setAttribute("usuario", usuario);
            request.setAttribute("password", password);
            request.setAttribute("msg_error", msg);
            request.getRequestDispatcher("/GestionErroresServlet").forward(request, response);     
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
