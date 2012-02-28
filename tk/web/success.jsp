<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Success &mdash; Long Twit</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes" />
    <link rel="stylesheet" type="text/css" href="/css/main.css" title="Style">
</head>
<body>
<div id="content">
    <%@include file="/WEB-INF/jsp/header.jspf"%>
    <div class="message">Your long twit was posted successfully!</div>
    <div id="success">
        <a class="button" href="<c:url value="http://twitter.com"/>">Twitter</a>
        <a class="button" href="<c:url value="/"/>">Another twit?</a>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/yandex.jspf"%>
</body>
</html>