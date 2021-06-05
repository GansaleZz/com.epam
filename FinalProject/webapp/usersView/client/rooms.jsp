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
  <link rel="stylesheet" href="<c:url value="/resources/css/styles.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/tableStyles.css"/> ">
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
    <li><a href="http://localhost:8080/controller?command=ACTLOGOUT"><c:out value="${bundle.getString('logOut')}"/></a></li>
  </ul>
</nav>
<div class="Table">
  <table>
  <colgroup>
    <col span="5">
  </colgroup>
  <caption><c:out value="${bundle.getString('roomsList')}" /></caption>
  <tr>
    <th><b><c:out value="${bundle.getString('roomNumber')}"/></b></th>
    <th><b><c:out value="${bundle.getString('roomClass')}"/></b></th>
    <th><b><c:out value="${bundle.getString('roomStatus')}"/></b></th>
    <th><b><c:out value="${bundle.getString('numberOfSeats')}"/></b></th>
    <th><b><c:out value="${bundle.getString('priceDay')}"/></b></th>
  </tr>
  <c:forEach var="room" items="${list}">
    <tr>
      <td><c:out value="${room.roomNumber}"/> </td>
      <td>
        <c:choose>
          <c:when test="roomStatus == 'AVAILABLE">
            <c:out value="${bundle.getString('roomStatus.available')}"/>
          </c:when>
          <c:otherwise>
            <c:out value="${bundle.getString('roomStatus.closed')}"/>
          </c:otherwise>
        </c:choose>
      </td>
      <td><c:choose>
        <c:when test="${room.roomClass == 'BUSINESS'}">
          <c:out value="${bundle.getString('business')}"/>
        </c:when>
        <c:when test="${room.roomClass == 'ECONOM'}">
            <c:out value="${bundle.getString('econom')}"/>
        </c:when>
        <c:when test="${room.roomClass == 'LUXE'}">
            <c:out value="${bundle.getString('luxe')}"/>
        </c:when>
        <c:when test="${room.roomClass == 'PREMIUM'}">
          <c:out value="${bundle.getString('premium')}"/>
        </c:when>
      </c:choose></td>
      <td><c:out value="${room.numberOfSeats}"/></td>
      <td><c:out value="${room.price}"/></td>
    </tr>
  </c:forEach>
</table>
</div>
</body>
</html>
