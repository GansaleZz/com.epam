<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 25.04.21
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Link.css"/> ">
</head>
<body>
<h1 style="font-size: 100px;">
    Welcome!
</h1>

<a href="http://localhost:8080/controller?command=ACTSHOWLOGIN" class="s2">Log In</a><br>
<a href="http://localhost:8080/controller?command=ACTSHOWSIGNUP" class="s2">Sign Up</a>

</body>
</html>
