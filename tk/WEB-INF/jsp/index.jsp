<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="fontColor" value="${requestScope.command.fontColor}"/>
<c:if test="${fontColor==null}"><c:set var="fontColor" value="#000000"/></c:if>
<c:set var="backgroundColor" value="${requestScope.command.backgroundColor}"/>
<c:if test="${backgroundColor==null}"><c:set var="backgroundColor" value="#ffffff"/></c:if>
<html>
<head></head>
<body>
<div id="form">
    <form action="<c:url value="/twit"/>" method="post">
        <label for="message"><fmt:message key="message.label.twit"/></label>
        <textarea rows="8" cols="60" id="message" name="message" style="color: ${fontColor};background-color: ${backgroundColor};"><c:out value="${requestScope.command.text}"/></textarea>
        <div id="counter" class="js">0</div>
        <fmt:message key="message.label.font.color" var="fontColorLabel"/>
        <tags:colorSelector label="${fontColorLabel}" name="font-color" selected="${fontColor}"/>
        <fmt:message key="message.label.background.color" var="backgroundColorLabel"/>
        <tags:colorSelector label="${backgroundColorLabel}" name="background-color" selected="${backgroundColor}"/>
        <input class="button twit" type="submit" value="<fmt:message key="button.twit"/>"/>
    </form>
    <c:if test="${requestScope.errorMessage!=null}">
        <div class="error"><fmt:message key="${requestScope.errorMessage}"/></div>
    </c:if>
</div>
</body>
</html>