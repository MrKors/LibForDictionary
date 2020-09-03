<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/resources/static/js/changeText.js"></script>
    <script src="/resources/static/js/deleteTranslation-ajax.js"></script>

    <title>Edit translation</title>
</head>
<body>

<h1>Edit translation</h1>

<form:form action="/words/editTranslation" method="post" modelAttribute="word">
    <table>
        <tr>
                <%--            <td><form:label path="originValue" >Origin Value</form:label></td>--%>
                <%--            <td><form:input path="originValue" readonly="true" id="origin-value"/></td>--%>
            <td><label for="originValue">Origin Value</label></td>
            <td><input id="originValue" name="originValue" type="text" value="${word.word.originValue}"/></td>
        </tr>
        <tr>
            <c:if test="${!empty word.word.translationList}">
                <td><label for="chooseTranslation">Choose translation</label></td>
                <td><select id="chooseTranslation" name="oldTranslation">
                    <c:forEach items="${word.word.translationList}" var="translation">
                    <option value="${translation.id}">${translation.translation}</option>
                    </c:forEach>
                </td>
                <td><a href="/words/deleteTranslation" id="deleteTranslationRef">Delete Translation</a></td>
            </c:if>
        </tr>
        <tr>
            <td><form:label path="translation">Translation</form:label></td>
            <td><form:input path="translation" id="editTranslation"/></td>
            <td><form:errors path="translation" cssClass="ui-state-error"/></td>
                <%--            <td><label for="editTranslation">Edit translation</label></td>--%>
                <%--            <td><input id="editTranslation" name="newTranslation" type="text" value=""/></td>--%>
        </tr>
        <tr>
            <td><label for="dictionaryName">Dictionary</label></td>
            <td><input id="dictionaryName" name="dictionaryName" readonly value="${word.word.dictionary.name}"/></td>
            <td><input name="dictionary" type="text" value="${word.word.dictionary.id}" hidden/></td>
                    <%--                <form:select path="dictionary">--%>
                    <%--                <c:forEach items="${dictionaryList}" var="dictionary">--%>
                    <%--                    <form:option value="${dictionary.id}" label="${dictionary.name}"/>--%>
                    <%--                </c:forEach>--%>
                    <%--            </form:select>--%>
            </td>
        </tr>
        <tr>
            <c:if test="${!empty word.word.translationList}">
                <td colspan="2">
                    <input type="submit" value="<spring:message text="OK"/>"/>
                </td>
            </c:if>
            <td><a href="/words-list">Back</a></td>
        </tr>
    </table>
</form:form>


</body>
</html>
