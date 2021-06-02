<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 30.05.21
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Approve</title>
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
<c:set var="bundle" value="${sessionScope.bundle}"/>

<nav class="one">
    <ul>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWHOME"><i class="fa fa-home fa-fw"></i><c:out value="${bundle.getString('home')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWROOMS"><c:out value="${bundle.getString('rooms')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWPROFILE"><c:out value="${bundle.getString('profile')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS"><c:out value="${bundle.getString('requests')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWUSERS"><c:out value="${bundle.getString('users')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=LOGOUT"><c:out value="${bundle.getString('LogOut')}"/></a></li>
    </ul>
</nav>

<p>
<table>
    <colgroup>
        <col span="1" style="background: Khaki">
    </colgroup>
    <caption>Choose room number</caption>
    <tr>
        <th>Room number for client</th>
    </tr>
        <tr>
            <form action="controller?command=ACTUPDATEREQUEST" method = "post">
            <td>
                <c:choose>
                    <c:when test="${not empty rooms}">
                        <select name = "room">
                            <c:forEach var="i" items="${rooms}">
                                <option>${i.roomNumber}</option>
                                <c:set var="rmId" value="${i.id}"/>
                            </c:forEach>
                        </select>
                        <input type="hidden" value="${rmId}" name="roomId"/>
                        <input type="hidden" value="${id}" name="reqId"/>
                        <input type="submit" name="submit" value="Accept">
                    </c:when>
                    <c:otherwise>
                        <c:out value="There is no suitable rooms"/>
                        <input type="hidden" value="${id}" name="reqId"/>
                        <input type="submit" name="submit" value="Deny">
                    </c:otherwise>
                </c:choose>
            </td>
            </form>
        </tr>
</table>
</p>

</body>
</html>
