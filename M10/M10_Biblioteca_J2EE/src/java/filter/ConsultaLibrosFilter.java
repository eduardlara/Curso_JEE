/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ConsultaLibrosFilter implements Filter {    
    private FilterConfig filterConfig = null;
    private String ruta;
    private String ruta_consulta;
    
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        ruta = filterConfig.getInitParameter("Registro");
        ruta_consulta = filterConfig.getInitParameter("Consulta");
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        String consulta = (String)request.getAttribute("consulta").toString().toLowerCase();
        String usuario = (String)request.getAttribute("usuario").toString();
        String titulo = request.getAttribute("titulo").toString();
        String linea = "Usuario: " + usuario  + " Titulo: "+titulo+"\r\nConsulta: " + consulta + "\r\n";
        File fichero = new File(ruta_consulta);
        if (fichero.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero,true));
            bw.write(linea);
            bw.close();
        }else{
            fichero.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write(linea);
            bw.close();
        }
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {          
        doBeforeProcessing(request, response);        
        chain.doFilter(request, response);
        doAfterProcessing(request, response);
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {    
        InetAddress address = InetAddress.getLocalHost();
        //String usuario = request.getAttribute("usuario").toString();
        String usuario = request.getParameter("usuario");
        String titulo = request.getParameter("titulo");
        String linea = "Usuario: " + usuario + " IP: " + address.toString() + " Titulo: " + titulo  + "\r\n";
        File fichero = new File(ruta);
        if (fichero.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero,true));
            bw.write(linea);
            bw.close();
        }else{
            fichero.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write(linea);
            bw.close();
        }
    }   

    
    //HttpSession session;
     //HttpServletRequest req  = (HttpServletRequest) request;
    //HttpSession session2 = req.getSession(false);
    //String UsuarioSesion = session2.getAttribute("Usuario").toString();
    
    @Override
    public void destroy() {        
    }
    
    @Override
    public String toString() {
        return ("");
    }    
}
