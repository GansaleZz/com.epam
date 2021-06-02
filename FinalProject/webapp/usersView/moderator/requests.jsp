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
