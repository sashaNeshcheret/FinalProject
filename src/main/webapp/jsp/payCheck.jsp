<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title> Enjoy your work</title></head>
<body>

<h1>Print payed check </h1><br/>
<form align="right" name="addProduct" method="GET" action="servlet">
    <input type="hidden" name="command" value="LogOut" />
    <input type="submit" value="Log out"/>
    <h1>Enter value in cases</h1>


<table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
    <tr colspan="2" bgcolor="#D3EDF6" valign="top" align="center">
        <td width="55%">Назва товару/продукту</td>
        <td width="15%">Кількість</td>
        <td width="15%">ціна за 1</td>
        <td width="15%">ціна за всі</td>
    </tr>
</table>

<c:forEach var = "products" items = "${list}">
    <table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
        <tr colspan="2"  valign="top" align="center">
            <td width="55%">${products.nameProduct}</td>
            <td width="15%">${products.numberOfProduct}</td>
            <td width="15%">${products.priceForOne}</td>
            <td width="15%">${products.resultPrice}</td>
        </tr>
    </table>
</c:forEach>

<form align="center" name="closeForm" method="GET" action="servlet">
    <input type="hidden" name="command" value="closeCheck" />
    <input type="submit" value="Close check"/>
</form>


</body></html>
