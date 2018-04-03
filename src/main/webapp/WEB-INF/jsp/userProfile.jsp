<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User</title>

</head>
<body>
<section>

    <h2>Edit user</h2>
    <hr>
    <c:set var="user" value="${user}"/>
    <jsp:useBean id="user" type="ru.dsoccer1980.dishvote.model.User" scope="request"/>
    <form action="/user/update" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${user.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="text" value="${user.email}" name="email" required></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="password" value="${user.password}" name="password" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>

</section>
</body>
</html>
