<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title> Enjoy your work</title></head>
<body>

<form align="right" name="logOut" method="GET" action="servlet">
    <input type="hidden" name="command" value="logOut" />
    <input type="submit" value="Log out"/>
</form>

<form name="addProductForm" method="POST" action="servlet">
    <input type="hidden" name="command" value="addProduct" />
    <table align="center" style="border-color: transparent">
        <tr valign="top" align="center">
            <td width="25%">Enter name product:</td>
            <td width="25%">or enter code:</td>
            <td width="25%">and enter number of products:</td>
        </tr>
        <tr valign="top" align="center">
            <td width="25%"><input type="text" name="name" value=""/></td>
            <td width="25%"><input type="number" name="code" value=""/></td>
            <td width="15%"><input type="number" min="0"  step="0.001" name="number" value=""/></td>
            <td width="25%"><input type="submit" value="add product"/></td>
        </tr>
    </table>
</form><hr/>
<h1 align="center"> New check has been opened</h1>
<h3 align="center"> Current check hasn't any products</h3><br/>

<table border="2" cellpadding="7" cellspacing="0" align="center">
    <tr colspan="2" bgcolor="#D3EDF6" valign="top" align="center">
        <td width="30%">Назва товару/продукту</td>
        <td width="20%">Код товару</td>
        <td width="15%">Кількість</td>
        <td width="15%">ціна за 1</td>
        <td width="20%">ціна за всі</td>
    </tr>
</table>
</form>
<h1></h1><br/>
<h1></h1><br/>
<h1></h1><br/>
<h1></h1><br/>
</body></html>