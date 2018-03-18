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

    Hello <c:out value="${userName.name}"/>  <br>

    <a href="">profile</a>    <br>
    <a href="restaurant">show restaurants</a>       <br>
    <a href="adminVote">show votes</a>           <br>


</section>
</body>
</html>