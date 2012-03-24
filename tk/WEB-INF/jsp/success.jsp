<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head></head>
<body>
<div class="message">
    <fmt:message key="message.success"/>
    <c:if test="${requestScope.flash.statusUrl!=null}">
        <br/>
        <a href="<c:out value="${requestScope.flash.statusUrl}"/>" target="_blank"><c:out value="${requestScope.flash.statusUrl}"/></a>
    </c:if>
</div>
<div id="success">
    <a class="button" href="<c:url value="http://twitter.com"/>"><fmt:message key="button.twitter"/></a>
    <a class="button" href="<c:url value="/"/>"><fmt:message key="button.another"/></a>
</div>
</body>
</html>