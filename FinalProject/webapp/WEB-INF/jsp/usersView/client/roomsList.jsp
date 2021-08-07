<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 23.07.21
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rooms list</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/Main.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Header.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Rooms.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Footer.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/Link.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/fontawesome/css/all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/Table.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/dataTables.bootstrap4.min.css"/>">
</head>
<body>
<c:set var="bundle" value="${sessionScope.bundle}"/>

<div class="page">
  <nav class="one">
    <ul>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_HOME">
          <i class="fa fa-home fa-fw" aria-hidden="true"></i>
          <c:out value="${bundle.getString('home')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_ROOMS">
          <i class="fa fa-shower" aria-hidden="true"></i>
          <c:out value="${bundle.getString('rooms')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_PROFILE">
          <i class="fa fa-user-circle" aria-hidden="true"></i>
          <c:out value="${bundle.getString('profile')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_CREATE_REQUEST_PAGE">
          <i class="fa fa-pencil-alt" aria-hidden="true"></i>
          <c:out value="${bundle.getString('createRequest')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_SHOW_REQUESTS">
          <i class="fa fa-book" aria-hidden="true"></i>
          <c:out value="${bundle.getString('requests')}"/>
        </a>
      </li>
      <li>
        <a href="http://localhost:8080/controller?command=ACT_LOGOUT">
          <i class="fa fa-sign-out-alt" aria-hidden="true"></i>
          <c:out value="${bundle.getString('logOut')}"/>
        </a>
      </li>
    </ul>
  </nav>


  <a href="http://localhost:8080/controller?command=ACT_SHOW_ROOMS" style="left:0; margin-bottom: 11px; width: 135px;" class="s2"><c:out value="${bundle.getString('roomTypes')}"/></a>
  <div class="content">
    <div class="container mb-3 mt-3">
        <table class="table table-bordered mydatatable table-hover table-dark" >
          <colgroup>
            <col span="6">
          </colgroup>
          <caption>
            <c:out value="${bundle.getString('roomsList')}"/>
          </caption>
          <thead>
            <tr>
              <th>
                <b>
                  <c:out value="${bundle.getString('roomNumber')}"/>
                </b>
              </th>
              <th>
                <b>
                  <c:out value="${bundle.getString('roomClass')}"/>
                </b>
              </th>
              <th>
                <b>
                  <c:out value="${bundle.getString('priceDay')}"/>
                </b>
              </th>
              <th>
                <b>
                  <c:out value="${bundle.getString('numberOfSeats')}"/>
                </b>
              </th>
              <th>
                <b>
                  <c:out value="${bundle.getString('roomStatus')}"/>
                </b>
              </th>
              <th>
                <b>
                  <c:out value="${bundle.getString('roomReserved')}"/>
                </b>
              </th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="room" items="${list}">
            <tr>
                <td>
                  <c:out value="${room.roomNumber}"/>
                </td>
                <td>
                  <c:choose>
                    <c:when test="${room.roomClass == 'BUSINESS'}">
                      <c:out value="${bundle.getString('business')}"/>
                    </c:when>
                    <c:when test="${room.roomClass == 'ECONOM'}">
                      <c:out value="${bundle.getString('econom')}"/>
                    </c:when>
                    <c:when test="${room.roomClass == 'LUXE'}">
                      <c:out value="${bundle.getString('luxe')}"/>
                    </c:when>
                    <c:when test="${room.roomClass == 'PREMIUM'}">
                      <c:out value="${bundle.getString('premium')}"/>
                    </c:when>
                  </c:choose>
                </td>
                <td>
                  <c:out value="${room.price}"/>
                </td>
                <td>
                  <c:out value="${room.numberOfSeats}"/>
                </td>
                <td>
                  <c:choose>
                    <c:when test="${room.roomStatus == 'AVAILABLE'}">
                      <c:out value="${bundle.getString('roomStatus.available')}"/>
                    </c:when>
                    <c:when test="${room.roomStatus == 'CLOSED'}">
                      <c:out value="${bundle.getString('roomStatus.closed')}"/>
                    </c:when>
                  </c:choose>
                </td>
                <td>
                  <c:set var="i" value="0"/>
                  <c:forEach var="item" items="${reservedRooms}">
                    <c:choose>
                      <c:when test="${room.roomNumber == item.value.room.roomNumber}">
                        <c:set var="i" value="1"/>
                        <c:out value="${bundle.getString('from')} ${item.value.start}"/>
                        <c:out value="${bundle.getString('to')} ${item.value.end}"/>
                        <br>
                      </c:when>
                    </c:choose>
                  </c:forEach>
                  <c:choose>
                    <c:when test="${i == 0}">
                      <c:out value="${bundle.getString('notReserved')}"/>
                    </c:when>
                  </c:choose>
                </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
    </div>
  </div>

  <footer class="footer">
    <img src="<c:url value="/resources/images/image1.png"/>" alt="image">
    <p>
      <c:out value="${bundle.getString('author')}"/>
    </p>
  </footer>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>

<script>
  $('.mydatatable').DataTable({
    searching: false,
    ordering: true,
    info: false,
    language: {
      lengthMenu: '${bundle.getString('showEntries')} _MENU_',
      paginate:{
        "first":      "First",
        "last":       "Last",
        "next":       "${bundle.getString('next')}",
        "previous":   "${bundle.getString('prev')}"
      },
      zeroRecords:    '${bundle.getString('zeroRecords')}',
      emptyTable:     '${bundle.getString('emptyTable')}'
    },
    lengthMenu: [[5,10,25,50,-1],[5,10,25,50,"All"]],
    "dom": '<"top"i>rt<"bottom"flp><"clear">',
    createRow: function (row, data, index){
      if ( data[5].replace(/[\$,]/g,'') * 1 > 150000){
        $('td', row).eq(5).addClass('text-success');
      }
    }
  });
</script>
</body>
</html>