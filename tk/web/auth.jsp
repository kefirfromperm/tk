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
    <%@include file="/header.jspf"%>
    <div id="auth"><a href="<c:url value="/signin"/>">Sign in!</a></div>
</div>
</body>
</html>