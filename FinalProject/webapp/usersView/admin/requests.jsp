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
        <col span="7" style="background: Khaki">
    </colgroup>
    <caption><c:out value="${bundle.getString('usersRequestsList')}"/></caption>
    <tr>
        <th><c:out value="${bundle.getString('userName')}"/></th>
        <th><c:out value="${bundle.getString('roomClass')}"/></th>
        <th><c:out value="${bundle.getString('numberOfSeats')}"/></th>
        <th><c:out value="${bundle.getString('startDate')}"/></th>
        <th><c:out value="${bundle.getString('endDate')}"/></th>
        <th><c:out value="${bundle.getString('arrivalTime')}"/></th>
        <th><c:out value="${bundle.getString('requestStatus')}"/></th>
    </tr>
    <c:forEach var="request" items="${list}">
    <tr>
        <td><c:out value="${request.user.name}"/></td>
        <td>
            <c:choose>
                <c:when test="${not empty request.room}">
                    <c:choose>
                        <c:when test="${request.room.roomClass == 'BUSINESS'}">
                            <c:out value="${bundle.getString('business')}"/>
                        </c:when>
                        <c:when test="${request.room.roomClass == 'ECONOM'}">
                            <c:out value="${bundle.getString('econom')}"/>
                        </c:when>
                        <c:when test="${request.room.roomClass == 'LUXE'}">
                            <c:out value="${bundle.getString('luxe')}"/>
                        </c:when>
                        <c:when test="${request.room.roomClass == 'PREMIUM'}">
                            <c:out value="${bundle.getString('premium')}"/>
                        </c:when>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${request.roomClass == 'BUSINESS'}">
                            <c:out value="${bundle.getString('business')}"/>
                        </c:when>
                        <c:when test="${request.roomClass == 'ECONOM'}">
                            <c:out value="${bundle.getString('econom')}"/>
                        </c:when>
                        <c:when test="${request.roomClass == 'LUXE'}">
                            <c:out value="${bundle.getString('luxe')}"/>
                        </c:when>
                        <c:when test="${request.roomClass == 'PREMIUM'}">
                            <c:out value="${bundle.getString('premium')}"/>
                        </c:when>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </td>
        <td><c:out value="${request.numberOfSeats}"/> </td>
        <td><c:out value="${request.start}"/></td>
        <td><c:out value="${request.end}"/> </td>
        <fmt:parseNumber var="per" integerOnly="true"
                         type="number" value="${(request.end.time-request.start.time)/ (1000*60*60*24)}" />
        <td><c:out value="${per}" /> </td>
        <td><c:choose>
            <c:when test="${request.requestStatus == 'INPROGRESS'}">
                <form action="controller?command=ACTUPDATEREQUEST" method = "post">
                    <input type="hidden" name="id" value="${request.id}">
                    <input type="submit" name="submit" value="<c:out value="${bundle.getString('approve')}"/>">
                    <input type="submit" name="submit" value="<c:out value="${bundle.getString('deny')}"/>">
                </form>
            </c:when>
            <c:when test="${request.requestStatus == 'PAID'}">
                <c:out value="${bundle.getString('requestStatus.paid')}"/>
            </c:when>
            <c:when test="${request.requestStatus == 'CANCELLED'}">
                <c:out value="${bundle.getString('requestStatus.cancelled')}"/>
            </c:when>
            <c:when test="${request.requestStatus == 'DENIED'}">
                <c:out value="${bundle.getString('requestStatus.denied')}"/>
            </c:when>
        </c:choose></td>
    </tr>
    </c:forEach>
</table>
</p>

</body>
</html>
