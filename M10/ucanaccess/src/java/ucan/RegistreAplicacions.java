package ucan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RegistreAplicacions implements ServletContextListener {

	String sFichero = "D:\\Projectes_Eclipse\\Nou_Exercicis_JEE\\WebContent\\registreAplicacio.txt";
    File fichero = new File(sFichero);
    BufferedWriter bw;
	Date dataInici;
	Date dataFi;
	long horaInici;
	long horaFi;
	long diferencia;
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) 
	{
		dataFi = new Date();
		horaFi = dataFi.getTime();
		diferencia = horaFi - horaInici;
		diferencia = diferencia / (1000 * 60);
		
		Date data = new Date();
		try {
			bw = new BufferedWriter(new FileWriter(sFichero,true));
			bw.write("La aplicaci� ha finalitzat el dia: " + dataFi + "\n");
			bw.write("La aplicaci� ha estat conectada  : " + diferencia + " minuts.\n");
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) 
	{
		dataInici = new Date();
		horaInici = dataInici.getTime();
		
//		Calendar calendari = Calendar.getInstance();
		//calendari.get(Calendar.t);
		
		try {
			bw = new BufferedWriter(new FileWriter(sFichero,true));
			bw.write("La aplicaci� s'ha iniciat el dia   : " + dataInici + "\n");
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
