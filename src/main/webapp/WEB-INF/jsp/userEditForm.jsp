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
    <jsp:useBean id="user" type="ru.dsoccer1980.dishvote.model.User" scope="request"/>
    <form action="/rest/admin/editUser" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${user.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>E-mail:</dt>
            <dd><input type="text" value="${user.email}" name="email" required></dd>
        </dl>
        <dl>
            <dt>E-Password:</dt>
            <dd><input type="password" value="${user.password}" name="password" required></dd>
        </dl>
        <dl>
            <dt>Registered:</dt>
            <dd><input type="date" value="${user.registered}" name="registered" required></dd>
        </dl>
        <dl>
            <dt>Enabled:</dt>
            <dd>yes<input type="radio" name="enabled" value="true"
                <c:if test = "${user.enabled}" >
                    checked="checked"
                </c:if>
                >
                no<input type="radio" name="enabled" value="false"
                <c:if test = "${not user.enabled}" >
                       checked="checked"
                </c:if>
                >
            </dd>
        </dl>
        <dl>
            <dt>Is admin:</dt>
            <dd>false<input type="radio" name="is_admin" value="false"
            <c:if test = "${not user.admin}" >
                       checked="checked"
            </c:if>
            >
                true<input type="radio" name="is_admin" value="true"
                <c:if test = "${user.admin}" >
                       checked="checked"
                </c:if>
                >
            </dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>

</section>
</body>
</html>
