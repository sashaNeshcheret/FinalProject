<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<html><head><title> Enjoy your work</title></head>
<body>

<h1 align="center">Cheque has been printed</h1><br/>



     <div align="center">       
        <h3>TOB "Capaфан"</h3>
        <h2>магазин "Пчелка маркет"</h2>
        <h5>м. Київ, проспект Визволителів, 6</h5>
        <h2>Пчелка маркет</h2>
        <h4>#Чек # [${chequeReports.id}]</h4>
	</div>
	<table width="20%" frame="void" rules="none" border="2" cellpadding="7" cellspacing="0" align="center">
		 <c:forEach var = "products" items = "${list}">
			 <tr>
					<td colspan="2">${products.numberOfProduct} x ${products.priceForOne}</td>
			</tr>
			 <tr>
					<td width="70%">${products.nameProduct}</td>
					<td width="30%">${products.resultPrice}</td>
			</tr>
		 </c:forEach>
	</table>  
	<div align="center">
        <h2> СУМА ${chequeReports.chequeAmount}</h2>
        <h2>cashier id: ${chequeReports.userId}</h2>
        <h4>час закриття чеку: ${date}</h4>
	</div>


<form align="center" name="openForm" method="GET" action="servlet">
    <input type="hidden" name="command" value="openCheck" />
    <input type="submit" value="Open check"/>
</form>


</body></html>

