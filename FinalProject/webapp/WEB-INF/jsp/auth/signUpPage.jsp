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
        <c:choose>
            <c:when test="${loginBad == true}">
                <p style="color: red; width: 300px; position: relative">
                    User with this login already exists
                </p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${loginSignUp != null}">
                <input type = "text" name="login" placeholder="Login" value="${loginSignUp}" minlength="3" maxlength="12" required/><br/>
            </c:when>
            <c:otherwise>
                <input type = "text" name="login" placeholder="Login" minlength="3" maxlength="12" required/><br/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${passSignUp != null}">
                <input type = "password" name="password" placeholder="Password" value="${passSignUp}" minlength="5" maxlength="12" required/><br/>
            </c:when>
            <c:otherwise>
                <input type = "password" name="password" placeholder="Password" minlength="5" maxlength="12" required/><br/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${nameSignUp != null}">
                <input type = "text" name="name" placeholder="Name" value="${nameSignUp}" minlength="2" maxlength="12" required/><br/>
            </c:when>
            <c:otherwise>
                <input type = "text" name="name" placeholder="Name" minlength="2" maxlength="12" required/><br/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${emailBad == true}">
                <p style="color: red; width: 300px; position: relative; margin: -15px 0 0 0;">
                    User with this email already exists
                </p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${emailSignUp != null}">
                <input type = "email" name="email" placeholder="Email" value="${emailSignUp}" required/><br/>
            </c:when>
            <c:otherwise>
                <input type = "email" name="email" placeholder="Email" required/><br/>
            </c:otherwise>
        </c:choose>
        <button type="submit">&#xf0da;</button>
    </form>
</div>
</body>
</html>
