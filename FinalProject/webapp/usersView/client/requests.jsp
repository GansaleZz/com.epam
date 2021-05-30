<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 19.05.21
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
<a href="http://localhost:8080/usersView/client/home/home.jsp">Home</a>
<a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
<a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
<a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE">Create request</a>
<a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">Requests</a>

<p>
<table>
    <colgroup>
        <col span="6" style="background: Khaki">
    </colgroup>
    <caption>List of your requests</caption>
    <tr>
        <th>Room number</th>
        <th>Room class</th>
        <th>Number of seats</th>
        <th>Price for the whole period</th>
        <th>Payment</th>
        <th>Request status</th>
    </tr>
    <c:forEach var="request" items="${list}">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${request.requestStatus == 'INPROGRESS'}">
                        <c:out value="INPROGRESS"/>
                    </c:when>
                    <c:when test="${request.requestStatus == 'DENIED'}">
                       <c:out value="DENIED"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${request.room.roomNumber}"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td><c:choose>
                <c:when test="${request.requestStatus == 'INPROGRESS' || request.requestStatus == 'DENIED'}">
                    <c:out value="${request.roomClass}"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${request.room.roomClass}"/>
                </c:otherwise>
            </c:choose></td>
            <td><c:out value="${request.numberOfSeats}"/> </td>
            <td><c:choose>
                <c:when test="${request.requestStatus == 'INPROGRESS'}">
                    <c:out value="INPROGRESS"/>
                </c:when>
                <c:when test="${request.requestStatus == 'DENIED'}">
                    <c:out value="DENIED"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${(request.end.time-request.start.time)*request.room.price/ (1000*60*60*24)}"/> BYN</td>
                </c:otherwise>
            </c:choose>
            <td><c:choose>
                <c:when test="${request.requestStatus == 'INPROGRESS'}">
                    <c:out value="INPROGRESS"/>
                </c:when>
                <c:when test="${request.requestStatus == 'DENIED'}">
                    <c:out value="DENIED"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${request.payment.status}"/>
                </c:otherwise>
            </c:choose></td>
            <td><c:out value="${request.requestStatus}"/></td>
        </tr>
    </c:forEach>
</table>
</p>

</body>
</html>
