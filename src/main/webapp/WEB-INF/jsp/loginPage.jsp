<html>
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
</head>
<body>

<form method="POST" action="login">
    <h2>Log in</h2>

    <div>
        <span>${message}</span>
        <input name="username" id="username" type="text"  placeholder="Username"
               autofocus="true"/>
        <input name="password" id="password" type="password"  placeholder="Password"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <span>${error}</span>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
    </div>
</form>

<button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('user1@yandex.ru', 'password')">
  User
</button>
<button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('admin@gmail.com', 'admin')">
    Admin
</button>

<script type="text/javascript">
    function setCredentials(username, password) {
        document.getElementById('username').setAttribute('value', username);
        document.getElementById('password').setAttribute('value', password);
    }
</script>
</body>
</html>
