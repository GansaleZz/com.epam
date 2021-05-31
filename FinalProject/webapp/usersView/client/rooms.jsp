<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 15.05.21
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rooms</title>
  <style>
    table {
      border: 1px solid grey;
      border-collapse: collapse;
      margin: 20px;
    }
    th {
      border: 1px solid grey;
    }
    td {
      border: 1px solid grey;
    }
    caption{
      font-family: annabelle, cursive;
      font-weight: bold;
      font-size: 2em;
      padding: 10px;
      color: #F3CD26;
      text-shadow: 1px 1px 0 rgba(0,0,0,.3);
    }
  </style>
</head>
<body>
<a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
<a href="http://localhost:8080/controller?command=ACTSHOWHOME">Home</a>
<a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
<a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
<a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE">Create request</a>
<a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">Requests</a>

<p>
  <table>
  <colgroup>
    <col span="5" style="background: Khaki">
  </colgroup>
  <caption>List of rooms</caption>
  <tr>
    <th>Room number</th>
    <th>Class</th>
    <th>Status</th>
    <th>Number of seats</th>
    <th>Price (for day)</th>
  </tr>
  <c:forEach var="room" items="${list}">
    <tr>
      <td><c:out value="${room.roomNumber}"/> </td>
      <td><c:out value="${room.roomStatus}"/> </td>
      <td><c:out value="${room.roomClass}"/></td>
      <td><c:out value="${room.numberOfSeats}"/></td>
      <td><c:out value="${room.price}"/> BYN</td>
    </tr>
  </c:forEach>
</table>
</p>

</body>
</html>
