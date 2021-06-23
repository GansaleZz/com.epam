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
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Form.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Footer.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
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
        <form action="controller?command=ACTUPDATEREQUEST" method = "post">
            <div class="form-row">
                <c:choose>
                    <c:when test="${not empty rooms}">
                        <b>
                            <c:out value="${bundle.getString('roomNumber')}"/>
                        </b>
                        <select name = "roomId">
                            <c:forEach var="i" items="${rooms}">
                                <option value="${i.id}">${i.roomNumber}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" value="${id}" name="reqId"/>
                        <input type="submit" name="submit" value="<c:out value="${bundle.getString('approve')}"/> ">
                    </c:when>
                    <c:otherwise>
                        <h1>
                            <c:out value="${bundle.getString('noRooms')}"/>
                        </h1>
                        <input type="hidden" value="${id}" name="reqId"/>
                        <input type="submit" name="submit" value="<c:out value="${bundle.getString('deny')}"/>">
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>
    <footer class="footer">
        <img src="<c:url value="/resources/images/image1.png"/>" alt="image">
        <p><c:out value="${bundle.getString('author')}"/></p>
    </footer>
</div>
</body>
</html>
