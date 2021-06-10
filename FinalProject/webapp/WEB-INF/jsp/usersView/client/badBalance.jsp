<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 31.05.21
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bad balance</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Form.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<nav class="one">
    <ul>
        <li>
            <a href="http://localhost:8080/controller?command=ACTSHOWHOME">
                <i class="fa fa-home fa-fw" aria-hidden="true"></i>
                <c:out value="${bundle.getString('home')}"/></a>
        </li>
        <li>
            <a href="http://localhost:8080/controller?command=ACTSHOWROOMS">
                <i class="fa fa-shower" aria-hidden="true"></i>
                <c:out value="${bundle.getString('rooms')}"/></a>
        </li>
        <li>
            <a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">
                <i class="fa fa-user-circle" aria-hidden="true"></i>
                <c:out value="${bundle.getString('profile')}"/></a>
        </li>
        <li>
            <a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE">
                <i class="fa fa-pencil-alt" aria-hidden="true"></i>
                <c:out value="${bundle.getString('createRequest')}"/></a>
        </li>
        <li>
            <a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">
                <i class="fa fa-book" aria-hidden="true"></i>
                <c:out value="${bundle.getString('requests')}"/></a>
        </li>
        <li>
            <a href="http://localhost:8080/controller?command=ACTLOGOUT">
                <i class="fa fa-sign-out-alt" aria-hidden="true"></i>
                <c:out value="${bundle.getString('logOut')}"/></a>
        </li>
    </ul>
</nav>
<h1><c:out value="${bundle.getString('badBalance')}"/></h1>
    <form action="/controller?command=ACTNEWDEPOSITPAGE" method = "post">
        <div class="form-row">
            <input type="submit" value="${bundle.getString('deposit')}">
        </div>
    </form>
</body>
</html>
