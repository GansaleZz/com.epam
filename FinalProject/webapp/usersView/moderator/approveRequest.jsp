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

<form action="controller?command=ACTUPDATEREQUEST" method = "post">
    <div class="form-row">
        <b><c:out value="${bundle.getString('roomNumber')}"/> </b>
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
                <input type="submit" name="submit" value="<c:out value="${bundle.getString('approve')}"/> ">
            </c:when>
            <c:otherwise>
                <c:out value="There is no suitable rooms"/>
                <input type="hidden" value="${id}" name="reqId"/>
                <input type="submit" name="submit" value="<c:out value="${bundle.getString('deny')}"/>">
            </c:otherwise>
        </c:choose>
    </div>
</form>

</body>
</html>
