<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BIENVENIDA</title>        
    </head>
    <body>            
	<%String usuario=request.getParameter("usuario");
	String iniciado=request.getParameter("iniciado");%>            

	<%
	String usuario2 = session.getAttribute("Usuario").toString();
	out.print("<h3>Usuario sesion:"+ usuario2+"</h3>");        
	String password2 = session.getAttribute("Password").toString(); 
	out.print("<h3> Password sesion:"+password2+"</h3>");   
	%>     

	<%if (iniciado.equals("false")){%>
	    <h1>Conecta do a la BD (llamada GET)</h1>
	<%}%>   

	<h1>BIENVENIDO USUARIO <%=usuario%></h1>            
	<table border="1">		
	<tr><td><h2>Selección de Libro</h2>
	<form method=GET action=ConsultaLibrosServlet>
	    <br> <input type='text' name='titulo'>
	    <input type='hidden' name='usuario' value='<%=usuario%>'>
	    <input type=submit value="Enviar consulta">            
	</form>
	<br>
	</table>		
	<br><br>
	<table border="1">
	<tr><td><h2>Consulta todos los libros</h2>
	<form method=GET action=GestionLibrosServlet>
	    <br><input type='hidden' name='usuario' value='<%=usuario%>'>	
	    <input type=submit name="submit" value="Enviar Consulta">
	</form><br></table>	    
    </body>
</html>


<%--! //Signo de admiración para la creación de una función jsp 
            public static Cookie getCookie(HttpServletRequest request, String name) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals(name)) {
                            return cookie;
                        }
                    }
                }
                return null;
            }


  Cookie ckusuario = getCookie(request, "ckUsuario");
              out.print("<h3>"+ckusuario.getName()+"-"+ckusuario.getValue()+"</h3>");                    
              Cookie ckpassword = getCookie(request, "ckPassword");
              out.print("<h3>"+ ckpassword.getName()+"-"+ckpassword.getValue()+"</h3>");                  
--%>