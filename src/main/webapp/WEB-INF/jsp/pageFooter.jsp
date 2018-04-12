
<br><br><br>
<a href="/">menu</a>

<div>
        <form id="logoutForm" method="post" action="/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    <input type="button" name="Logout" value="Logout" onclick="document.forms['logoutForm'].submit()">
</div>
