<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<div id="form">
    <form action="<c:url value="/twit"/>" method="post">
        <label for="message">Your long twit</label>
        <textarea rows="8" cols="60" id="message" name="message"><c:out value="${param.message}"/></textarea>
        <div id="counter" class="js">0</div>
        <input class="button twit" type="submit" value="Twit"/>
    </form>
    <c:if test="${requestScope.errorMessage!=null}">
        <div class="error"><c:out value="${requestScope.errorMessage}"/></div>
    </c:if>
</div>
</body>
</html>