<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c-rt" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Edita Projectes</title>
    </head>
    <body>
        <h1>Edita el XML:</h1>
        <br/>
        <form method="POST" action="/controlador" id="form" class="form"
              style="text-align: center;border: 1px solid;margin: 1% 5% 1% 5%;padding: 5% 1% 1% 1%;">
            <c:set var="i" value="0"/>
            <c:forEach var="a" items="${llistatProjectes}">
                Projecte:   <input type="text" name="nameP" value="${a.name}">
                Desc:       <input type="text" name="descP" value="${a.desc}">
                <fmt:formatDate var="dini" value="${a.dini}" pattern="dd-MM-yyyy HH:mm:ss"/>
                <fmt:formatDate var="dfin" value="${a.dfin}" pattern="dd-MM-yyyy HH:mm:ss"/>
                Data Inici: <input type="text" name="data_iniP" value="${dini}">
                Data Fi:    <input type="text" name="data_finP" value="${dfin}">
                <br/>
                <section>
                    <c:forEach var="b" items="${a.llistaTasques}">
                        <div>
                            Tasca :     <input type="text" name="name${a.name}" value="${b.name}">
                            Desc:       <input type="text" name="desc${a.name}" value="${b.desc}">
                            <fmt:formatDate var="diniT" value="${b.dini}" pattern="dd-MM-yyyy HH:mm:ss"/>
                            <fmt:formatDate var="dfinT" value="${b.dfin}" pattern="dd-MM-yyyy HH:mm:ss"/>
                            Data Inici: <input type="text" name="data_ini${a.name}" value="${diniT}">
                            Data Fi:    <input type="text" name="data_fin${a.name}" value="${dfinT}">
                                        <input type="button" class="clonar" value="Duplicar Tasca"/>
                                        <input type="button" class="eliminar" value="Eliminar Tasca"/>
                        </div>
                    </c:forEach>
                </section>
                <br/>
            </c:forEach>
            <center><br><input type="submit" name="envia" value="Form" style="padding: 1%;font-weight: bold;"/></center>
        </form>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $(".clonar").on('click', function(){
                    $(this).parent().clone().removeClass('tasques').appendTo($(this).parent().parent());
                });
                $(document).on("click",".eliminar",function(){
                var parent = $(this).parents().get(0);
                    $(parent).remove();
                });
            });
    </script>
    </body>
</html>
