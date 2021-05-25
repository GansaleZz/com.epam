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
    <style>
        table {
            border: 1px solid grey;
            border-collapse: collapse;
            margin: 20px;
        }
        th {
            border: 1px solid grey;
        }
        td {
            border: 1px solid grey;
        }
        caption{
            font-family: annabelle, cursive;
            font-weight: bold;
            font-size: 2em;
            padding: 10px;
            color: #F3CD26;
            text-shadow: 1px 1px 0 rgba(0,0,0,.3);
        }
    </style>
</head>
<body>
<a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a>
<a href="http://localhost:8080/usersView/admin/home/home.jsp">Home</a>
<a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a>
<a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a>
<a href="http://localhost:8080/controller?command=ACTSSHOWREQUESTS">Requests</a>
<a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users</a>

<p>
<table>
    <colgroup>
        <col span="5" style="background: Khaki">
    </colgroup>
    <caption>List of users</caption>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
        <c:forEach var="user" items="${list}">
            <tr>
             <form action="controller?command=ACTCHANGEUSERSRS" method = "post">
                 <input type = "hidden" name = "id" value="${user.id}" >
                 <td> <c:out value="${user.name}" /></td>
                 <td><c:out value="${user.email}" /></td>
                <td><c:choose>
                                    <c:when test="${user.userRole == 'ADMIN'}">
                                        <c:out value="${user.userRole}" />
                                    </c:when>
                                    <c:when test="${user.userRole == 'MODERATOR'}">
                                        <select name="role">
                                            <option selected = "selected">MODERATOR</option>
                                            <option>CLIENT</option>
                                        </select>
                                    </c:when>
                                    <c:when test="${user.userRole == 'CLIENT'}">
                                        <select name="role">
                                            <option>MODERATOR</option>
                                            <option selected = "selected">CLIENT</option>
                                        </select>
                                    </c:when>
                                </c:choose>
                </td>
                <td> <c:choose>
                 <c:when test="${user.userRole == 'ADMIN'}">
                     <c:out value="${user.status}" />
                 </c:when>
                 <c:when test="${user.status == 'AVAILABLE' && user.userRole != 'ADMIN'}">
                     <select name="status">
                         <option selected = "selected">AVAILABLE</option>
                         <option>BANNED</option>
                     </select>
                 </c:when>
                 <c:when test="${user.status == 'BANNED' && user.userRole != 'ADMIN'}">
                     <select name="status">
                         <option>AVAILABLE</option>
                         <option selected = "selected">BANNED</option>
                     </select>
                 </c:when>
             </c:choose>
                </td>
                 <td><c:if test="${user.userRole != 'ADMIN'}">
                     <input type = "submit" value="Submit" />
                 </c:if>
                 </td>
             </form>
            </tr>
        </c:forEach>
</table>
</p>

</body>
</html>
