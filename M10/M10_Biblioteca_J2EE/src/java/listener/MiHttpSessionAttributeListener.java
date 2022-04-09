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
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 *
 * @author usuario
 */

public class MiHttpSessionAttributeListener implements HttpSessionAttributeListener {
    int contador = 0;
    String sFichero = "c:\\prova\\usuariosConectados.txt";
    
    @Override
    public void attributeAdded(HttpSessionBindingEvent hsbe) {
    
        String nombreAtt = hsbe.getName();
        String valorAtt = hsbe.getValue().toString();
    			 	
	if (nombreAtt.equals("Usuario")){
            contador++;
            try {
                Date now = new Date();
                String str = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(now);
                BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero,true));
		bw.write(str + " Usuarios conectados: " + contador + "\r\n");
		bw.close();
            } catch (IOException e) {
            	System.out.println(e.getMessage());
            }
	}
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent hsbe) {
        
        String nombreAtt = hsbe.getName();
        String valorAtt = hsbe.getValue().toString();
		
	if (nombreAtt.equals("Usuario")){
            contador--;
            if (contador>=0){
                try {
                    Date now = new Date();
                    String str = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(now);
                    BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero,true));
                    bw.write(str + " Usuarios conectados: " + contador + "\r\n");
                    bw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
	}
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent hsbe) {
        
    }    
}

	
