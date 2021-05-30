<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 29.05.21
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deposit</title>
</head>
<body>
<a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
<a href="http://localhost:8080/usersView/client/home/home.jsp">Home</a>
<a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
<a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
<a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE">Create request</a>
<a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">Requests</a>

<p>
<form action="controller?command=ACTREALISEDEPOSITE" method = "post">
    <b>Bank card:</b><input type="number" name="card" min="1111111111111111" max="9999999999999999" maxlength="16" minlength="16"><br>
    <b>Amount: </b><input type="number" name="balance" min="50" max="10000"value="50" > BYN<br>
    <input type="submit" name="submit" value="Submit">
</form>
</p>
</body>
</html>
