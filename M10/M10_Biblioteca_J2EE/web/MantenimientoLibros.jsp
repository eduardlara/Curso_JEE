<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <H1>LIBROS DE LA BIBLIOTECA</H1>		       
        <%ArrayList<String> lista = (ArrayList<String>)request.getAttribute("lista");%>
	<form action="GestionLibrosServlet" method="post">
	    <table border=1>   
	    <tr><h2><td>ID<td>TITULO<td>AUTOR<td>EDITORIAL<td>FECHA<td>CATEGORIA<td>NOVEDAD<td>ELIMINAR<td>MODIFICAR</h2>
	    <%  for (String p : lista){
		    String id = p.substring(0,p.indexOf(";"));
		    p=p.replace(";", "<td>");
		    out.print("<tr><h3><td>"+ p +"</h3>");
		    out.print("<td><center><input type=checkbox name=llista value=" + id + " /></center>");
		    out.print("<td><center><input type=checkbox name=llista2 value=" + id + " /></center>");
		}
	    %>	   
	    </table><br>	    
	    <input type="submit" name="submit" value="Eliminar Libros">
	    <input type="submit" name="submit" value="Recuperar Libro">
	</form><br>
	<%  Object mod = request.getAttribute("FlagModificar");
	    if (mod==null){ %>
	    <form action="GestionLibrosServlet" method="post">
		ID: <input type="text" name="id">
		TITULO: <input type="text" name="titulo">
		AUTOR: <input type="text" name="autor">
		EDITORIAL: <input type="text" name="editorial"> <br><br>
		FECHA: <input type="text" name="fecha">
		CATEGORIA: <input type="text" name="categoria">
		NOVEDAD: <input type="text" name="novedad"><br><br>
		<input type="submit" name="submit" value="Insertar Libro">
	    </form>   
	<%}else{
	    String libro = (String)request.getAttribute("libro");
 	    String[] parte = libro.split(";");%>
	    <form action="GestionLibrosServlet" method="post">
		ID: <input type="text" name="id" value="<%=parte[0]%>" readonly>
		TITULO: <input type="text" name="titulo" value="<%=parte[1]%>">
		AUTOR: <input type="text" name="autor" value="<%=parte[2]%>">
		EDITORIAL: <input type="text" name="editorial" value="<%=parte[3]%>"> <br><br>
		FECHA: <input type="text" name="fecha" value="<%=parte[4]%>">
		CATEGORIA: <input type="text" name="categoria" value="<%=parte[5]%>">
		NOVEDAD: <input type="text" name="novedad" value="<%=parte[6]%>"><br><br>
	    <input type="submit" name="submit" value="Actualiza Libro">
	    <input type="submit" name="submit" value="Cancelar">
	    </form>   
	<%}%>
    </body>
</html>

