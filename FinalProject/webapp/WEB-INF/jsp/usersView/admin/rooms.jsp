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
    <title>Rooms</title>
  <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/Table.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/Link.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/Footer.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
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
        <a href="http://localhost:8080/controller?command=ACT_SHOW_PROFILE" >
          <i class="fa fa-user-circle" aria-hidden="true"></i>
          <c:out value="${bundle.getString('profile')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_REQUESTS">
          <i class="fa fa-book" aria-hidden="true"></i>
          <c:out value="${bundle.getString('requests')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_USERS">
          <i class="fa fa-users" aria-hidden="true"></i>
          <c:out value="${bundle.getString('users')}"/>
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
  <div class="content">
    <div class="Table">
      <table>
        <colgroup>
          <col span="6">
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
          <th>
            <b>
              <c:out value="${bundle.getString('action')}"/>
            </b>
          </th>
        </tr>
      <c:forEach var="room" items="${list}">
        <tr>
        <form action="controller?command=ACT_UPDATE_ROOM" method = "post">
          <input type = "hidden" name = "id" value="${room.id}" >
          <td>
            <input type = "number" name = "roomNumber" value="${room.roomNumber}" min="1">
          </td>
          <td>
            <c:choose>
              <c:when test="${room.roomClass == 'BUSINESS'}">
                <select name="class">
                  <option selected = "selected" value="BUSINESS">
                    <c:out value="${bundle.getString('business')}"/>
                  </option>
                  <option value="ECONOM">
                    <c:out value="${bundle.getString('econom')}"/>
                  </option>
                  <option value="LUXE">
                    <c:out value="${bundle.getString('luxe')}"/>
                  </option>
                  <option value="PREMIUM">
                    <c:out value="${bundle.getString('premium')}"/>
                  </option>
                </select><br>
              </c:when>
              <c:when test="${room.roomClass == 'ECONOM'}">
                <select name="class">
                  <option value="BUSINESS">
                    <c:out value="${bundle.getString('business')}"/>
                  </option>
                  <option selected = "selected" value="ECONOM">
                    <c:out value="${bundle.getString('econom')}"/>
                  </option>
                  <option value="LUXE">
                    <c:out value="${bundle.getString('luxe')}"/>
                  </option>
                  <option value="PREMIUM">
                    <c:out value="${bundle.getString('premium')}"/>
                  </option>
                </select>
              </c:when>
              <c:when test="${room.roomClass == 'LUXE'}">
                <select name="class">
                  <option value="BUSINESS">
                    <c:out value="${bundle.getString('business')}"/>
                  </option>
                  <option value="ECONOM">
                    <c:out value="${bundle.getString('econom')}"/>
                  </option>
                  <option selected = "selected" value="LUXE">
                    <c:out value="${bundle.getString('luxe')}"/>
                  </option>
                  <option value="PREMIUM">
                    <c:out value="${bundle.getString('premium')}"/>
                  </option>
                </select>
              </c:when>
              <c:when test="${room.roomClass == 'PREMIUM'}">
                <select name="class">
                  <option value="BUSINESS">
                    <c:out value="${bundle.getString('business')}"/>
                  </option>
                  <option value="ECONOM">
                    <c:out value="${bundle.getString('econom')}"/>
                  </option>
                  <option value="LUXE">
                    <c:out value="${bundle.getString('luxe')}"/>
                  </option>
                  <option selected = "selected" value="PREMIUM">
                    <c:out value="${bundle.getString('premium')}"/>
                  </option>
                </select>
              </c:when>
            </c:choose>
          </td>
          <td>
            <input type="number" name="price" value="${room.price}">
          </td>
          <td>
            <c:choose>
              <c:when test="${room.numberOfSeats == 1}">
                <select name="numberOfSeats">
                  <option selected = "selected">
                    1
                  </option>
                  <option>
                    2
                  </option>
                  <option>
                    3
                  </option>
                  <option>
                    4
                  </option>
                  <option>
                    5
                  </option>
                </select>
              </c:when>
              <c:when test="${room.numberOfSeats == 2}">
                <select name="numberOfSeats">
                  <option>
                    1
                  </option>
                  <option selected = "selected">
                    2
                  </option>
                  <option>
                    3
                  </option>
                  <option>
                    4
                  </option>
                  <option>
                    5
                  </option>
                </select>
              </c:when>
              <c:when test="${room.numberOfSeats == 3}">
                <select name="numberOfSeats">
                  <option>
                    1
                  </option>
                  <option>
                    2
                  </option>
                  <option selected = "selected">
                    3
                  </option>
                  <option>
                    4
                  </option>
                  <option>
                    5
                  </option>
                </select>
              </c:when>
              <c:when test="${room.numberOfSeats == 4}">
                <select name="numberOfSeats">
                  <option>
                    1
                  </option>
                  <option>
                    2
                  </option>
                  <option>
                    3
                  </option>
                  <option selected = "selected">
                    4
                  </option>
                  <option>
                    5
                  </option>
                </select>
              </c:when>
              <c:when test="${room.numberOfSeats == 5}">
                <select name="numberOfSeats">
                  <option>
                    1
                  </option>
                  <option>
                    2
                  </option>
                  <option>
                    3
                  </option>
                  <option>
                    4
                  </option>
                  <option selected = "selected">
                    5
                  </option>
                </select>
              </c:when>
            </c:choose>
          </td>
          <td>
            <c:choose>
              <c:when test="${room.roomStatus == 'AVAILABLE'}">
                <select name="status">
                  <option selected = "selected" value="AVAILABLE">
                    <c:out value="${bundle.getString('roomStatus.available')}"/>
                  </option>
                  <option value="CLOSED">
                    <c:out value="${bundle.getString('roomStatus.closed')}"/>
                  </option>
                </select>
              </c:when>
              <c:when test="${room.roomStatus == 'CLOSED'}">
                <select name="status">
                  <option value="AVAILABLE">
                    <c:out value="${bundle.getString('roomStatus.available')}"/>
                  </option>
                  <option selected = "selected" value="CLOSED">
                    <c:out value="${bundle.getString('roomStatus.closed')}"/>
                  </option>
                </select>
              </c:when>
            </c:choose>
          </td>
          <td>
            <input class="button" type="submit" value="${bundle.getString('submit')}" name="submit"/>
            <input class="button" type="submit" value="${bundle.getString('delete')}" name ="submit"/>
          </td>
        </form>
        </tr>
      </c:forEach>
      </table>
    </div>
      <a href="http://localhost:8080/controller?command=ACT_NEW_ROOM_PAGE" class = "s2">
        <c:out value="${bundle.getString('addNewRoom')}"/>
      </a>
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
