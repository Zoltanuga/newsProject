<%@ include file="include.jsp" %>
<html>
<head>
    <title><fmt:message key="main.title"/></title>
</head>
<body>
<div align="center">
    <h1><fmt:message key="main.header"/></h1>

    <form action="Controller" method="post">
        <input type="email" name="emailInput" placeholder="email"/><br/>
        <input type="password" name="passwordInput" placeholder="password" /><br/>
        <input type="hidden" name="command" value="main"/>
        <input type="submit" name="login" value="log in"/>
    </form>

    <form action="Controller" method="post">
        <input type="hidden" name="command" value="main_redirect"/>
    </form>
</div>
</body>
</html>
