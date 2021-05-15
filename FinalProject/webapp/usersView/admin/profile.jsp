<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 15.05.21
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
  <a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
  <a href="http://localhost:8080/usersView/admin/home/home.jsp">Home</a>
  <a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
  <a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
  <a href="http://localhost:8080/controller?command=ACTCREATEREQUEST">Create request</a>
  <a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a>

  <c:set var="user" value="${user}" />
  <p><b>Id: </b><c:out value="${user.id}"/> </br>
    <b>Login: </b><c:out value="${user.login}"/><br>
    <b>Name: </b><c:out value="${user.name}"/><br>
    <b>Email: </b><c:out value="${user.email}"/><br>
    <b>Role: </b><c:out value="${user.userRole}"/></p>
</body>
</html>
