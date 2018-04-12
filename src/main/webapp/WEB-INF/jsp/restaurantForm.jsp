<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Restaurant</title>

</head>
<body>
<section>

    <h2>Edit restaurant</h2>
    <hr>
    <jsp:useBean id="restaurant" type="ru.dsoccer1980.dishvote.model.Restaurant" scope="request"/>
    <form action="/rest/restaurant/edit" method="post">
        <input type="hidden" name="id" value="${restaurant.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${restaurant.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Address:</dt>
            <dd><input type="text" value="${restaurant.address}" name="address" required></dd>
        </dl>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>

</section>
<jsp:include page="pageFooter.jsp"/>
</body>
</html>
