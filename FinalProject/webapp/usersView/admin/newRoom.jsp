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
<a href="http://localhost:8080/usersView/admin/home/home.jsp">Home</a>
<a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
<a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
<a href="http://localhost:8080/controller?command=ACTSSHOWREQUESTS">Requests</a>
<a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a>

<p><form action="controller?command=ACTADDNEWROOM" method = "post">
    <b>Room number: </b><input type="number" name ="roomNumber" min="1" value="100"><br>
    <b>Number of seats: </b><input type="number" name="numberOfSeats" min="1" max="5" value="1">
    </select><br>
    <b>Room class: </b><select name="roomClass">
        <option selected = "selected">BUSINESS</option>
        <option>ECONOM</option>
        <option>LUXE</option>
        <option>PREMIUM</option>
    </select><br>
    <b>Price: </b><input type="number" name="price" min="1" max="10000" value="100"><br>
    <input type = "submit" value="Submit" /></p>
</form></p>
</body>
</html>
