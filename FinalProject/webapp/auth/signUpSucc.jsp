<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 30.04.21
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Success</title>
    <link rel="stylesheet" href="<c:url value="../resources/css/auth.css"/> ">
</head>
<body>
  <h1>You were successfully signed up! Now you can sign in</h1>
  <a href="http://localhost:8080/auth/logInPage.jsp" class="s2">Log In</a>
  <a href="http://localhost:8080/auth/authPage.jsp" class="s2">Back</a>
</body>
</html>
