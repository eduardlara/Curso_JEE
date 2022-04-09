<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c-rt" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Proyectos</title>
    </head>
    <style>
        @import url('https://fonts.googleapis.com/css?family=Merriweather');
        body{
            font-family: 'Merriweather', serif;
            text-transform: uppercase;
        }
        h2{ font-weight: bold; }
        table{
            text-transform: capitalize;
            text-align: center;
            border-collapse:collapse;
            border: 0px;
        }
        tr#week, td#week{ border: 1px solid !important; }
    </style>
    <body>
        <h2>Dades dels Projectes:</h2>
        <ul>
            <table>
                <tr id="week">
                    <td id="week" bgcolor="white" width="50px" height="10px">Setmana</td>
                    <c:set var="t" value="${llistatProjectes[0]}"/>
                        <fmt:formatNumber var="week" value ="${(t.data_fin-t.data_ini)/1000/60/60/24/7}" maxFractionDigits="0" type="number"/>
                        <c:set var="fin" value="${week}"/>
                        <c:forEach var="num_sem" begin="1" end="${fin}" step="1">
                            <td id="week" bgcolor="white" width="50px" height="10px"> <c:out value="${num_sem}"/> </td>
                        </c:forEach>
                </tr>
                <c:set var="fecha_ini" value="${llistatProjectes[0].data_ini}"/>

                <c:forEach var="a" items="${llistatProjectes}">
                    <fmt:formatNumber var="semanas" value ="${(a.data_fin-a.data_ini)/1000/60/60/24/7}" maxFractionDigits="0" type="number"/>
                    <fmt:formatNumber var="desp" value ="${(a.data_ini-fecha_ini)/1000/60/60/24/7}" maxFractionDigits="0" type="number"/>
                    <fmt:formatNumber var="sem_comp" value="${(semanas*a.desc)/100}" maxFractionDigits="0" type="number"/>
                    <fmt:formatNumber var="sem_rest" value="${(semanas-sem_comp)}" maxFractionDigits="0" type="number"/>

                    <ct:taula nom="${a.name}" p_ini="${desp}" p_mig="${sem_comp}" p_fin="${sem_rest}" desc="${a.desc}"/>

                    <c:forEach var="b" items="${a.llistaTasques}">
                        <fmt:formatNumber var="semanasB" value ="${(b.data_fin-b.data_ini)/1000/60/60/24/7}" maxFractionDigits="0" type="number"/>
                        <fmt:formatNumber var="despB" value ="${(b.data_ini-fecha_ini)/1000/60/60/24/7}" maxFractionDigits="0" type="number"/>
                        <fmt:formatNumber var="sem_compB" value="${(semanasB*b.desc)/100}" maxFractionDigits="0" type="number"/>
                        <fmt:formatNumber var="sem_restB" value="${(semanasB-sem_compB)}" maxFractionDigits="0" type="number"/>

                        <ct:taula nom="${b.name}" p_ini="${despB}" p_mig="${sem_compB}" p_fin="${sem_restB}" desc="${b.desc}"/>
                    </c:forEach>
                </c:forEach>
            </table>
        </ul>
    </body>
</html>
