<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Enjoy your work</title></head>
<body>
<h1 align="center">Enjoy your work</h1>

<form align="center" name="openForm" method="GET" action="servlet">
    <input type="hidden" name="command" value="openCurrentCheck" />
    <input type="submit" value="Open current check"/>
</form>

<form align="center" name="openForm" method="GET" action="servlet">
    <input type="hidden" name="command" value="openCheck" />
    <input type="submit" value="Open new check"/>
</form>

<form align="center" name="outXForm" method="GET" action="servlet">
    <input type="hidden" name="command" value="outX" />
    <input type="submit" value="Print X report"/>
</form>
</body></html>