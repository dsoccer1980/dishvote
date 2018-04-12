<%@ page import="java.time.LocalDate" %>
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
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="ru.dsoccer1980.dishvote.model.User"/>
            <tr>
                <td>${user.name}</td>
                <td><a href="/rest/admin/userEditForm/id/${user.id}">Update</a></td>
                <td><a href="/rest/admin/userDelete/id/${user.id}">Delete</a></td>
            </tr>
          </c:forEach>
    </table>

    <br>
    Add new user
    <form action="/rest/admin/addUser" method="post">
        Name<input type="text" name="name"> <br>
        E-mail <input type="text" name="email"> <br>
        Password <input type="password" name="password"> <br>
        <c:set var="date" value="<%=LocalDate.now()%>"/>
        Registered <input type="date" value="${date}" name="registered" required> <br>
        Enabled yes<input type="radio" name="enabled" value="true" checked="checked">
                no<input type="radio" name="enabled" value="false"> <br>
        Is admin no<input type="radio" name="is_admin" value="false" checked="checked">
                 yes<input type="radio" name="is_admin" value="true"> <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" name="submit" value="submit">
    </form>
</section>
<jsp:include page="pageFooter.jsp"/>
</body>
</html>