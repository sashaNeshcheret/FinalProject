<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title> Enjoy your work</title></head>
<body>

<h1 align="center">X report</h1><br/>


<table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
    <tr colspan="2" bgcolor="#D3EDF6" valign="top" align="center">
        <td width="55%">user id</td>
        <td width="15%">Кількість</td>
        <td width="15%">ціна за всі</td>
        <td width="15%">час закриття</td>
    </tr>
</table>

<c:forEach var = "products" items = "${chequeReportsList}">
    <table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
        <tr colspan="2"  valign="top" align="center">
            <td width="55%">${products.userId}</td>
            <td width="15%">${products.numberOfProduct}</td>
            <td width="15%">${products.chequeAmount}</td>
            <td width="15%">${products.date}</td>
        </tr>
    </table>
</c:forEach>
<br/>
<a  align="center" href="/jsp/general.jsp">On main</a>
</body>
</html>