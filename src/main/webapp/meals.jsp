<%--
  Created by IntelliJ IDEA.
  User: enjoy
  Date: 04.10.19
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<html>
<head>
    <title>Meals</title>
    <style>
        table {
            border-collapse: collapse;
            width: 55%;
        }
        th, td {
            text-align: left;
            padding: 11px;
        }
        tr:nth-child(even){background-color: #f2f2f2}
        th {
            background-color: white;
            color: grey;
        }
    </style>
</head>
<body>
<h1 style="color: grey">Список еды:</h1>
<table>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    <c:forEach items="${mealToFromServer}" var="mealTo">
        <tr style="${mealTo.excess ? 'color: red':'color: green'}">
            <td>${TimeUtil.formatWithoutT(mealTo.dateTime)}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
