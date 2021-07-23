<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 23.07.21
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rooms list</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Rooms.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Footer.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Link.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/Table.css"/> ">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<div class="page">
  <nav class="one">
    <ul>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_HOME">
          <i class="fa fa-home fa-fw" aria-hidden="true"></i>
          <c:out value="${bundle.getString('home')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_ROOMS">
          <i class="fa fa-shower" aria-hidden="true"></i>
          <c:out value="${bundle.getString('rooms')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_PROFILE">
          <i class="fa fa-user-circle" aria-hidden="true"></i>
          <c:out value="${bundle.getString('profile')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_CREATE_REQUEST_PAGE">
          <i class="fa fa-pencil-alt" aria-hidden="true"></i>
          <c:out value="${bundle.getString('createRequest')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_REQUESTS">
          <i class="fa fa-book" aria-hidden="true"></i>
          <c:out value="${bundle.getString('requests')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_LOGOUT">
          <i class="fa fa-sign-out-alt" aria-hidden="true"></i>
          <c:out value="${bundle.getString('logOut')}"/>
        </a>
      </li>
    </ul>
  </nav>
  <a href="http://localhost:8080/controller?command=ACT_SHOW_ROOMS" style="left:0; margin-bottom: 11px; width: 135px;" class="s2"><c:out value="${bundle.getString('roomTypes')}"/></a>
  <div class="content">
    <div class="Table">
      <table>
        <colgroup>
          <col span="5">
        </colgroup>
        <caption>
          <c:out value="${bundle.getString('roomsList')}"/>
        </caption>
        <tr>
          <th>
            <b>
              <c:out value="${bundle.getString('roomNumber')}"/>
            </b>
          </th>
          <th>
            <b>
              <c:out value="${bundle.getString('roomClass')}"/>
            </b>
          </th>
          <th>
            <b>
              <c:out value="${bundle.getString('priceDay')}"/>
            </b>
          </th>
          <th>
            <b>
              <c:out value="${bundle.getString('numberOfSeats')}"/>
            </b>
          </th>
          <th>
            <b>
              <c:out value="${bundle.getString('roomStatus')}"/>
            </b>
          </th>
        </tr>
        <c:forEach var="room" items="${list}">
          <tr>
              <td>
                <c:out value="${room.roomNumber}"/>
              </td>
              <td>
                <c:choose>
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
                </c:choose>
              </td>
              <td>
                <c:out value="${room.price}"/>
              </td>
              <td>
                <c:out value="${room.numberOfSeats}"/>
              </td>
              <td>
                <c:choose>
                  <c:when test="${room.roomStatus == 'AVAILABLE'}">
                    <c:out value="${bundle.getString('roomStatus.available')}"/>
                  </c:when>
                  <c:when test="${room.roomStatus == 'CLOSED'}">
                    <c:out value="${bundle.getString('roomStatus.closed')}"/>
                  </c:when>
                </c:choose>
              </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
  <footer class="footer">
    <img src="<c:url value="/resources/images/image1.png"/>" alt="image">
    <p>
      <c:out value="${bundle.getString('author')}"/>
    </p>
  </footer>
</div>
</body>
</html>
