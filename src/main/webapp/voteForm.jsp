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
        <input type="date" name="date" value="${date}">
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
            </tr>
        </c:forEach>
    </table>
        <input type="submit" name="submit">
    </form>

    <table border="1" cellpadding="8" cellspacing="0">
    <c:forEach items="${allVotesForUser}" var="userVote">
        <jsp:useBean id="userVote" scope="page" type="ru.dsoccer1980.dishvote.model.UserVote"/>
        <tr>
             <td>${userVote.restaurant.name}</td>
             <td>${userVote.date}</td>
        </tr>
    </c:forEach>
    </table>

</section>
</body>
</html>