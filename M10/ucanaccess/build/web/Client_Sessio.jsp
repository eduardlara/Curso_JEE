<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Benvinguda</title>
</head>
<body>
   
   <jsp:include page="cab_pagina.html" />

  <% 
    String usuari = request.getParameter("usuari");
    String bd = request.getParameter("bd");
    bd = "false";
    
    StringBuilder pepe = new StringBuilder();
    if (bd.equals("false"))
       pepe.append("<h1>NO Connectat a la BD (crida amb GET o POST)</h1>");
    pepe.append("<h1>Benvinut Usuari " + usuari + "</h1>");
    
	//  ConsultaLlibresServlet_2 per treballar amb sessions
    pepe.append("<form method=GET action=ConsultaLlibresServlet_2>");
    pepe.append("Sel·lecció del llibre: <input type='text' name='titol'<BR>");
    pepe.append("<input type=hidden name=usuari value="+usuari+">");
    pepe.append("<input type=submit value='Recerca llibres amb Sessio'>");
    pepe.append("</form>");
    
    pepe.append("<br/>");
    
	//  ConsultaLlibresServlet_4 per treballar amb ArrayList dins de sessio:
    pepe.append("<form method=GET action=ConsultaLlibresServlet_4>");
    pepe.append("Sel·lecció del llibre: <input type='text' name='titol'<BR>");
    pepe.append("<input type=hidden name=usuari value="+usuari+">");
    pepe.append("<input type=submit value='Recerca llibres amb ArrayList'>");
    pepe.append("</form>");

    pepe.append("<br/>");
    
	//  ConsultaLlibresServlet_5 per treballar amb ArrayList dins de sessio i amb accés a BBDD:
    pepe.append("<form method=GET action=servlet2>");
    pepe.append("Sel·lecció del llibre: <input type='text' name='titol'<BR>");
    pepe.append("<input type=hidden name=usuari value="+usuari+">");
    pepe.append("<input type=submit value='Recerca am ArrayList i BBDD'>");
    pepe.append("</form>");

    out.println(pepe.toString());
  %>
  
  <jsp:include page="peu_pagina.html" /></html>

</body>
