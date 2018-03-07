<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Vote</title>

</head>
<body>
<section>
    <form method="post" action="userVote">
    <table border="1" cellpadding="8" cellspacing="0">
        <c:forEach items="${dishes}" var="entry">
            <tr>
            <c:forEach items="${entry.value}" var="dish" varStatus="loopCount">
                <jsp:useBean id="dish" scope="page" type="ru.dsoccer1980.dishvote.model.Dish"/>
                <c:if test = "${loopCount.count == 1}" >
                    <td>${dish.restaurant.name}</td>
                </c:if>
                    <td>${dish.name}</td>
            </c:forEach>
                <td> <input type="radio" name="restaurantId" value="${entry.key}"></td>
                <input type="hidden" name="userId" value="100000">
            </tr>
        </c:forEach>
    </table>
        <input type="submit" name="submit">
    </form>
</section>
</body>
</html>