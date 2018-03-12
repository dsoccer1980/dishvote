<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User</title>

</head>
<body>
<section>


    <a href="">profile</a>    <br>
    <a href="voteChooseDate.jsp">vote</a>       <br>
    <a href=""></a>           <br>

    <form action="userVote?action=chosenDate" method="post">
        <c:set var="date" value="<%=LocalDate.now()%>"/>
        <input type="date"  name="date" required value="${date}">
        <button type="submit">Choose</button>
    </form>


</section>
</body>
</html>