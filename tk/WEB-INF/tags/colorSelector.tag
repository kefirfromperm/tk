<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="name" type="java.lang.String" required="true" %>
<%@attribute name="label" type="java.lang.String" required="true" %>
<%@attribute name="selected" type="java.lang.String" required="true" %>
<div class="color-selector">
    <label>${label}</label>
    <span class="radio-buttons">
        <input type="radio" name="${name}" value="#ffffff" id="${name}-white" <c:if test="${selected == '#ffffff'}">checked="checked"</c:if>/>
        <label for="${name}-white" class="white"></label>
        <input type="radio" name="${name}" value="#000000" id="${name}-black" <c:if test="${selected == '#000000'}">checked="checked"</c:if>/>
        <label for="${name}-black" class="black"></label>
        <input type="radio" name="${name}" value="#808080" id="${name}-gray" <c:if test="${selected == '#808080'}">checked="checked"</c:if>/>
        <label for="${name}-gray" class="gray"></label>
        <input type="radio" name="${name}" value="#0000ff" id="${name}-blue" <c:if test="${selected == '#0000ff'}">checked="checked"</c:if>/>
        <label for="${name}-blue" class="blue"></label>
        <input type="radio" name="${name}" value="#00ffff" id="${name}-cyan" <c:if test="${selected == '#00ffff'}">checked="checked"</c:if>/>
        <label for="${name}-cyan" class="cyan"></label>
        <input type="radio" name="${name}" value="#ff0000" id="${name}-red" <c:if test="${selected == '#ff0000'}">checked="checked"</c:if>/>
        <label for="${name}-red" class="red"></label>
        <input type="radio" name="${name}" value="#ffc0cb" id="${name}-pink" <c:if test="${selected == '#ffc0cb'}">checked="checked"</c:if>/>
        <label for="${name}-pink" class="pink"></label>
        <input type="radio" name="${name}" value="#008000" id="${name}-green" <c:if test="${selected == '#008000'}">checked="checked"</c:if>/>
        <label for="${name}-green" class="green"></label>
        <input type="radio" name="${name}" value="#ff00ff" id="${name}-magenta" <c:if test="${selected == '#ff00ff'}">checked="checked"</c:if>/>
        <label for="${name}-magenta" class="magenta"></label>
        <input type="radio" name="${name}" value="#ffa500" id="${name}-orange" <c:if test="${selected == '#ffa500'}">checked="checked"</c:if>/>
        <label for="${name}-orange" class="orange"></label>
        <input type="radio" name="${name}" value="#ffff00" id="${name}-yellow" <c:if test="${selected == '#ffff00'}">checked="checked"</c:if>/>
        <label for="${name}-yellow" class="yellow"></label>
    </span>
</div>
