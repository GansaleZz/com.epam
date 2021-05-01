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
    <title>Log in</title>
</head>
<body>
    <form action="/controller?command=LOG_IN" method = "post">
        Login:<input type = "text" name="login"/><br/>
        Password:<input type = "password" name="password"/><br/>
        <input type = "submit" value="Log In" />
    </form>
<a href="http://localhost:8080/logIn/authPage.jsp">Back</a>
</body>
</html>
