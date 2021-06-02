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
    <link rel="stylesheet" href="<c:url value="/resources/css/auth.css"/> ">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<nav class="one">
    <ul>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWHOME"><i class="fa fa-home fa-fw"></i><c:out value="${bundle.getString('home')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWROOMS"><c:out value="${bundle.getString('rooms')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWPROFILE"><c:out value="${bundle.getString('profile')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS"><c:out value="${bundle.getString('requests')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWUSERS"><c:out value="${bundle.getString('users')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=LOGOUT"><c:out value="${bundle.getString('logOut')}"/></a></li>
    </ul>
</nav>

<p>
<table>
    <colgroup>
        <col span="6" style="background: Khaki">
    </colgroup>
    <caption>List of rooms</caption>
    <tr>
        <th><c:out value="${bundle.getString('roomNumber')}"/></th>
        <th><c:out value="${bundle.getString('roomClass')}"/></th>
        <th><c:out value="${bundle.getString('priceDay')}"/></th>
        <th><c:out value="${bundle.getString('numberOfSeats')}"/></th>
        <th><c:out value="${bundle.getString('roomStatus')}"/></th>
        <th><c:out value="${bundle.getString('action')}"/></th>
    </tr>
    <c:forEach var="room" items="${list}">
        <tr>
            <form action="controller?command=ACTUPDATEROOM" method = "post">
                <input type = "hidden" name = "id" value="${room.id}" >
                <td>
                    <input type = "number" name = "roomNumber" value="${room.roomNumber}" min="1">
                </td>
                <td><c:choose>
                    <c:when test="${room.roomClass == 'BUSINESS'}">
                        <select name="class">
                            <option selected = "selected">BUSINESS</option>
                            <option>ECONOM</option>
                            <option>LUXE</option>
                            <option>PREMIUM</option>
                        </select><br>
                    </c:when>
                    <c:when test="${room.roomClass == 'ECONOM'}">
                        <select name="class">
                            <option>BUSINESS</option>
                            <option selected = "selected">ECONOM</option>
                            <option>LUXE</option>
                            <option>PREMIUM</option>
                        </select>
                    </c:when>
                    <c:when test="${room.roomClass == 'LUXE'}">
                        <select name="class">
                            <option>BUSINESS</option>
                            <option>ECONOM</option>
                            <option selected = "selected">LUXE</option>
                            <option>PREMIUM</option>
                        </select>
                    </c:when>
                    <c:when test="${room.roomClass == 'PREMIUM'}">
                        <select name="class">
                            <option>BUSINESS</option>
                            <option>ECONOM</option>
                            <option>LUXE</option>
                            <option selected = "selected">PREMIUM</option>
                        </select>
                    </c:when>
                </c:choose></td>
                <td><input type="number" name="price" value="${room.price}"> BYN</td>
                <td><c:choose>
                    <c:when test="${room.numberOfSeats == 1}">
                        <select name="numberOfSeats">
                            <option selected = "selected">1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </c:when>
                    <c:when test="${room.numberOfSeats == 2}">
                        <select name="numberOfSeats">
                            <option>1</option>
                            <option selected = "selected">2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </c:when>
                    <c:when test="${room.numberOfSeats == 3}">
                        <select name="numberOfSeats">
                            <option>1</option>
                            <option>2</option>
                            <option selected = "selected">3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </c:when>
                    <c:when test="${room.numberOfSeats == 4}">
                        <select name="numberOfSeats">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option selected = "selected">4</option>
                            <option>5</option>
                        </select>
                    </c:when>
                    <c:when test="${room.numberOfSeats == 5}">
                        <select name="numberOfSeats">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option selected = "selected">5</option>
                        </select>
                    </c:when>
                </c:choose></td>
                <td><c:choose>
                    <c:when test="${room.roomStatus == 'AVAILABLE'}">
                        <select name="status">
                            <option selected = "selected">AVAILABLE</option>
                            <option>CLOSED</option>
                        </select>
                    </c:when>
                    <c:when test="${room.roomStatus == 'CLOSED'}">
                        <select name="status">
                            <option>AVAILABLE</option>
                            <option selected = "selected">CLOSED</option>
                        </select>
                    </c:when>
                </c:choose></td>
                <td><input type = "submit" value="Submit" name="Submit"/>
                    <input type = "submit" value="Delete" name ="Submit"/></td>
            </form>
        </tr>
    </c:forEach>
</table>
</p>
    <a href="http://localhost:8080/controller?command=ACTNEWROOMPAGE">Add new room</a>

</body>
</html>
