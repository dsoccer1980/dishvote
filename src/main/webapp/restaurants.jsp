<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Workers</title>

</head>
<body>
<section>

    <table border="1" cellpadding="8" cellspacing="0">
        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="ru.dsoccer1980.dishvote.model.Restaurant"/>
            <tr>
               <td>${restaurant.id}</td>
                <td>${restaurant.name}</td>
            </tr>
          </c:forEach>
    </table>
</section>
</body>
</html>