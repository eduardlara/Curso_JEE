<%-- 
    Document   : consulta
    Created on : 01-dic-2017, 18:38:47
    Author     : eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BIENVENIDA</title>
    </head>
    <body>
            <%String usuario=request.getParameter("usuario");%>
            <%String iniciado=request.getParameter("iniciado");%>
        
            <%if (iniciado.equals("false")){%>
                <h1>Conectado a la BD (llamada GET)</h1>
            <%}%>   
            
            <h1>BIENVENIDO USUARIO <%=usuario%></h1>            
            <form method=GET action=ConsultaLibrosServlet>
                Selección de Libro: <input type='text' name='titulo'><br><br>
                <input type=submit>            
            </form>        
        
    </body>
</html>
