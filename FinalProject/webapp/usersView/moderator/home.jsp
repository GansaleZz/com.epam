<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 25.04.21
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/auth.css"/> ">
</head>
<body>
<nav class="one">
    <ul>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWHOME"><i class="fa fa-home fa-fw"></i>Home</a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">Requests</a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a></li>
        <li><a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a></li>
    </ul>
</nav>
</body>
</html>
