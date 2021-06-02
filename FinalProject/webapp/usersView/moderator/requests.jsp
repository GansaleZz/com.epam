<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 29.05.21
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Requests</title>
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
    <link rel="stylesheet" href="<c:url value="/resources/css/auth.css"/> ">
</head>
<body>
<nav class="one">
    <ul>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWHOME"><i class="fa fa-home fa-fw"></i>Home</a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">Requests</a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a></li>
        <li><a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a></li>
    </ul>
</nav>

<p>
<table>
    <colgroup>
        <col span="5" style="background: Khaki">
    </colgroup>
    <caption>List of users requests</caption>
    <tr>
        <th>User's name</th>
        <th>Room class</th>
        <th>Number of seats</th>
        <th>Period (days)</th>
        <th>Action</th>
    </tr>
    <c:forEach var="request" items="${list}">
        <tr>
            <td><c:out value="${request.user.name}"/></td>
            <td><c:out value="${request.roomClass}" /></td>
            <td><c:out value="${request.numberOfSeats}"/> </td>
            <fmt:parseNumber var="per" integerOnly="true"
                             type="number" value="${(request.end.time-request.start.time)/ (1000*60*60*24)}" />
            <td><c:out value="${per}" /> </td>
            <td><form action="controller?command=ACTUPDATEREQUEST" method = "post">
                <input type="hidden" name="id" value="${request.id}">
                <input type="submit" name="submit" value="Approve">
                <input type="submit" name="submit" value="Deny">
            </form></td>
        </tr>
    </c:forEach>
</table>
</p>

</body>
</html>
