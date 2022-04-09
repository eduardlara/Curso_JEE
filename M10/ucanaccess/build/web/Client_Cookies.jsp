<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="cab_pagina.html" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Benvinguda</title>
</head>
<body>
  <% 
    String usuari = request.getParameter("usuari");
    String bd = request.getParameter("bd");
    bd = "false";
    
    StringBuilder pepe = new StringBuilder();
    if (bd.equals("false"))
       pepe.append("<h1>Connectat a la BD (crida amb GET)</h1>");
    
    pepe.append("<h1>Benvinut Usuari " + usuari + "</h1>");
    
    /* ConsultaLlibresServlet_3 per treballar amb cookies  */
    pepe.append("<form method=GET action=ConsultaLlibresServlet_3>");
    pepe.append("Sel·lecció del llibre: <input type='text' name='titol'<BR>");
    pepe.append("                       <input type=submit name='botoR' value='Recerca llibres amb Cookies'>");
    pepe.append("</form>");
    pepe.append("<br/>");

    pepe.append("<form method=POST action=LlistaCookies>");
    pepe.append("                       <input type=hidden name=usuari value="+usuari+">");
    pepe.append("                       <input type=submit name='botoL' value='Llista llibres amb Cookies'>");
    pepe.append("</form>");
    out.println(pepe.toString());
  %>
</body>
</html>

<jsp:include page="peu_pagina.html" />