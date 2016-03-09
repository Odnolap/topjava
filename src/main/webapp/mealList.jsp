<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal List</title>
    <style type="text/css">
        table, td, th{
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
    <body>
        <h2>Meal List</h2>
        <table>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
            </tr>
            <c:forEach items="${requestScope.UMWEList}" var="umwe">
                <c:choose>
                    <c:when test="${umwe.exceed}">
                        <tr style="color: red">
                    </c:when>
                    <c:otherwise>
                        <tr style="color: green">
                    </c:otherwise>
                </c:choose>

                <td>${umwe.dateTime.toLocalDate()} ${umwe.dateTime.toLocalTime()}</td>
                <td>${umwe.description}</td>
                <td>${umwe.calories}</td>
                </tr>

            </c:forEach>
        </table>
    </body>
</html>

