<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Long Twit</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css" title="Style">
    <link rel="stylesheet" type="text/css" href="/css/mobile.css" title="Style">
</head>
<body>
<div id="content">
    <%@include file="/header.jspf"%>
    <div id="form">
        <form action="/twit" method="post">
            <label for="message">Your long twit</label>
            <textarea rows="8" cols="60" id="message" name="message"><c:out value="${param.message}"/></textarea>
            <input type="submit" value="Twit"/>
        </form>
        <c:if test="${requestScope.errorMessage!=null}">
            <div class="error"><c:out value="${requestScope.errorMessage}"/></div>
        </c:if>
    </div>
</div>
<!-- Yandex.Metrika counter -->
<div style="display:none;"><script type="text/javascript">
    (function(w, c) {
        (w[c] = w[c] || []).push(function() {
            try {
                w.yaCounter12853090 = new Ya.Metrika({id:12853090,
                    trackLinks:true,
                    accurateTrackBounce:true});
            }
            catch(e) { }
        });
    })(window, "yandex_metrika_callbacks");
</script></div>
<script src="//mc.yandex.ru/metrika/watch.js" type="text/javascript" defer="defer"></script>
<noscript><div><img src="//mc.yandex.ru/watch/12853090" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
<!-- /Yandex.Metrika counter -->
</body>
</html>