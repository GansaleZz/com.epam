<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 31.05.21
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
<a href="http://localhost:8080/controller?command=ACTSHOWHOME">Home</a>
<a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
<a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
<a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE">Create request</a>
<a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">Requests</a>
<p>
    Your account has insufficient funds.
    <form action="controller?command=ACTNEWDEPOSITPAGE" method = "post">
        <input type="submit" value="Deposit">
    </form>
</p>
</body>
</html>
