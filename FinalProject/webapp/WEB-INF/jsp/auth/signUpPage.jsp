<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 30.04.21
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Link.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../../resources/css/SignIn.css"/> ">
</head>
<body>

<a href="http://localhost:8080/controller?command=ACT_SHOW_AUTH" class="s1">Back</a>

<h1>Sign Up</h1>

<div id="wrapper">
    <form id="signin" action="/controller?command=ACT_SIGNUP" method = "post">
        <input type = "text" name="login" placeholder="Login"/><br/>
        <input type = "password" name="password" placeholder="Password"/><br/>
        <input type = "text" name="name" placeholder="Name"/><br/>
        <input type = "email" name="email" placeholder="Email"/><br/>
        <button type="submit">&#xf0da;</button>
    </form>
</div>
</body>
</html>
