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
<a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">Requests</a>
<a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a>

<p>
<table>
    <colgroup>
        <col span="6" style="background: Khaki">
    </colgroup>
    <caption>List of rooms</caption>
    <tr>
        <th>Room numeber</th>
        <th>Class</th>
        <th>Price (for day)</th>
        <th>Number of seats</th>
        <th>Status</th>
        <th>Action</th>
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
