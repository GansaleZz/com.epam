<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 19.05.21
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New request</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Form.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Footer.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<script>
    function btnClick() {
        var requestsCount = ${requestsCount};
        if (requestsCount == 5){
            alert("${bundle.getString('maxRequests')}");
            return false;
        }else{
            return true;
        }
    }
</script>

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

    <c:set var="today" value="${today}" />
    <c:set var="maxDay" value="${maxDay}" />
    <div class="content">
        <form action="controller?command=ACTNEWREQUEST" method = "post">
            <div class="form-row">
              <b>
                  <c:out value="${bundle.getString('startArriveDate')}"/>
              </b>
                <input type="date" name="start" min="${today}" max="${maxDay}" value ="${today}"><br>
              <b>
                  <c:out value="${bundle.getString('arrivalTime')}"/>
              </b>
                <input type="number" name="end" min="1" max="30" value="1"><br>
              <b>
                  <c:out value="${bundle.getString('numberOfSeats')}"/>
              </b>
                <input type="number" name="numberOfSeats" min="1" max="5" value="1"><br>
              <b>
                  <c:out value="${bundle.getString('roomClass')}"/>
              </b>
              <select name="class">
                  <option selected = "selected" value="BUSINESS">
                      <c:out value="${bundle.getString('business')}"/>
                  </option>
                  <option value="ECONOM">
                      <c:out value="${bundle.getString('econom')}"/>
                  </option>
                  <option value="LUXE">
                      <c:out value="${bundle.getString('luxe')}"/>
                  </option>
                  <option value="PREMIUM">
                      <c:out value="${bundle.getString('premium')}"/>
                  </option>
              </select><br>
              <input type="submit" name="submit" value="${bundle.getString('submit')}" onclick='return btnClick();'>
            </div>
        </form>
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
