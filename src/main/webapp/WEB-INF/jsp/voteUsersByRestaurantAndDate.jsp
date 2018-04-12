<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Vote</title>

</head>
<body>
<section>


    <table border="1" cellpadding="8" cellspacing="0">
    <c:forEach items="${userList}" var="user">
        <jsp:useBean id="user" scope="page" type="ru.dsoccer1980.dishvote.model.User"/>
        <tr>
             <td>${user.name}</td>
        </tr>
    </c:forEach>
    </table>

    ${message}

</section>
<jsp:include page="pageFooter.jsp"/>
</body>
</html>