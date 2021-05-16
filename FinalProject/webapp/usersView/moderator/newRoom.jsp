<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 16.05.21
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
<a href="http://localhost:8080/usersView/moderator/home/home.jsp">Home</a>
<a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
<a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
<a href="http://localhost:8080/controller?command=ACTCREATEREQUEST">Create request</a>
<a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a>

<p><form action="controller?command=ACTADDNEWROOM" method = "post">
    <b>Number of seats: </b><select name="numberOfSeats">
     <option selected = "selected">1</option>
     <option>2</option>
     <option>3</option>
     <option>4</option>
     <option>5</option>
    </select><br>
    <b>Room class: </b><select name="roomClass">
        <option selected = "selected">BUSINESS</option>
        <option>ECONOM</option>
        <option>LUXE</option>
        <option>PREMIUM</option>
    </select><br>
    <b>Price: </b><input type="text" name="price"><br>
    <input type = "submit" value="Submit" />
</form></p>
</body>
</html>
