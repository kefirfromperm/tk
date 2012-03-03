<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="title.auth"/></title>
</head>
<body>
<div class="onebutton"><a class="button" href="<c:url value="/signin"/>"><fmt:message key="button.signin"/></a></div>
</body>
</html>