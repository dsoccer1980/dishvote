<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Dish</title>

</head>
<body>
<section>

    <h2>Edit dish</h2>
    <hr>
    <jsp:useBean id="dish" type="ru.dsoccer1980.dishvote.model.Dish" scope="request"/>
    <form action="/rest/dish/edit" method="post">
        <input type="hidden" name="id" value="${dish.id}" >
        <input type="hidden" name="restaurant_id" value="${dish.restaurant.id}" >
        <input type="hidden" name="date" value="${dish.date}" >
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${dish.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Price:</dt>
            <dd><input type="text" value="${dish.price}" name="price" required></dd>
        </dl>
        <dl>
            <dt>Date:</dt>
            <dd>${dish.date}</dd>
        </dl>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>

</section>
<jsp:include page="pageFooter.jsp"/>
</body>
</html>
