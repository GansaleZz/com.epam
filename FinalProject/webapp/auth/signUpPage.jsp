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
    <form action="/controller?command=SIGNUP" method = "post">
        <b>Login: </b><input type = "text" name="login"/><br/>
        <b>Password: </b><input type = "password" name="password"/><br/>
        <b>Name: </b><input type = "text" name="name"/><br/>
        <b>Email: </b><input type = "text" name="email"/><br/>
        <input type = "submit" value="Sign Up" />
    </form>
    <a href="http://localhost:8080/auth/authPage.jsp">Back</a>
</body>
</html>
