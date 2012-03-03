<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/main.css"/>" title="Style">
    <title><d:title default="Main"/> &mdash; Long Twit</title>
    <script src="<c:url value="/static/js/jquery-1.7.1.min.js"/>"></script>
    <script src="<c:url value="/static/js/jquery.timers-1.2.js"/>"></script>
    <script src="<c:url value="/static/js/lngtw.js"/>"></script>
    <d:head/>
</head>
<body>
<div id="content">
    <%@include file="/WEB-INF/jsp/header.jspf"%>
    <d:body/>
</div>
<%@ include file="/WEB-INF/jsp/yandex.jspf"%>
</body>
</html>