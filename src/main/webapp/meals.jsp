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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Meals</title>
    <style>
        table {
            text-align:center;
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
            color: black;
        }
    </style>
</head>
<body>
<h1 style="color: black " >Список еды:</h1>
<table>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <tr>
            <th><input type="datetime-local" size="13" name="dateTime" value="${meal.dateTime}"></th>
            <th><input type="text" size="23" name="description" placeholder="Описание" value="${meal.description}"></th>
            <th><input type="number" size="15" name="calories" placeholder="Калории" value="${meal.calories}"></th>
            <th><button type="submit">Сохранить</button></th>
        </tr>
        <input type="text" size="3" name="id" value="${meal.id}" hidden >
    </form>
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
            <td><a href="meals?id=${mealTo.id}&action=edit">Update</a></td>
            <td><a href="meals?id=${mealTo.id}&action=delete">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
