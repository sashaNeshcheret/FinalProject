<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Login</title></head>
<body>
<form name="registrationForm" method="POST" action="servlet">
    <h1>Enter value in cases</h1>
    <br/>
    <input type="hidden" name="command" value="registration" />
    <br/>Login:<br/>
    <input type="text" name="login" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value=""/>
    <br/>Position:<br/>
    <select name="position">
        <option selected value="cashier">Cashier</option>
        <option value="senior_cashier">Senior cashier</option>
        <option value="merchant">Merchant</option>
    </select>
    <br/>Name:<br/>
    <input type="text" name="name" value=""/>
    <br/>
    <br/>Check word:<br/>
    <input type="text" name="check word" value=""/>
    <br/>

    <input type="submit" value="Registration in"/>
</form><hr/>
</body></html>