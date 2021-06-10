<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 15.05.21
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Table.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

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
        </div>
    </ul>
</nav>

<c:set var="user" value="${user}" />
<div class="Table">
    <table>
        <colgroup>
            <col span="2" >
        </colgroup>
        <caption><c:out value="${bundle.getString('profile')}"/></caption>
        <form action="controller?command=ACTUPDATEPROFILE" method = "post">
            <input type = "hidden" name = "id" value="${user.id}" >
            <tr>
                <th><b><c:out value="${bundle.getString('login')}"/></b></th>
                <td><c:out value="${user.login}"/></td>
            </tr>
            <tr>
                <th><b><c:out value="${bundle.getString('name')}"/></b></th>
                <td><input type="text" name="name" value="${user.name}"></td>
            </tr>
            <tr>
                <th><b><c:out value="${bundle.getString('email')}"/></b></th>
                <td><input type="email" name="email" value="${user.email}"></td>
            </tr>
            <tr>
                <th><b><c:out value="${bundle.getString('role')}"/></b></th>
                <td>
                    <c:out value="${bundle.getString('moderator')}"/>
                </td>
                </td>
            </tr>
            <tr>
                <th><b><c:out value="${bundle.getString('language')}"/></b></th>
                <td>
                    <select name ="locale">
                        <c:choose>
                            <c:when test="${locale == 'en'}">
                                <option selected ="selected" value="en"> <c:out value="${bundle.getString('english')}"/></option>
                                <option value="ru"><c:out value="${bundle.getString('russian')}"/></option>
                                <option value="by"><c:out value="${bundle.getString('belarusian')}"/></option>
                            </c:when>
                            <c:when test="${locale == 'ru'}">
                                <option value="en"> <c:out value="${bundle.getString('english')}"/></option>
                                <option selected ="selected" value="ru"><c:out value="${bundle.getString('russian')}"/></option>
                                <option value="by"><c:out value="${bundle.getString('belarusian')}"/></option>
                            </c:when>
                            <c:otherwise>
                                <option value="en"> <c:out value="${bundle.getString('english')}"/></option>
                                <option value="ru" ><c:out value="${bundle.getString('russian')}"/></option>
                                <option selected ="selected" value="by"><c:out value="${bundle.getString('belarusian')}"/></option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </td>
            </tr>
            <tr>
                <th><b><c:out value="${bundle.getString('action')}"/></b></th><td><input type="submit" value="<c:out value="${bundle.getString('submit')}"/>"></td>
            </tr>
        </form>
    </table>
</div>
</body>
</html>
