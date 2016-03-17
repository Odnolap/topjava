<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>User list</h3>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>email</th>
            <th>password</th>
            <th>calories Per Day</th>
            <th>roles</th>
            <th>registered</th>
            <th>enabled</th>
        </tr>
        </thead>
        <c:forEach items="${userList}" var="user">
            <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User"/>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.caloriesPerDay}</td>
                <td>${user.roles}</td>
                <td>${user.registered}</td>
                <td>${user.enabled}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
