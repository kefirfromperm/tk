<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Authorization &mdash; Long Twit</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes" />
    <link rel="stylesheet" type="text/css" href="/css/main.css" title="Style">
</head>
<body>
<div id="content">
    <%@include file="/WEB-INF/jsp/header.jspf"%>
    <div id="auth"><a class="button" href="<c:url value="/signin"/>">Sign in!</a></div>
</div>
<%@ include file="/WEB-INF/jsp/yandex.jspf"%>
</body>
</html>