/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BaseDatos {
    private Connection conn;
    
    public BaseDatos(){         
        try {
            //Para el Driver mysql-connector-java-5.1.47.jar
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String conex = "jdbc:mysql://localhost:3306/biblioteca_online";
            this.conn = DriverManager.getConnection(conex,"root","");                                            
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }   
        
    public boolean compruebaUsuario(String usuario, String password){
	boolean check=false;
	try {
	    DatabaseMetaData pepe=conn.getMetaData();
	    Statement s = conn.createStatement();
	    String sql = "SELECT count(*) FROM USUARIOS WHERE usuario='"+usuario+"' and password='"+password+"'";
	    s.execute(sql);
	    ResultSet rs = s.getResultSet();
	    rs.next();
	    if (rs.getInt(1)>0)
		check=true;		
	} catch (SQLException ex) {
	    System.out.print(ex.getMessage());
	}
	return check;
    }
    
    public ArrayList<String> filtraLibros(String titulo, StringBuffer sb){
	ArrayList<String> lista = new ArrayList<String>();             	

	try {
            DatabaseMetaData pepe=conn.getMetaData();
            Statement s = conn.createStatement();
            String sql = "SELECT * FROM LIBROS WHERE TITULO LIKE '%"+titulo+"%' OR "
		                                   + "AUTOR LIKE '%"+titulo+"%' OR "
		                                   + "CATEGORIA LIKE '%"+titulo+"%' OR "
						   + "EDITORIAL LIKE '%"+titulo+"%'" ;
	    s.execute(sql);
	    ResultSet aux = s.getResultSet();
	    SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
            while (aux.next()) {  //id-titulo-autor-editorial-fecha-categoria-novedad
                String linea = aux.getInt(1) +"<td>"+ aux.getString(2)+"<td>" +aux.getString(3) +"<td>"+ aux.getString(4) 
			+"<td>"+ dt1.format(aux.getDate(5)) +"<td>"+ aux.getString(6)+"<td>"+ aux.getInt(7);		
		lista.add(linea);
		linea=linea.replace("<td>",";");
		sb.append(linea+"\r\n");               
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());             
        }    
	return lista;
    }
    
    public ArrayList<String> consultaLibros(){
	ArrayList<String> lista =new ArrayList<String>();
	try {
	    DatabaseMetaData pepe=conn.getMetaData();
	    Statement s = conn.createStatement();
	    String sql = "SELECT * FROM LIBROS";
	    s.execute(sql);
	    ResultSet rs = s.getResultSet();
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
	    while (rs.next()) {  //id-titulo-autor-editorial-fecha-categoria-novedad
                String linea = rs.getInt(1) +";"+ rs.getString(2)+";" +rs.getString(3) +";"+ rs.getString(4) 
			+";"+ dt1.format(rs.getDate(5)) +";"+ rs.getString(6)+";"+ rs.getInt(7);		
		lista.add(linea);		               
            }	    
	} catch (SQLException ex) {
	    System.out.print(ex.getMessage());
	}
	return lista;
    }
    
    public void insertarLibro(String id, String titulo, String autor, String editorial, 
	     String fecha, String categoria, String novedad){
	String query = " insert into libros (id, titulo, autor, editorial, fecha, categoria, novedad)"
        + " values (?, ?, ?, ?, ?, ?, ?)";
	try {	  
	    PreparedStatement preparedStmt;
	    preparedStmt = conn.prepareStatement(query);
	    preparedStmt.setString (1, id);
	    preparedStmt.setString (2, titulo);
	    preparedStmt.setString (3, autor);
	    preparedStmt.setString (4, editorial);
	    
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date myDate = formatter.parse(fecha);
	    java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
	    	    
	    preparedStmt.setDate (5, sqlDate);
	    preparedStmt.setString (6, categoria);
	    preparedStmt.setString (7, novedad);
	   
	    preparedStmt.executeUpdate();
	} catch (SQLException ex) {
	    System.out.print(ex.getMessage());
	}  catch (ParseException ex){
	    System.out.print(ex.getMessage());
	}
    }
    
    public void eliminarLibro(String id){
	String query = " delete from libros where id="+id;
	
	try {	  
	    PreparedStatement preparedStmt = conn.prepareStatement(query);
	    preparedStmt.executeUpdate();
	} catch (SQLException ex) {
	    System.out.print(ex.getMessage());
	}
    } 
    
    public String recuperarLibro(String id){
	String linea="";
	try {
	    DatabaseMetaData pepe=conn.getMetaData();
	    Statement s = conn.createStatement();
	    String sql = "SELECT * FROM LIBROS WHERE ID="+id;
	    s.execute(sql);
	    ResultSet rs = s.getResultSet();
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
	    rs.next();
            linea = rs.getInt(1) +";"+ rs.getString(2)+";" +rs.getString(3) +";"+ rs.getString(4) 
			+";"+ dt1.format(rs.getDate(5)) +";"+ rs.getString(6)+";"+ rs.getInt(7);             
	} catch (SQLException ex) {
	    System.out.print(ex.getMessage());
	}
	return linea; 
    }
    
    public void modificarLibro(String id, String titulo, String autor, String editorial, 
	             String fecha, String categoria, String novedad){
	String query = " update libros set titulo=?, autor=?, editorial=?, fecha=?, categoria=?, novedad=? "
        + " where id=?";	
	try {	  
	    PreparedStatement preparedStmt = conn.prepareStatement(query);
	    preparedStmt.setString (1, titulo);
	    preparedStmt.setString (2, autor);
	    preparedStmt.setString (3, editorial);
	    
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date myDate = formatter.parse(fecha);
	    java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
	    	    
	    preparedStmt.setDate (4, sqlDate);
	    preparedStmt.setString (5, categoria);
	    preparedStmt.setString (6, novedad);
	    preparedStmt.setString (7, id);
	    System.out.print(preparedStmt.toString());
	    
	    preparedStmt.executeUpdate();
	} catch (SQLException ex) {
	    System.out.print(ex.getMessage());
	}  catch (ParseException ex){
	    System.out.print(ex.getMessage());
	}
	
    }

}



/* 
     public ResultSet ConsultaSelect (String sql){
        ResultSet rs=null;
        try {
            DatabaseMetaData pepe=conn.getMetaData();
            Statement s = conn.createStatement();
            s.execute(sql);
            rs = s.getResultSet();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return rs;
    }
    public boolean Inserta (String equipo, String ciudad, String estadio, String quiniela){
        boolean p=true;
        try{
            String sql = " INSERT INTO Equipos (equipo, ciudad, estadio, nomquinlfp ) values ('"
                    + equipo + "','" + ciudad + "','" + estadio + "','"+quiniela+"');";
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            p=false;
        }      
        return p;
    }
    public boolean Elimina (String equipo){
        boolean p=true;
        try{
            String sql = " DELETE from Equipos WHERE codeq="+equipo;
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            p=false;
        }      
        return p;
    }
}
*/



