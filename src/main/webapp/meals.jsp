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
          <link rel="stylesheet" type="text/css"  href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h1>Список еды:</h1>
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
    <c:forEach items="${mealToFromServer}" var="meal">
        <tr class="${meal.excess ? 'exceed' : 'normal'}">
            <td>${TimeUtil.formatWithoutT(meal.dateTime)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?id=${meal.id}&action=edit">Update</a></td>
            <td><a href="meals?id=${meal.id}&action=delete">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
