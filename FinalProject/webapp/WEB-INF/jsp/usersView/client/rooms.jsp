<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 15.05.21
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rooms</title>
  <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
  <link rel="stylesheet" href="<c:url value="/resources/css/Rooms.css"/> ">
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
          <c:out value="${bundle.getString('rooms')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">
          <i class="fa fa-user-circle" aria-hidden="true"></i>
          <c:out value="${bundle.getString('profile')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE">
          <i class="fa fa-pencil-alt" aria-hidden="true"></i>
          <c:out value="${bundle.getString('createRequest')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">
          <i class="fa fa-book" aria-hidden="true"></i>
          <c:out value="${bundle.getString('requests')}"/>
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
    <div class="view view-first">
      <img src="<c:url value="/resources/images/econom.jpg"/>" />
          <div class="mask">
            <h2>
              <c:out value="${bundle.getString('infoEconomTittle')}"/>
            </h2>
            <p>
              <c:out value="${bundle.getString('infoEconomText')}"/>
            </p>
          </div>
    </div>
    <div class="view view-first">
      <img src="<c:url value="/resources/images/business.jpg"/>" />
          <div class="mask">
            <h2>
              <c:out value="${bundle.getString('infoBusinessTittle')}"/>
            </h2>
            <p>
              <c:out value="${bundle.getString('infoBusinessText')}"/>
            </p>
          </div>
    </div>
    <div class="view view-first">
      <img src="<c:url value="/resources/images/luxe.jpg"/>" />
      <div class="mask">
        <h2>
          <c:out value="${bundle.getString('infoLuxeTittle')}"/>
        </h2>
        <p>
          <c:out value="${bundle.getString('infoLuxeText')}"/>
        </p>
      </div>
    </div>
    <div class="view view-first">
      <img src="<c:url value="/resources/images/premium.jpg"/>" />
      <div class="mask">
        <h2>
          <c:out value="${bundle.getString('infoPremiumTittle')}"/>
        </h2>
        <p>
          <c:out value="${bundle.getString('infoPremiumText')}"/>
        </p>
      </div>
    </div>
  </div>
  <footer class="footer">
    <img src="<c:url value="/resources/images/image1.png"/>" alt="image">
    <p>
      <c:out value="${bundle.getString('author')}"/>
    </p>
  </footer>
</div>
</body>
</html>
