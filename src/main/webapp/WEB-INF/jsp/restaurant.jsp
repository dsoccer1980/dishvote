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
                <td><a href="/rest/restaurant/update/id/${restaurant.id}">update</a></td>
                <td><a href="/rest/restaurant/delete/id/${restaurant.id}">delete</a></td>
                <td><a href="/rest/restaurant/listdish/id/${restaurant.id}">add/edit menu</a></td>
            </tr>
          </c:forEach>
    </table>

    <br>
    Add new restaurant
    <form action="/rest/restaurant/add" method="post">
        Name <input type="text" name="name"> <br>
        Address <input type="text" name="address"> <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" name="submit" value="submit">
    </form>
</section>
<jsp:include page="pageFooter.jsp"/>
</body>
</html>