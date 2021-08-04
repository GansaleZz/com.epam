<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 4.08.21
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Verify</title>
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../../resources/css/Link.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../../resources/css/SignIn.css"/> ">
</head>
<body>
    <h1>Verify your account by code which was sent on your email after registration</h1>
    <div class="wrapper" style="margin: 50px auto">
        <form id = "signin" action="controller?command=ACT_VERIFY_ACCOUNT" method = "post" style="margin: 0 auto;">
            <c:choose>
                <c:when test="${verifyTry == true}">
                    <p style="color: red; width: 300px; position: relative">
                        You entered wrong code.. Try again
                    </p>
                </c:when>
            </c:choose>
            <input type="text" name="code" placeholder="Enter code" minlength="9" maxlength="9" required>
            <input type="submit" value="Submit">
        </form>
    </div>
    <a href="http://localhost:8080/controller?command=ACT_SHOW_AUTH" class="s2">Back</a>
</body>
</html>
