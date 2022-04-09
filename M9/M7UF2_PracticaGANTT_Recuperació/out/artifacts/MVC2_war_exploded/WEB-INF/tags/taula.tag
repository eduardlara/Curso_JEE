<%@ attribute name="nom" %>
<%@ attribute name="desc" %>
<%@ attribute name="p_fin" %>
<%@ attribute name="p_mig" %>
<%@ attribute name="p_ini" %>
<%@tag language="java" description="component taula" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tr>
    <td id="name" bgcolor="white" width="275px" height="10px" style="border: 1px solid !important;"> ${nom} </td>
    <c:forEach var="i" begin="1" end="${p_ini}">
        <td bgcolor="white" width="50px" height="10px"> </td>
    </c:forEach>
    <c:forEach var="j" begin="1" end="${p_mig}" step="1">
        <td bgcolor="green" width="50px" height="10px"> </td>
    </c:forEach>
    <c:forEach var="k" begin="1" end="${p_fin}" step="1">
        <td bgcolor="red" width="50px" height="10px"> </td>
    </c:forEach>
    <c:set var="desc" value="${desc}"/>
    <td bgcolor="white"> ${desc}% complet </td>
</tr>

