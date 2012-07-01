<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="name" type="java.lang.String" required="true" %>
<%@attribute name="label" type="java.lang.String" required="true" %>
<%@attribute name="selected" type="java.lang.String" required="true" %>
<div class="color-selector">
    <label>${label}:</label>
    <span class="radio-buttons">
        <input type="radio" name="${name}" value="#ffffff" id="${name}-white" <c:if test="${selected == 'white'}">checked="checked"</c:if>/>
        <label for="${name}-white" class="white"></label>
        <input type="radio" name="${name}" value="#000000" id="${name}-black" <c:if test="${selected == 'black'}">checked="checked"</c:if>/>
        <label for="${name}-black" class="black"></label>
        <input type="radio" name="${name}" value="#808080" id="${name}-gray"/>
        <label for="${name}-gray" class="gray"></label>
        <input type="radio" name="${name}" value="#0000FF" id="${name}-blue"/>
        <label for="${name}-blue" class="blue"></label>
        <input type="radio" name="${name}" value="#00ffff" id="${name}-cyan"/>
        <label for="${name}-cyan" class="cyan"></label>
        <input type="radio" name="${name}" value="#FF0000" id="${name}-red"/>
        <label for="${name}-red" class="red"></label>
        <input type="radio" name="${name}" value="#FFC0CB" id="${name}-pink"/>
        <label for="${name}-pink" class="pink"></label>
        <input type="radio" name="${name}" value="#008000" id="${name}-green"/>
        <label for="${name}-green" class="green"></label>
        <input type="radio" name="${name}" value="#ff00ff" id="${name}-magenta"/>
        <label for="${name}-magenta" class="magenta"></label>
        <input type="radio" name="${name}" value="#FFA500" id="${name}-orange"/>
        <label for="${name}-orange" class="orange"></label>
        <input type="radio" name="${name}" value="#FFFF00" id="${name}-yellow"/>
        <label for="${name}-yellow" class="yellow"></label>
    </span>
</div>
