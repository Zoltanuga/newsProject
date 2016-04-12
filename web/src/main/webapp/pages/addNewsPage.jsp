<%@ include file="include.jsp" %>

<html>
<head>
    <title><fmt:message key="addNewsPage.title"/></title>
</head>
<body>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="add_news"/>
    <h4><fmt:message key="addNewsPage.header"/></h4><br/>

    <fmt:message key="addNewsPage.titleInputHeader"/><br/>
    <table bgcolor="#f0f8ff">
        <tr>
            <td><textarea name="header" cols="40" rows="1"></textarea></td>
        </tr>
    </table>

    <fmt:message key="addNewsPage.titleInputText"/><br/>
    <table bgcolor="#f0f8ff">
        <tr>
            <td><textarea name="textNews" cols="40" rows="4"></textarea></td>
        </tr>
    </table>
    <input type="reset" value="clear">
    <input type="submit" name="save" value="Save"/>
</form>
</body>
</html>
