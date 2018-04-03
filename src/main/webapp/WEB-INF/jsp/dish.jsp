<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Dish</title>

</head>
<body>
<section>

    <table border="1" cellpadding="8" cellspacing="0">
        <c:forEach items="${dishes}" var="dish">
            <jsp:useBean id="dish" scope="page" type="ru.dsoccer1980.dishvote.model.Dish"/>
            <tr>
                <td>${dish.name}</td>
                <td>${dish.price}</td>
                <td>${dish.date}</td>
                <td><a href="/dish/update/id/${dish.id}">update</a></td>
                <td><a href="/dish/delete/id/${dish.id}">delete</a></td>
            </tr>
        </c:forEach>
    </table>

    <br>

    Add new dish
    <form action="/dish/add" method="post">
        <input type="hidden" name="restaurant_id" value="${restaurant}">
        <c:set var="date" value="<%=LocalDate.now()%>"/>
        Data <input type="date"  name="date" required value="${date}"> <br>
        Name<input type="text" name="name"> <br>
        Price <input type="text" name="price"> <br>
        <input type="submit" name="submit" value="submit">
    </form>
</section>
</body>
</html>