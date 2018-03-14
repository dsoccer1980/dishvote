<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
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
                <td><a href="dish?action=update&id=${dish.id}">update</a></td>
                <td><a href="dish?action=delete&id=${dish.id}">delete</a></td>
                <td><a href="dish?action=dish&id=${dish.id}">add/edit menu</a></td>
            </tr>
        </c:forEach>
    </table>

    <br>

</section>
</body>
</html>