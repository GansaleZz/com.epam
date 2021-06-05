<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 2.06.21
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="<c:url value="../resources/css/styles.css"/> ">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<h1><c:out value="${bundle.getString('errorPage')}"/> </h1>
<a href="http://localhost:8080/controller?command=ACTSHOWHOME" class="s2"><c:out value="${bundle.getString('back')}"/> </a>
</body>
</html>
