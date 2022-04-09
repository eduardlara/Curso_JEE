package ucan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * Aquest servlet �s un filtre i per tant, es defineix al descriptor com un "Filter".
 * Esta definit al descriptor com associat al servlet ConsultaLlibresServlet_2
 * Abans de possar-se en marxa el servlet associat, s'executa aquest "Filter" en el "init".
 * Al executar-se el servlet, s'executa el "doFilter" fins abans del "chaio".
 * Quan acaba el servlet, s'executa a partir del "chain".
 */

public class FiltreUsuaris implements Filter {

	private FilterConfig filterConfig = null;
    private ServletContext context = null;
    private String mode = null;
    
    String ruta;

	@Override
	public void destroy() {
		System.out.println("FiltroNombres : destroy");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		// El codi que ve a continuaci� s'executa abans d'executar el Servlet fins a la comanda "chain":
		
		InetAddress address = InetAddress.getLocalHost();
        String sHostName;
        sHostName = address.getHostName();
		
		String sFichero = ruta;
        File fichero = new File(sFichero);
        
        if (fichero.exists()) 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero,true));
            bw.write("Usuario: " + req.getParameter("usuari") 
            	+ " IP: " + sHostName 
            	+ " Libro consultado: " + req.getParameter("titol")  + "\n");
            bw.close();
        }
        else
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero,false));
            bw.write("Usuario: " + req.getParameter("usuari") 
            	+ " IP: " + sHostName 
            	+ " Libro consultado: " + req.getParameter("titol")  + "\n");
            bw.close();
        }

		
		chain.doFilter(req, resp);
		
        // Despres de la comanda "chain", s'executa la resta del codi quan torna del Servlet:
        
		String Respuesta = (String)req.getAttribute("Respuesta").toString().toLowerCase();
		
        if (fichero.exists()) 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero,true));
            bw.write(" Respuesta: \n" + Respuesta);
            bw.close();
        }
        else
        {
        	fichero.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero,false));
            bw.write(" Respuesta: \n" + Respuesta);
            bw.close();
        }


	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		ruta = fConfig.getInitParameter("FicheroRegistro");

	}

}
