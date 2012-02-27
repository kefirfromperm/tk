<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Auth &mdash; Long Twit</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css" title="Style">
    <link rel="stylesheet" type="text/css" href="/css/mobile.css" title="Style">
</head>
<body>
<div id="content">
    <%@include file="/WEB-INF/jsp/header.jspf"%>
    <div id="auth"><a class="button" href="<c:url value="/signin"/>">Sign in!</a></div>
</div>
<%@ include file="/WEB-INF/jsp/yandex.jspf"%>
</body>
</html>