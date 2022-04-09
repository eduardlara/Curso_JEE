/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author usuario
 */

public class MiHttpSessionListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        System.out.println("Inicio de sesión!!"); 
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        System.out.println("Fin de sesion!!"); 
    }    
}



