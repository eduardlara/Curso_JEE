package ucan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/*
 * Aquest Servlet �s un Servlet especial encarregat d'atendre els events.
 * Per aquest motiu es defineix al descriptor com a "Listener"
 * Cada cop que un servlet d'aquesta aplicaci� crea, modifica o esborra un par�metre
 * de Sessi�, es posa en marxa aquest "listener" d'events.
 */


public class ControlUsuaris implements HttpSessionAttributeListener {

	int contador = 0;
	String nomAtribut = "";
	String ruta = "";
	public String usuCon = "";
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent attadd) 
	{
		String nomAtt = attadd.getName();
		if (nomAtt == "usucon") 
			ruta = (String) attadd.getValue();
		
		String sFichero = ruta;
        File fichero = new File(sFichero);
        BufferedWriter bw;
		
		nomAtribut = attadd.getName();
		
		if (nomAtribut == "Usuari")
		{
			contador++;
			try {
				bw = new BufferedWriter(new FileWriter(sFichero,true));
				bw.write("Numero d'usuaris connectats: " + contador + "\n");
		        bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent attrem) 
	{
		String sFichero = ruta;
        File fichero = new File(sFichero);
        BufferedWriter bw;
		
		nomAtribut = attrem.getName();
		
		if (nomAtribut == "Usuari")
		{
			contador--;
			try {
				bw = new BufferedWriter(new FileWriter(sFichero,true));
				bw.write("Numero d'usuaris connectats: " + contador + "\n");
		        bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) 
	{
		//contador++;
	}
	

}
