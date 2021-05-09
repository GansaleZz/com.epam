<%--
  Created by IntelliJ IDEA.
  User: andrew_wannasesh
  Date: 25.04.21
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <style>
        ul {
            background: #0083ca; /* Цвет фона */
            list-style: none; /* Убираем маркеры */
            margin: 0; padding: 0; /* Обнуляем все отступы */
            overflow: hidden; /* Отменяем обтекание */
        }
        li {
            float: left; /* Выстраиваем по горизонтали */
        }
        a {
            display: block; /* Блочный элемент */
            padding: 7px 15px; /* Поля вокруг текста */
            color: #fff; /* Цвет текста */
        }
    </style>
</head>
<body>
    <ul>
    <li><a href="http://localhost:8080/controller?command=LOGOUT">Log Out</a></li>

    <li><a href="http://localhost:8080/usersView/admin/home/home.jsp">Home</a></li>

    <li><a href="http://localhost:8080/controller?command=ACTSHOWROOMS">Rooms</a></li>

    <li><a href="http://localhost:8080/controller?command=ACTSHOWPROFILE">Profile</a></li>

    <li><a href="http://localhost:8080/controller?command=ACTCREATEREQUEST">Create request</a></li>

    <li><a href="http://localhost:8080/controller?command=ACTSHOWUSERS">Users list</a></li>
    </ul>

</body>
</html>
