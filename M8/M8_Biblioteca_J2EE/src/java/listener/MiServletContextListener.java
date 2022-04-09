/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author usuario
 */

public class MiServletContextListener implements ServletContextListener{
    String sFichero;    
    long horaInici;    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext aplicacion = sce.getServletContext();
        sFichero = aplicacion.getInitParameter("RegistroAplicacion"); 
            
        Date now = new Date();
        horaInici = now.getTime();
	String str = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(now);
		
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero,true));
            bw.write("\r\nInicio Aplicación: " + str + " - ");
	    bw.close();
	} catch (IOException e) {
            System.out.println(e.getMessage());
	}
    }    

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Date now = new Date();
	long horaFi = now.getTime();
	long diferencia = (horaFi - horaInici) / 1000;
	String str = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(now);
	
	try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero,true));
            bw.write("Fin Aplicacion: " + str + " - ");
            bw.write("Tiempo conexión: " + diferencia + " segundos.");
            bw.close();
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
}




	

