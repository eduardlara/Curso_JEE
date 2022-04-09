
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
	<%@include file="encabezado.jsp" %>
        <h1>LIBROS SELECCIONADOS JSP</h1>
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
        <%
        String usuario = session.getAttribute("Usuario").toString();
        String password = session.getAttribute("Password").toString();
        out.println("<h2>Usuario: "+usuario+"  Password: "+password+"</h2>");  
  
        %>

	<c:forEach var="item" items="${lista}">
	    <c:out value="${item}" /><BR>	      
	</c:forEach>	
	<%--
        String []lista=(String [])session.getAttribute("lista");
	int i=0;
        for(String item:lista){                
                out.println("<h3 style='color:"+pintarColor(i)+";'>"+item+"</h3>");
                i++;
        }

        --%>
	<%@include file="pie.jsp" %>
    </body>
</html>



