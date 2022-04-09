<%-- 
    Document   : seleccionado
    Created on : 17-dic-2017, 16:17:49
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LIBROS SELECCIONADOS JSP</h1>
        <%
        String usuario = session.getAttribute("Usuario").toString();
        String password = session.getAttribute("Password").toString();
        out.println("<h2>Usuario: "+usuario+"  Password: "+password+"</h2>");  
            
        String []lista=(String [])session.getAttribute("lista");
        for(String item:lista){                
                out.println("<h3>"+item+"</h3>");
        }

        %>
    </body>
</html>


