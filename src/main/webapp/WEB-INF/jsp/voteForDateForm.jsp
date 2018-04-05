<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Vote</title>

</head>
<body>
<section>

    <form method="get" action="/rest/adminVote/show">
        <input type="date" name="date" value="${date}" onchange="document.getElementById('submitButton').click();">
        <input type="submit" name="submit" id="submitButton" hidden>
    </form>

    <table border="1" cellpadding="8" cellspacing="0">
        <c:forEach items="${allVotesForDate}" var="entry">
        <tr>
                <c:set var="restaurant" value="${entry.key}"></c:set>
                <jsp:useBean id="restaurant" scope="page" type="ru.dsoccer1980.dishvote.model.Restaurant"/>

                <td>
                    <c:if test = "${entry.value > 0}" >
                        <a href="/rest/adminVote/showUsersByRestaurantAndDate/restaurant_id/${restaurant.id}/date/${date}">${restaurant.name}</a>
                    </c:if>
                    <c:if test = "${entry.value == 0}" >
                        ${restaurant.name}
                    </c:if>

                </td>
                <td>${entry.value}</td>
         </c:forEach>
        </tr>
    </table>

    ${message}

</section>
</body>
</html>