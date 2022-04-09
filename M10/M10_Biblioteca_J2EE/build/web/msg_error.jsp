<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP DE ERROR</title>
    </head>
    <body>
	<%@include file="encabezado.jsp" %>
        <h1>JSP DE ERROR</h1>
        <h2>Usuario: <%=request.getParameter("usuario")%> </h2>
        <h2>Password: <%=request.getParameter("password")%></h2>
        <h2>Mensaje: <%=request.getParameter("msg_error")%></h2>        
        <!--<h2>Usuario: <%=request.getAttribute("usuario")%> </h2>
        <h2>Password: <%=request.getAttribute("password")%></h2>
        <h2>Mensaje: <%=request.getAttribute("msg_error")%></h2>-->    
	<%@include file="pie.jsp" %>
    </body>
</html>


