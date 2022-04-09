<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>GANTT</title>
  </head>
  <style>
    @import url('https://fonts.googleapis.com/css?family=Permanent+Marker');
    @import url('https://fonts.googleapis.com/css?family=Merriweather');
    body{
      font-family: 'Permanent Marker', cursive;
      text-transform: uppercase;
    }
    h1{ font-weight: bold; }
    center{
      border: 1px solid;
      border-radius: 50px;
      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
      padding: 10%;
      margin: 3%;
    }
    input[type="submit"] {
      padding: 20px;
      text-transform: uppercase;
    }
  </style>
  <body>
    <center>
      <h1>GANTT</h1>
      <form method="POST" action="/controlador">
        <input type="submit" name="envia" value="Visualitza" />
        <h3>O</h3>
        <input type="submit" name="envia" value="Edita" />
      </form>
    </center>
  </body>
</html>
