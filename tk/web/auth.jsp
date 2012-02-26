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