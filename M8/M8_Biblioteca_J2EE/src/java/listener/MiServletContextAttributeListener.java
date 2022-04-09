/*+
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 *
 * @author usuario
 */

public class MiServletContextAttributeListener implements ServletContextAttributeListener{

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("Atributo creado: " + scae.getName()+"  Valor: "+scae.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
         System.out.println("Atributo eliminado: " + scae.getName()+"  Valor: "+scae.getValue());
        
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("Atributo modificado: " + scae.getName()+"  Valor: "+scae.getValue());    
    }
}

