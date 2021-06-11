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
    <link rel='stylesheet' href="<c:url value="/resources/css/immersive-slider.css"/>" type='text/css'>
    <script type="text/javascript" src=<c:url value="http://code.jquery.com/jquery-1.9.1.js"/>></script>
    <script type="text/javascript" src=<c:url value="/resources/js/jquery.immersive-slider.js"/>></script>
</head>
<body>

<c:set var="bundle" value="${sessionScope.bundle}"/>
<div class="page">
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
    <div class="main">
        <div class="page_container">
            <div id="immersive_slider">
                <div class="slide" data-blurred="<c:url value="/resources/images/slide_1_blurred.jpg"/>">
                    <div class="content">
                        <h2><c:out value="${bundle.getString('info1Tittle')}"/></h2>
                        <p><c:out value="${bundle.getString('info1Text')}"/></p>
                    </div>
                    <div class="image">
                        <img src="<c:url value="/resources/images/slide_1.jpg"/>" alt="Slider 1">
                    </div>
                </div>
                <div class="slide" data-blurred="<c:url value="/resources/images/slide_2_blurred.jpg"/>">
                    <div class="content">
                        <h2><c:out value="${bundle.getString('info2Tittle')}"/></h2>
                        <p><c:out value="${bundle.getString('info2Text')}"/></p>
                    </div>
                    <div class="image">
                        <img src="<c:url value="/resources/images/slide_2.jpg"/>" alt="Slider 1">
                    </div>
                </div>
                <div class="slide" data-blurred="<c:url value="/resources/images/slide_3_blurred.jpg"/>">
                    <div class="content">
                        <h2><c:out value="${bundle.getString('info3Tittle')}"/></h2>
                        <p><c:out value="${bundle.getString('info3Text')}"/></p>
                    </div>
                    <div class="image">
                        <img src="<c:url value="/resources/images/slide_3.jpg"/>" alt="Slider 1">
                    </div>
                </div>
                <a href="#" class="is-prev">&laquo;</a>
                <a href="#" class="is-next">&raquo;</a>
            </div>
        </div>
    </div>
    <footer class="footer">
        <img src="/resources/images/image1.png" alt="image">
        <p><c:out value="${bundle.getString('author')}"/></p>
    </footer>
</div>
<script type="text/javascript">
    $(document).ready( function() {
        $("#immersive_slider").immersive_slider({
            container: ".main"
        });
    });
</script>
</body>
</html>
