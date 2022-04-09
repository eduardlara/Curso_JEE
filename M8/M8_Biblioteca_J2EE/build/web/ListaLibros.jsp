
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Libros</title>
    </head>
    <body>
	<%@include file="encabezado.jsp" %>
	 <%!
	    //Función que retorna 3 colores diferentes en función del módulo 3 de i
	    //donde i es un parámetro de entrada
            public String pintarColor(int i){
                String color="";
                if (i%3==0) color="green";
                else if (i%3==1) color="blue";
                else color="red"; 
                return color;
            }
        %>
	
	<%ArrayList<String> lista = (ArrayList<String>)request.getAttribute("lista");%>
	
        <H1>SELECCIONA LIBROS PREFERIDOS</H1>		       
        <form action=ListaUsuarioServlet method=post>
	    <table border=1>   
	    <tr><h2><td>ID<td>TITULO<td>AUTOR<td>EDITORIAL<td>FECHA<td>CATEGORIA<td>NOVEDAD<td>Seleccion</h2>
                <%  int i=0;
		    for (String p : lista){
			out.print("<tr style='color:"+pintarColor(i)+";'><h3><td>"+ p +"</h3>");
			out.print("<td style=\"text-align: center;\">");
			out.print("<input type=checkbox name=lista value=\"" + p + "\"/>");
			i++;
		    }
		%>
	    </table>    
	    <input type=submit name=boton value=Selecciona>
	    <input type=submit name=boton value='Desconecta sesion'>
        </form>	
	<%@include file="pie.jsp" %>
    </body>
</html>


