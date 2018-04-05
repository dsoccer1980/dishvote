<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User</title>

</head>
<body>
<section>
Admin panel <br><br>

    Hello <c:out value="${user.name}"/>  <br>

    <a href="/rest/restaurant/show">Show restaurants</a>       <br>
    <a href="/rest/adminVote/show">Show votes</a>           <br>
    <a href="/rest/admin/showUsers">Edit users</a>           <br>


</section>
</body>
</html>