<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Long twit</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css" title="Style">
    <link rel="stylesheet" type="text/css" href="/css/mobile.css" title="Style">
</head>
<body>
<div id="content">
    <%@include file="/header.jspf"%>
    <div id="form">
        <form action="/twit" method="post">
            <label for="message">Your long twit</label>
            <textarea rows="8" cols="60" id="message" name="message"></textarea>
            <input type="submit" value="Twit"/>
        </form>
        <c:if test="${requestScope.errorMessage!=null}">
            <div class="error"><c:out value="${requestScope.errorMessage}"/></div>
        </c:if>
    </div>
</div>
</body>
</html>