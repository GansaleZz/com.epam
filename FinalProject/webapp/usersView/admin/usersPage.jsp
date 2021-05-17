<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 15.05.21
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
    <a href="http://localhost:8080/usersView/admin/home/home.jsp">Home</a>
    <a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
    <a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
    <a href="http://localhost:8080/controller?command=ACTCREATEREQUEST">Create request</a>
    <a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a><br>

        <c:forEach var="user" items="${list}">
         <form action="controller?command=ACTCHANGEUSERSRS" method = "post">
            <b>Id: </b><input type = "text" size ="5" name = "id" value="${user.id}" readonly><br>
            <b>Name: </b> <c:out value="${user.name}" /><br>
            <b>Email: </b> <c:out value="${user.email}" /><br>
            <b>Role: </b> <c:choose>
                                <c:when test="${user.userRole == 'ADMIN'}">
                                    <c:out value="${user.userRole}" /><br>
                                </c:when>
                                <c:when test="${user.userRole == 'MODERATOR'}">
                                    <select name="role">
                                        <option selected = "selected">MODERATOR</option>
                                        <option>CLIENT</option>
                                    </select><br>
                                </c:when>
                                <c:when test="${user.userRole == 'CLIENT'}">
                                    <select name="role">
                                        <option>MODERATOR</option>
                                        <option selected = "selected">CLIENT</option>
                                    </select><br>
                                </c:when>
                            </c:choose>
            <b>Status: </b> <c:choose>
             <c:when test="${user.userRole == 'ADMIN'}">
                 <c:out value="${user.status}" /><br>
             </c:when>
             <c:when test="${user.status == 'AVAILABLE' && user.userRole != 'ADMIN'}">
                 <select name="status">
                     <option selected = "selected">AVAILABLE</option>
                     <option>BANNED</option>
                 </select><br>
             </c:when>
             <c:when test="${user.status == 'BANNED' && user.userRole != 'ADMIN'}">
                 <select name="status">
                     <option>AVAILABLE</option>
                     <option selected = "selected">BANNED</option>
                 </select><br>
             </c:when>
         </c:choose>
             <c:if test="${user.userRole != 'ADMIN'}">
                 <input type = "submit" value="Submit" />
             </c:if>
         </form>
        </c:forEach>



</body>
</html>
