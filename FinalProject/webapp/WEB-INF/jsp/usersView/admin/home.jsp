<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 25.04.21
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Footer.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
</head>
<body>
<div class="page">
    <c:set var="bundle" value="${sessionScope.bundle}"/>
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
        <h1> You're welcome !</h1>
    </div>

    <footer class="footer">
        <img src="/resources/images/image1.png" alt="image">
        <p><c:out value="${bundle.getString('author')}"/></p>
    </footer>
</div>
</body>
</html>
