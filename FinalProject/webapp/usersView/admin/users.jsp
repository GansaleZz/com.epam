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
