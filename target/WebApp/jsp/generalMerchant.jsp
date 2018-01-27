<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Enjoy your work</title>
    <style type="text/css">

        #menu{
            margin: 0,auto;
        }
        label {
            margin-right: 3px;
        }
        input, select{
            margin-right: 5px;
        }
        form{
            margin-bottom:10px;
            width: 1200px;
        }
    </style>
</head>
<body>
<h1 align="center">Enjoy your work</h1>
<div align="center" id="menu">
    <form align="right" name="logOut" method="GET" action="servlet">
        <input type="hidden" name="command" value="logOut" />
        <input type="submit" value="Log out"/>
    </form>

    <form  name="createProductForm" method="POST" action="servlet">
        <fieldset>
            <legend>Create product</legend>
            <input type="hidden" name="command" value="createProduct" />
            <label> name product: </label>
            <input type="text" name="name" value=""/>
            <label>code: </label>
            <input type="number" min="1000"  step="1" name="code" value=""/>
            <label>countable: </label>
            <select name="countable">
                <option selected value="true">yes</option>
                <option value="false">no</option>
            </select>
            <label> price for one: </label>
            <input type="number" min="0"  step="0.001" name="price" value=""/>
            <input type="submit" value="createProduct"/>
        </fieldset>
    </form>
    <form  name="addProductStockForm" method="POST" action="servlet">
        <fieldset>
            <legend>Add product</legend>
            <input type="hidden" name="command" value="addProductStock" />
            <label>name product:</label>
            <input type="text" name="name" value=""/>
            <label>code:  </label>
            <input type="number" min="1000"  step="1" name="code" value=""/>
            <label>number: </label>
            <input type="number" min="0"  step="0.001" name="number" value=""/>
            <input type="submit" value="add product stock"/>
        </fieldset>
    </form>
</div>
<table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
    <tr colspan="2" bgcolor="#D3EDF6" valign="top" align="center">
        <td width="50%">Назва товару/продукту</td>
        <td width="15%">Код</td>
        <td width="15%">ціна за 1</td>
        <td width="20%">Кількість на складі</td>
    </tr>
    <c:forEach var = "products" items = "${list}">
        <tr colspan="2"  valign="top" align="center">
            <td width="50%">${products.nameProduct}</td>
            <td width="15%">${products.codeProduct}</td>
            <td width="15%">${products.priceForOne}</td>
            <td width="20%">${products.numberOfProduct}</td>
        </tr>
</c:forEach></table></br>
<form align="right" name="pagStockForm" method="get" action="servlet">
    <input type="hidden" name="command" value="getProductStock" />
    <input type="hidden" name="numberPage" value="${numberPage}" />
    <input type="submit" value="next"/>
</form>

</body></html>