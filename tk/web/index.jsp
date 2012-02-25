<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Twitter killer</title>
</head>
<body>
<form action="/render" method="post">
    <label for="message">Message</label>
    <textarea rows="8" cols="60" id="message" name="message"></textarea>
    <input type="submit" value="Twit"/>
</form>
</body>
</html>