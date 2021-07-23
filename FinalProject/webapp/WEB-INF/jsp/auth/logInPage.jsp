<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 25.04.21
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Link.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../../resources/css/SignIn.css"/> ">
    <title>Log in</title>
</head>
<body>
<a href="http://localhost:8080/controller?command=ACT_SHOW_AUTH" class="s1">Back</a>

<h1>Sign In</h1>

<div id ="wrapper">
    <form id="signin" action="/controller?command=ACT_LOGIN" method = "post">
        <input type = "text" name="login" placeholder="Login" minlength="3" maxlength="12"/><br/>
        <input type = "password" name="password" placeholder="Password" minlength="5" maxlength="12"/><br/>
        <button type="submit">&#xf0da;</button>
    </form>
</div>

</body>
</html>
