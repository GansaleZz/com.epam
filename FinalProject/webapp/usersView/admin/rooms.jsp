<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 15.05.21
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
  <a href="http://localhost:8080/usersView/admin/home/home.jsp">Home</a>
  <a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
  <a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
  <a href="http://localhost:8080/controller?command=ACTCREATEREQUEST">Create request</a>
  <a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a>

  <c:forEach var="room" items="${list}">
    <p><b> Id: </b><c:out value="${room.id}"/><br>
      <b>Room status: </b><c:out value="${room.roomStatus}"/> <br>
      <b>Room class:  </b><c:out value="${room.roomClass}"/><br>
      <b>Number of seats: </b><c:out value="${room.numberOfSeats}"/><br>
      <b>Price: </b><c:out value="${room.price}"/><br></p>
  </c:forEach>

</body>
</html>
