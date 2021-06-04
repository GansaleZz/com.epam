<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 15.05.21
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
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

<div class="Table">
    <table>
        <colgroup>
            <col span="4">
        </colgroup>
        <caption><c:out value="${bundle.getString('usersList')}"/></caption>
        <tr>
            <th><b><c:out value="${bundle.getString('name')}"/></b></th>
            <th><b><c:out value="${bundle.getString('email')}"/></b></th>
            <th><b><c:out value="${bundle.getString('role')}"/></b></th>
            <th><b><c:out value="${bundle.getString('status')}"/></b></th>
        </tr>
        <c:forEach var="user" items="${list}">
        <tr>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td>
                <c:choose>
                    <c:when test="${user.userRole == 'ADMIN'}">
                        <c:out value="${bundle.getString('admin')}"/>
                    </c:when>
                    <c:when test="${user.userRole == 'MODERATOR'}">
                        <c:out value="${bundle.getString('moderator')}"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${bundle.getString('client')}"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.status == 'BANNED'}">
                        <c:out value="${bundle.getString('user.banned')}"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${bundle.getString('user.available')}"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
