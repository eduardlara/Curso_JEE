<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList" %>

<%-- Abans de començar l'html es genera el codi necessàri per recuperar un paràmetre --%>

<% 
String p=request.getAttribute("conta").toString();
%>

<%-- <jsp:include page='cab1.jsp'> --%>
<%--     <jsp:param name="conta" value="${conta}"/> --%>
<%-- </jsp:include> --%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>

	<jsp:include page='cab.jsp'>
    	<jsp:param name="conta" value="<%=p%>"/>
	</jsp:include>

	<% 
	ArrayList al = new ArrayList();
	al = (ArrayList) request.getAttribute("taula");
	String linia = "";

	StringBuilder pepe = new StringBuilder();
  
	for (int i=0; i < al.size(); i++ )
	{
		pepe.append("<h3>" + al.get(i) + "</h3>");
	}
	
	pepe.append("<table border='1' WIDTH='50%'>");
	
	for (int i=0; i < al.size(); i++ )
	{
		linia = (String) al.get(i);
		pepe.append("<tr>");
		if (i%2 == 0)
			Linia_Groc (pepe, linia);
		else
			Linia_Blau (pepe, linia);
		pepe.append("</tr>");
	}

	pepe.append("</table>");
	out.println(pepe.toString());
  	%>
	<!--   	Mètode per mostrar les linies de color groc: -->
  	<%!
  	void Linia_Groc (StringBuilder pepe, String linia)
  	{
		pepe.append("<td BGCOLOR='YELLOW'><b>" + linia + "</b></td>");
  	}
  	%>
	<!--   	Mètode per mostrar les linies de color blau: -->
	<%!
  	void Linia_Blau (StringBuilder pepe, String linia)
  	{
		pepe.append("<td BGCOLOR='BLUE'><b>" + linia + "</b></td>");
  	}
	%>

    <jsp:include page="peu_pagina.html" />
	
	</body>

</html>


