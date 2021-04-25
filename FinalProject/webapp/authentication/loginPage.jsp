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
    <title>Title</title>
</head>
<body>
    <form action="/LogIn" method = "post">
        Login:<input type = "text" name="username"/><br/>
        Password:<input type = "password" name="password"/><br/>
        <input type = "submit" value="login" />
    </form>
</body>
</html>
