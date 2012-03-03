<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<div class="message">Your long twit was posted successfully!</div>
<div id="success">
    <a class="button" href="<c:url value="http://twitter.com"/>">Twitter</a>
    <a class="button" href="<c:url value="/"/>">Another twit?</a>
</div>
</body>
</html>