<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 30.04.21
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    request.getSession().invalidate();
    response.sendRedirect("/auth/authPage.jsp");
  %>
</body>
</html>
