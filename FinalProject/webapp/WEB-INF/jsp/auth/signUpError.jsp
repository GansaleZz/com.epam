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
    <title>Error</title>
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Link.css"/> ">
</head>
<body>

  <h1>User with this login already exists or input data incorrect</h1>

  <a href="http://localhost:8080/controller?command=ACT_SHOW_SIGNUP" class="s2">Try again</a>
</body>
</html>
