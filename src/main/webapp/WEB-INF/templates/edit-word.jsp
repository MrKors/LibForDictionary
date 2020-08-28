<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit words</title>
</head>
<body>
<h1>Edit words</h1>

<form:form action="/words/edit" method="post" modelAttribute="word">
    <table>
        <tr>
            <td><label for="oldOriginValue">Origin Value</label></td>
            <td><input id="oldOriginValue" name="oldOriginValue" readonly value="${word.originValue}"/></td>
        </tr>
        <tr>
            <td><label for="newOriginValue">New origin Value</label></td>
            <td><input id="newOriginValue" name="newOriginValue"/></td>
        </tr>
        <tr>
            <td><label for="chooseTranslation">Choose translation</label></td>
            <td>
                <c:if test="${!empty word.translationList}">
                    <select id="chooseTranslation" name="translations"><c:forEach items="${word.translationList}" var="translation">
                        <option>${translation.translation}</option>
                    </c:forEach>
                    </select>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><form:label path="dictionary"/>Dictionary</td>
            <td><form:select path="dictionary">
                <c:forEach items="${dictionaryList}" var="dictionary">
                    <form:option value="${dictionary.id}" label="${dictionary.name}"/>
                </c:forEach>
            </form:select></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Edit Word"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
