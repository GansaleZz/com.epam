<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 30.04.21
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <form action="/SignUp" method = "post">
        Login:<input type = "text" name="login"/><br/>
        Password:<input type = "password" name="password"/><br/>
        Name:<input type = "text" name="name"/><br/>
        <input type = "submit" value="Sign Up" />
    </form>
    <a href="http://localhost:8080/authentication/authPage.jsp">Back</a>
</body>
</html>
