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
  <link rel="stylesheet" href="<c:url value="/resources/css/auth.css"/> ">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<nav class="one">
  <ul>
    <li><a href="http://localhost:8080/controller?command=ACTSHOWHOME"><i class="fa fa-home fa-fw"></i><c:out value="${bundle.getString('home')}"/></a></li>
    <li><a href="http://localhost:8080/controller?command=ACTSHOWROOMS"><c:out value="${bundle.getString('rooms')}"/></a></li>
    <li><a href="http://localhost:8080/controller?command=ACTSHOWPROFILE"><c:out value="${bundle.getString('profile')}"/></a></li>
    <li><a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE"><c:out value="${bundle.getString('createRequest')}"/></a></li>
    <li><a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS"><c:out value="${bundle.getString('requests')}"/></a></li>
    <li><a href="http://localhost:8080/controller?command=LOGOUT"><c:out value="${bundle.getString('logOut')}"/></a></li>
  </ul>
</nav>

<p>
  <table>
  <colgroup>
    <col span="5" style="background: Khaki">
  </colgroup>
  <caption><c:out value="${bundle.getString('roomsList')}" /></caption>
  <tr>
    <th><c:out value="${bundle.getString('roomNumber')}"/></th>
    <th><c:out value="${bundle.getString('roomClass')}"/></th>
    <th><c:out value="${bundle.getString('roomStatus')}"/></th>
    <th><c:out value="${bundle.getString('numberOfSeats')}"/></th>
    <th><c:out value="${bundle.getString('priceDay')}"/></th>
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
