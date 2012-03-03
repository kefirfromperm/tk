<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="title.error.internal"/></title>
</head>
<body>
<div class="error"><fmt:message key="message.error.internal"/></div>
<%@ include file="gotomain.jspf" %>
</body>
</html>