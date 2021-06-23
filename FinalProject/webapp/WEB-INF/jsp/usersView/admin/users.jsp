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
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Table.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Footer.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>
<div class="page">
    <nav class="one">
        <ul>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWHOME">
                    <i class="fa fa-home fa-fw" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('home')}"/>
                </a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWROOMS">
                    <i class="fa fa-shower" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('rooms')}"/></a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWPROFILE" >
                    <i class="fa fa-user-circle" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('profile')}"/>
                </a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS">
                    <i class="fa fa-book" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('requests')}"/>
                </a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTSHOWUSERS">
                    <i class="fa fa-users" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('users')}"/>
                </a>
            </li>
            <li>
                <a href="http://localhost:8080/controller?command=ACTLOGOUT">
                    <i class="fa fa-sign-out-alt" aria-hidden="true"></i>
                    <c:out value="${bundle.getString('logOut')}"/>
                </a>
            </li>
        </ul>
    </nav>
    <div class="content">
        <div class="Table">
            <table>
                <colgroup>
                    <col span="5">
                </colgroup>
                <caption><c:out value="${bundle.getString('usersList')}"/></caption>
                <tr>
                    <th><b><c:out value="${bundle.getString('name')}"/></b></th>
                    <th><b><c:out value="${bundle.getString('email')}"/></b></th>
                    <th><b><c:out value="${bundle.getString('role')}"/></b></th>
                    <th><b><c:out value="${bundle.getString('status')}"/></b></th>
                    <th><b><c:out value="${bundle.getString('action')}"/></b></th>
                </tr>
                    <c:forEach var="user" items="${list}">
                        <tr>
                         <form action="controller?command=ACTCHANGEUSERSRS" method = "post">
                             <input type = "hidden" name = "id" value="${user.id}" >
                             <td> <c:out value="${user.name}" /></td>
                             <td><c:out value="${user.email}" /></td>
                            <td><c:choose>
                                                <c:when test="${user.userRole == 'ADMIN'}">
                                                    <c:out value="${bundle.getString('admin')}"/>
                                                </c:when>
                                                <c:when test="${user.userRole == 'MODERATOR'}">
                                                    <select name="role">
                                                        <option selected = "selected" value="MODERATOR"><c:out value="${bundle.getString('moderator')}"/></option>
                                                        <option value="CLIENT"><c:out value="${bundle.getString('client')}"/> </option>
                                                    </select>
                                                </c:when>
                                                <c:when test="${user.userRole == 'CLIENT'}">
                                                    <select name="role">
                                                        <option value="MODERATOR"><c:out value="${bundle.getString('moderator')}"/></option>
                                                        <option selected = "selected" value="CLIENT"><c:out value="${bundle.getString('client')}"/></option>
                                                    </select>
                                                </c:when>
                                            </c:choose>
                            </td>
                            <td> <c:choose>
                             <c:when test="${user.userRole == 'ADMIN'}">
                                 <c:choose>
                                     <c:when test="${user.status == 'AVAILABLE'}">
                                         <c:out value="${bundle.getString('user.available')}"/>
                                     </c:when>
                                     <c:otherwise>
                                         <c:out value="${bundle.getString('user.banned')}"/>
                                     </c:otherwise>
                                 </c:choose>
                             </c:when>
                             <c:when test="${user.status == 'AVAILABLE' && user.userRole != 'ADMIN'}">
                                 <select name="status">
                                     <option selected = "selected" value="AVAILABLE"><c:out value="${bundle.getString('user.available')}"/></option>
                                     <option value="BANNED"><c:out value="${bundle.getString('user.banned')}"/></option>
                                 </select>
                             </c:when>
                             <c:when test="${user.status == 'BANNED' && user.userRole != 'ADMIN'}">
                                 <select name="status">
                                     <option value="AVAILABLE"><c:out value="${bundle.getString('user.available')}"/></option>
                                     <option selected = "selected" value="BANNED"><c:out value="${bundle.getString('user.banned')}"/></option>
                                 </select>
                             </c:when>
                         </c:choose>
                            </td>
                             <td><c:if test="${user.userRole != 'ADMIN'}">
                                 <input class="button" type="submit" value="${bundle.getString('submit')}" />
                             </c:if>
                             </td>
                         </form>
                        </tr>
                    </c:forEach>
            </table>
        </div>
    </div>
    <footer class="footer">
        <img src="<c:url value="/resources/images/image1.png"/>" alt="image">
        <p><c:out value="${bundle.getString('author')}"/></p>
    </footer>
</div>
</body>
</html>
