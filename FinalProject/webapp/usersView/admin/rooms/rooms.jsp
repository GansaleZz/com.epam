<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.epam.db.dao.impl.RoomDaoImpl" %>
<%@ page import="com.epam.entity.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.exceptions.DaoException" %><%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 6.05.21
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rooms</title>
</head>
<body>
    <a href="http://localhost:8080/auth/logOut.jsp">Log Out</a>

    <a href="http://localhost:8080/usersView/admin/home/home.jsp">Home</a>

    <a href="http://localhost:8080/usersView/admin/rooms/rooms.jsp">Rooms</a>
<%--    <%--%>
<%--        PrintWriter printWriter = response.getWriter();--%>
<%--        RoomDaoImpl roomDao = new RoomDaoImpl();--%>
<%--        try {--%>
<%--            List<Room> list = roomDao.findAllEntities();--%>
<%--            list.stream()--%>
<%--                    .forEach(printWriter::println);--%>
<%--        } catch (DaoException e) {--%>
<%--            e.printStackTrace();--%>
<%--        }--%>
<%--    %>--%>

</body>
</html>
