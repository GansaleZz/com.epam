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
    <title>Title</title>
</head>
<body>
<a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
<a href="http://localhost:8080/usersView/client/home/home.jsp">Home</a>
<a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
<a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
<a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE">Create request</a>

<c:set var="today" value="${today}" />
<c:set var="maxDay" value="${maxDay}" />
<form action="controller?command=ACTNEWREQUEST" method = "post">
      <b>Start date of arrive: </b><input type="date" name="start" min="${today}" max="${maxDay}" value ="${today}"><br>
      <b>Arrival time (days): </b><input type="number" name="end" min="1" max="30" value="1"><br>
      <b>Number of seats: </b><input type="number" name="numberOfSeats" min="1" max="5" value="1"><br>
      <b>Choose class: </b><select name="class">
          <option selected = "selected">BUSINESS</option>
          <option>ECONOM</option>
          <option>LUXE</option>
          <option>PREMIUM</option>
      </select><br>
      <input type="submit" name="submit" value="Submit">
</form>
</body>
</html>
