<%@ include file="include.jsp" %>

<html>
<head>
    <title><fmt:message key="newsPage.title"/></title>
</head>
<body>

<c:out value="${param.emailInput}"/><br/>
<table border="1" width="100%" cellpadding="0" cellspacing="2" bgcolor="#f0f8ff">
    <tr>
        <th colspan=2><fmt:message key="newsPage.tableTitle"/></th>
    </tr>
    <tr>
        <th width="25%"><fmt:message key="newsPage.header"/></th>
        <th width="75%"><fmt:message key="newsPage.text"/></th>
    </tr>
    <tr>
        <c:forEach items="${newsList}" var="news">
    <tr align="top-left">
        <td><h3>${news.header}</h3></br>
            <i>${news.date}</i>
            <i>${news.time}</i></td>
        <td align="top-left">${news.text}</td>
    </tr>
    </c:forEach>
</table>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="list_news"/>
    <input type="submit" name="addNews" value="add news"/>

</form>
</body>
</html>
