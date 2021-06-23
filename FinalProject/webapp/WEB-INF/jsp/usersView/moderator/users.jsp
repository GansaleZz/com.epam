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
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Table.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/Footer.css"/> ">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>
<div class="page">
    <nav class="one">
        <ul>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWHOME">
                    <i class="fa fa-home fa-fw" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('home')}"/>
                </a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWROOMS">
                    <i class="fa fa-shower" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('rooms')}"/></a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWPROFILE" >
                    <i class="fa fa-user-circle" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('profile')}"/>
                </a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">
                    <i class="fa fa-book" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('requests')}"/>
                </a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWUSERS">
                    <i class="fa fa-users" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('users')}"/>
                </a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTLOGOUT">
                    <i class="fa fa-sign-out-alt" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('logOut')}"/>
                </a>
            </li>
        </ul>
    </nav>
    <div class="content">
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
    </div>
    <footer class="footer">
        <img src="<c:url value="/resources/images/image1.png"/>" alt="image">
        <p><c:out value="${bundle.getString('author')}"/></p>
    </footer>
</div>
</body>
</html>
