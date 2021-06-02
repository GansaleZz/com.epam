<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 16.05.21
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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

<form action="controller?command=ACTADDNEWROOM" method = "post" >
    <div class="form-row">
        <b><c:out value="${bundle.getString('roomNumber')}"/> </b><input type="number" name ="roomNumber" min="1" value="100" ><br>
        <b><c:out value="${bundle.getString('numberOfSeats')}"/> </b><input type="number" name="numberOfSeats" min="1" max="5" value="1"><br>
        <b><c:out value="${bundle.getString('roomClass')}"/></b>
        <select name="roomClass">
            <option selected = "selected" value="BUSINESS"><c:out value="${bundle.getString('business')}"/> </option>
            <option value="ECONOM"><c:out value="${bundle.getString('econom')}"/></option>
            <option value="LUXE"><c:out value="${bundle.getString('luxe')}"/> </option>
            <option value="PREMIUM"><c:out value="${bundle.getString('premium')}"/> </option>
        </select><br>
        <b><c:out value="${bundle.getString('price')}"/> </b><input type="number" name="price" min="1" max="10000" value="100" /><br>
        <input type = "submit" value="${bundle.getString('submit')}" />
    </div>
</form>

</body>
</html>
