<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User</title>

</head>
<body>
<section>


    Hello <c:out value="${user.name}"/>  <br>

    <a href="/user/get">profile</a>    <br>
    <a href="/userVote">vote</a>       <br>
    <a href=""></a>           <br>


</section>
</body>
</html>