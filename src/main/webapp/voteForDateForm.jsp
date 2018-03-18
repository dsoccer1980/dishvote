<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Vote</title>

</head>
<body>
<section>

    <form method="get" action="adminVote">
        <input type="date" name="date" value="${date}" >
        <input type="submit" name="submit">
    </form>

    <table border="1" cellpadding="8" cellspacing="0">
        <c:forEach items="${allVotesForDate}" var="entry">
        <tr>
                <c:set var="restaurant" value="${entry.key}"></c:set>
                <jsp:useBean id="restaurant" scope="page" type="ru.dsoccer1980.dishvote.model.Restaurant"/>

                <td>${restaurant.name}</td>
                <td>${entry.value}</td>
         </c:forEach>
        </tr>
    </table>

    ${message}

</section>
</body>
</html>