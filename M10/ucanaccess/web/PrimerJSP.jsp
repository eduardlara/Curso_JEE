<%@ page 
	language="java" 
	contentType="text/html" 
	pageEncoding="UTF-8"
	import="java.util.*"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Primer JSP</title>
	</head>
	<body>
<%-- 		<%@incluse file="G:/Projectes_Eclipse/Nou_Exercicis_JEE/WebContent/logo.html" %> --%>
<%-- 		<%@incluse file="/Nou_Exercicis_JEE/WebContent/logo.html" %> --%>
<%-- 		<%@incluse file="/WebContent/logo.html" %> --%>
<%-- 		<%@ incluse file = "/logo.html" %> --%>
		<%@ incluse file = "logo.html" %>
		<h1>Bon dia</h1>
		Estem a
		<%
		out.println(new GregorianCalendar().getTime().toLocaleString());
		%>
	</body>
</html>