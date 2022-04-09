<img src='G:/Projectes_Eclipse/Nou_Exercicis_JEE/WebContent/Mafalda.jpg' height=50 width=50>
<%
int contador=0;
String pr = request.getParameter("conta");
contador=Integer.parseInt(pr);
%>
<p align="center">
Capçalera de la pàgina - Llibres sel·lecionats: <%=contador%>
</p>
<hr/>