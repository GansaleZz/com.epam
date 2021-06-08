<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 29.05.21
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Deposit</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Form.css"/> ">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<nav class="one">
    <ul>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWHOME"><i class="fa fa-home fa-fw"></i><c:out value="${bundle.getString('home')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWROOMS"><c:out value="${bundle.getString('rooms')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWPROFILE"><c:out value="${bundle.getString('profile')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE"><c:out value="${bundle.getString('createRequest')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS"><c:out value="${bundle.getString('requests')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTLOGOUT"><c:out value="${bundle.getString('logOut')}"/></a></li>
    </ul>
</nav>


<form action="controller?command=ACTREALISEDEPOSITE" method = "post">
    <div class="form-row">
        <b><c:out value="${bundle.getString('bankCard')}"/></b><input type="number" name="card" min="1111111111111111" max="9999999999999999" maxlength="16" minlength="16"><br>
        <b><c:out value="${bundle.getString('amount')}"/> (BYN)</b><input type="number" name="balance" min="50" max="10000"value="50" ><br>
        <input type="submit" name="submit" value="Submit">
    </div>
</form>
</body>
</html>
