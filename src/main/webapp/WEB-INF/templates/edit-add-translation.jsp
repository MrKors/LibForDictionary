<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add/Edit translation</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/resources/static/js/changeText.js"></script>
    <script src="/resources/static/js/deleteTranslation-ajax.js"></script>
</head>
<body>

<h1>Add/Edit translation</h1>

<form:form action="/words/editTranslation" method="post" modelAttribute="word">
    <table>
        <tr>
            <td><form:label path="originValue" >Origin Value</form:label></td>
            <td><form:input path="originValue" readonly="true" id="origin-value"/></td>
<%--                    <td><label for="originValue">Origin Value</label></td>--%>
<%--                    <td><input id="originValue" name="originValue" type="text" value="${word.originValue}"/></td>--%>
        </tr>
        <tr>
            <td><label for="chooseTranslation">Choose translation</label></td>
            <td><select id="chooseTranslation" name="oldTranslation">
                <c:forEach items="${word.translationList}" var="translation">
                <option>${translation.translation}</option>
                    </c:forEach>
            </td>
            <td><a href="/words/deleteTranslation" id="deleteTranslationRef">Delete Translation</a></td>
        </tr>
        <tr>
            <td><label for="editTranslation">Edit translation</label></td>
            <td><input id="editTranslation" name="newTranslation" type="text" value=""/></td>
        </tr>
        <tr>
            <td><label for="dictionaryName">Dictionary</label></td>
            <td><input id="dictionaryName" value="${dictionary.name}" readonly/>
<%--                <form:select path="dictionary">--%>
<%--                <c:forEach items="${dictionaryList}" var="dictionary">--%>
<%--                    <form:option value="${dictionary.id}" label="${dictionary.name}"/>--%>
<%--                </c:forEach>--%>
<%--            </form:select>--%>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="OK"/>"/>
            </td>
        </tr>
    </table>
</form:form>


</body>
</html>
