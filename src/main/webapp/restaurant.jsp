<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Restaurant</title>

</head>
<body>
<section>

    <table border="1" cellpadding="8" cellspacing="0">
        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="ru.dsoccer1980.dishvote.model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td>${restaurant.address}</td>
                <td><a href="restaurant?action=update&id=${restaurant.id}">update</a></td>
                <td><a href="restaurant?action=delete&id=${restaurant.id}">delete</a></td>
            </tr>
          </c:forEach>
    </table>

    <br>
    Добавить новый ресторан
    <form action="restaurant" method="post">
        Название<input type="text" name="name"> <br>
        Адрес <input type="text" name="address"> <br>
        <input type="submit" name="submit" value="submit">
    </form>
</section>
</body>
</html>