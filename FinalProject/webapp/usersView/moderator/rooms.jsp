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
</head>
<body>
<a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
<a href="http://localhost:8080/usersView/moderator/home/home.jsp">Home</a>
<a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
<a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
<a href="http://localhost:8080/controller?command=ACTSSHOWREQUESTS">Requests</a>
<a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a>

    <c:forEach var="room" items="${list}">
        <form action="controller?command=ACTUPDATEROOM" method = "post">
            <b>Id: </b><input type = "number" size ="5" name = "id" value="${room.id}" readonly><br>
            <b>Class: </b> <c:choose>
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
                </select><br>
            </c:when>
            <c:when test="${room.roomClass == 'LUXE'}">
                <select name="class">
                    <option>BUSINESS</option>
                    <option>ECONOM</option>
                    <option selected = "selected">LUXE</option>
                    <option>PREMIUM</option>
                </select><br>
            </c:when>
            <c:when test="${room.roomClass == 'PREMIUM'}">
                <select name="class">
                    <option>BUSINESS</option>
                    <option>ECONOM</option>
                    <option>LUXE</option>
                    <option selected = "selected">PREMIUM</option>
                </select><br>
            </c:when>
        </c:choose>
            <b>Price: </b><input type="text" name="price" value="${room.price}"> BYN for day.<br>
            <b>Number of seats: </b><c:choose>
            <c:when test="${room.numberOfSeats == 1}">
                <select name="numberOfSeats">
                    <option selected = "selected">1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select><br>
            </c:when>
            <c:when test="${room.numberOfSeats == 2}">
                <select name="numberOfSeats">
                    <option>1</option>
                    <option selected = "selected">2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select><br>
            </c:when>
            <c:when test="${room.numberOfSeats == 3}">
                <select name="numberOfSeats">
                    <option>1</option>
                    <option>2</option>
                    <option selected = "selected">3</option>
                    <option>4</option>
                    <option>5</option>
                </select><br>
            </c:when>
            <c:when test="${room.numberOfSeats == 4}">
                <select name="numberOfSeats">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option selected = "selected">4</option>
                    <option>5</option>
                </select><br>
            </c:when>
            <c:when test="${room.numberOfSeats == 5}">
                <select name="numberOfSeats">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option selected = "selected">5</option>
                </select><br>
            </c:when>
        </c:choose>
            <b>Status: </b><c:choose>
            <c:when test="${room.roomStatus == 'AVAILABLE'}">
                <select name="status">
                    <option selected = "selected">AVAILABLE</option>
                    <option>ENGAGED</option>
                </select><br>
            </c:when>
            <c:when test="${room.roomStatus == 'ENGAGED'}">
                <select name="status">
                    <option>AVAILABLE</option>
                    <option selected = "selected">ENGAGED</option>
                </select><br>
            </c:when>
        </c:choose>
            <input type = "submit" value="Submit" name="Submit"/>
            <input type = "submit" value="Delete" name ="Submit"/>
        </form>
    </c:forEach>
    <a href="http://localhost:8080/controller?command=ACTNEWROOMPAGE">Add new room</a>

</body>
</html>
