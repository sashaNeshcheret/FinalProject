<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title> Enjoy your work</title></head>
<body>

<h1 align="center">Check was closen</h1><br/>


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

<table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
    <tr colspan="2" bgcolor="#D3EDF6" valign="top" align="center">
        <td width="55%"></td>
        <td width="15%"></td>
        <td width="15%"></td>
        <td width="15%">${amount}</td>
    </tr>
</table>
       <!--     <form align="center" name="openCheckForm" method="GET" action="servlet">
                <input type="hidden" name="command" value="OPENCHECK"/>
                <input type="hidden" name="amount" value="${chequeReports.chequeAmount}"/>
                <input type="submit" value="Open new check"/>
            </form> -->
<form align="center" name="openForm" method="GET" action="servlet">
    <input type="hidden" name="command" value="payAndPrintCheque" />
    <input type="submit" value="pay and print cheque"/>
</form>


</body></html>

