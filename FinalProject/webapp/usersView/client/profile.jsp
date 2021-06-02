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
    <link rel="stylesheet" href="<c:url value="/resources/css/auth.css"/> ">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<nav class="one">
    <ul>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWHOME"><i class="fa fa-home fa-fw"></i><c:out value="${bundle.getString('home')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWROOMS"><c:out value="${bundle.getString('rooms')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWPROFILE"><c:out value="${bundle.getString('profile')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTCREATEREQUESTPAGE"><c:out value="${bundle.getString('createRequest')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=ACTSHOWREQUESTS"><c:out value="${bundle.getString('requests')}"/></a></li>
        <li><a href="http://localhost:8080/controller?command=LOGOUT"><c:out value="${bundle.getString('logOut')}"/></a></li>
    </ul>
</nav>

<c:set var="user" value="${user}" />
<p>
<table>
    <colgroup>
        <col span="2" style="background: Khaki">
    </colgroup>
    <caption><c:out value="${bundle.getString('profile')}"/></caption>
    <form action="controller?command=ACTUPDATEPROFILE" method = "post">
        <input type = "hidden" name = "id" value="${user.id}" >
        <tr>
            <th><c:out value="${bundle.getString('login')}"/> </th><td><c:out value="${user.login}"/></td>
        </tr>
        <tr>
            <th><c:out value="${bundle.getString('name')}"/></th><td><input type="text" name="name" value="${user.name}"></td>
        </tr>
        <tr>
            <th><c:out value="${bundle.getString('email')}"/></th><td><input type="email" name="email" value="${user.email}"></td>
        </tr>
        <tr>
            <th><c:out value="${bundle.getString('role')}"/></th><td>
            <c:choose>
                <c:when test="${user.userRole == 'ADMIN'}">
                    <c:out value="${bundle.getString('admin')}"/>
                </c:when>
                <c:when test="${user.userRole == 'MODERATOR'}">
                    <c:out value="${bundle.getString('moderator')}"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${bundle.getString('client')}"/>
                </c:otherwise>
            </c:choose>
        </td>
        </tr>
        <tr>
            <th><c:out value="${bundle.getString('language')}"/></th><td>
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
            <th><c:out value="${bundle.getString('action')}"/></th><td><input type="submit" value="<c:out value="${bundle.getString('submit')}"/>"></td>
        </tr>
    </form>
</table>
</p>

</body>
</html>
