<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 19.05.21
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Requests</title>
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

<p>
<table>
    <colgroup>
        <col span="8" style="background: Khaki">
    </colgroup>
    <caption><c:out value="${bundle.getString('userRequestsList')}"/></caption>
    <tr>
        <th><c:out value="${bundle.getString('roomNumber')}"/></th>
        <th><c:out value="${bundle.getString('roomClass')}"/></th>
        <th><c:out value="${bundle.getString('numberOfSeats')}"/></th>
        <th><c:out value="${bundle.getString('startDate')}"/></th>
        <th><c:out value="${bundle.getString('endDate')}"/></th>
        <th><c:out value="${bundle.getString('pricePeriod')}"/></th>
        <th><c:out value="${bundle.getString('payment')}"/></th>
        <th><c:out value="${bundle.getString('requestStatus')}"/></th>
    </tr>
    <c:forEach var="request" items="${list}">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${request.requestStatus == 'INPROGRESS'}">
                        <c:out value="${bundle.getString('requestStatus.progress')}"/>
                    </c:when>
                    <c:when test="${request.requestStatus == 'DENIED'}">
                        <c:out value="${bundle.getString('requestStatus.denied')}"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${request.room.roomNumber}"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td><c:choose>
                <c:when test="${request.requestStatus == 'INPROGRESS' || request.requestStatus == 'DENIED'}">
                    <c:out value="${request.roomClass}"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${request.room.roomClass}"/>
                </c:otherwise>
            </c:choose></td>
            <td><c:out value="${request.numberOfSeats}"/> </td>
            <td><c:out value="${request.start}"/> </td>
            <td><c:out value="${request.end}"/></td>
            <td><c:choose>
                <c:when test="${request.requestStatus == 'INPROGRESS'}">
                    <c:out value="${bundle.getString('requestStatus.progress')}"/>
                </c:when>
                <c:when test="${request.requestStatus == 'DENIED'}">
                    <c:out value="${bundle.getString('requestStatus.denied')}"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${(request.end.time-request.start.time)*request.room.price/ (1000*60*60*24)}"/> BYN</td>
                </c:otherwise>
            </c:choose>
            <td><c:choose>
                <c:when test="${request.requestStatus == 'INPROGRESS'}">
                    <c:out value="${bundle.getString('requestStatus.progress')}"/>
                </c:when>
                <c:when test="${request.requestStatus == 'DENIED'}">
                    <c:out value="${bundle.getString('requestStatus.denied')}"/>
                </c:when>
                <c:when test="${request.requestStatus == 'PAID'}">
                    <c:out value="${bundle.getString('requestStatus.paid')}"/>
                </c:when>
                <c:when test="${request.payment == null}">
                    <form action="controller?command=ACTPAYFORREQUEST" method = "post">
                        <input type="submit" name="submit" value="${bundle.getString('pay')}">
                        <input type="submit" name="submit" value="${bundle.getString('cancel')}">
                        <input type="hidden" name="id" value="${request.id}">
                    </form>
                </c:when>
                <c:when test="${request.payment != null}">
                   <c:choose>
                       <c:when test="${request.payment.status == 'PENDINGPAYMENT'}">
                           <c:out value="${bundle.getString('pendingPayment')}"/>
                       </c:when>
                       <c:when test="${request.payment.status == 'CANCELLED'}">
                           <c:out value="${bundle.getString('requestStatus.cancelled')}"/>
                       </c:when>
                       <c:otherwise>
                           <c:out value="${bundle.getString('requestStatus.paid ')}"/>
                       </c:otherwise>
                   </c:choose>
                </c:when>
            </c:choose></td>
            <td>
                <c:choose>
                    <c:when test="${request.requestStatus == 'INPROGRESS'}">
                        <c:out value="${bundle.getString('requestStatus.progress')}"/>
                    </c:when>
                    <c:when test="${request.requestStatus == 'DENIED'}">
                        <c:out value="${bundle.getString('requestStatus.denied')}"/>
                    </c:when>
                    <c:when test="${request.requestStatus == 'PAID'}">
                        <c:out value="${bundle.getString('requestStatus.paid')}"/>
                    </c:when>
                    <c:when test="${request.requestStatus == 'ACCEPTED'}">
                        <c:out value="${bundle.getString('requestStatus.accepted')}"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${bundle.getString('requestStatus.cancelled')}"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</p>

</body>
</html>
