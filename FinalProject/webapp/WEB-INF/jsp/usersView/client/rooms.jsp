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
  <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/Table.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/Footer.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<div class="page">
  <nav class="one">
    <ul>
      <li>
        <a href="http://localhost:8080/controller?command=ACTSHOWHOME">
          <i class="fa fa-home fa-fw" aria-hidden="true"></i>
          <c:out value="${bundle.getString('home')}"/></a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACTSHOWROOMS">
          <i class="fa fa-shower" aria-hidden="true"></i>
          <c:out value="${bundle.getString('rooms')}"/></a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">
          <i class="fa fa-user-circle" aria-hidden="true"></i>
          <c:out value="${bundle.getString('profile')}"/></a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE">
          <i class="fa fa-pencil-alt" aria-hidden="true"></i>
          <c:out value="${bundle.getString('createRequest')}"/></a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">
          <i class="fa fa-book" aria-hidden="true"></i>
          <c:out value="${bundle.getString('requests')}"/></a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACTLOGOUT">
          <i class="fa fa-sign-out-alt" aria-hidden="true"></i>
          <c:out value="${bundle.getString('logOut')}"/></a>
      </li>
    </ul>
  </nav>
  <div class="content">
    <div class="Table">
      <table>
        <colgroup>
          <col span="5">
        </colgroup>
        <caption><c:out value="${bundle.getString('roomsList')}" /></caption>
        <tr>
          <th><b><c:out value="${bundle.getString('roomNumber')}"/></b></th>
          <th><b><c:out value="${bundle.getString('roomStatus')}"/></b></th>
          <th><b><c:out value="${bundle.getString('roomClass')}"/></b></th>
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
  </div>
  <footer class="footer">
    <img src="/resources/images/image1.png" alt="image">
    <p><c:out value="${bundle.getString('author')}"/></p>
  </footer>
</div>
</body>
</html>
