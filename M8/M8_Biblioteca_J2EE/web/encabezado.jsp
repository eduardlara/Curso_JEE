
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <img src="images/logo.png">
    <h1>Numero de libros seleccionados: <%=session.getAttribute("librosSel").toString()%></h1> 

<table border="1">    
<%
    out.println("<tr><td colspan=2>REQUEST");
    for (Enumeration<String> enumeration = request.getAttributeNames(); enumeration.hasMoreElements();) {
	String attributeName = enumeration.nextElement();
	Object attribute = request.getAttribute(attributeName);

	if(attribute instanceof String){ 
	    out.println("<tr><td>"+attributeName);
	    out.println("<td>" + attribute.toString());
	}
    }
    out.println("<tr><td colspan=2>SESSION");
    Enumeration keys = session.getAttributeNames();
    while (keys.hasMoreElements())
    {
      String key = (String)keys.nextElement();
      out.println("<tr><td>"+key + "<td> " + session.getValue(key) );
    }
    out.println("<tr><td colspan=2>PAGECONTEXT");
    Enumeration claves =pageContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE); 
    while (claves.hasMoreElements())
    {
        String clave = (String)claves.nextElement();
	out.println("<tr><td>"+clave+"<td>"+ pageContext.getAttribute(clave));
    }
    out.println("<tr><td colspan=2>APPLICATION");
    claves = application.getAttributeNames();
    while (claves.hasMoreElements())
    {
        String clave = (String)claves.nextElement();
	out.println("<tr><td>"+clave+"<td>"+ application.getAttribute(clave));
    }
    
%>
</table>

