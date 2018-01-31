<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<html><head><title> Enjoy your work</title></head>
<body>
<form align="right" name="LogOut" method="GET" action="servlet">
    <input type="hidden" name="command" value="LogOut" />
    <input type="submit" value="Log out"/>
</form>
<h1 align="center">Cheque has been printed</h1><br/>


<table width="20%" border="2" cellpadding="7" cellspacing="0" align="center">
    <tr>
        <h3 align="center">TOB "Capaфан"</h3>
        <h1 align="center">магазин "Пчелка маркет"</h1>
        <h5 align="center">м. Київ, проспект Визволителів, 6</h5>
        <h1 align="center">Пчелка маркет</h1>
    </tr>
    <tr>
        <h4 align="center">#Чек # [${chequeReports.id}]</h4>
    </tr>
    <c:forEach var = "products" items = "${list}">
        <tr>
            <h4 align="center">${products.numberOfProduct}X${products.priceForOne}</h4>
        </tr>
        <tr><h4 align="center">${products.nameProduct}</h4></tr>

        <tr><h4 align="center">${products.resultPrice}</h4></tr>
    </c:forEach>
    <tr>
        <h1 align="center"> СУМА ${chequeReports.chequeAmount}</h1>
    </tr>
    <tr>
        <h1 align="center">cashier id: ${chequeReports.userId}</h1>
    </tr>
    <tr>
        <h4 align="center">час закриття чеку: ${date}</h4>
    </tr>
</table>

<form align="center" name="openForm" method="GET" action="servlet">
    <input type="hidden" name="command" value="openCheck" />
    <input type="submit" value="Open check"/>
</form>


</body></html>

