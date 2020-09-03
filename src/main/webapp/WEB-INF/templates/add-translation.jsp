<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css"/>

    <title>Add translation</title>
</head>
<body>
<h1>Add translation</h1>

<form:form action="/words/addTranslation" method="post" modelAttribute="word">
    <table>
        <tr>
<%--            <td><form:label path="originValue">Origin Value</form:label></td>--%>
<%--            <td><form:input path="originValue" readonly="true">${originValue}</form:input></td>--%>
<%--            <td><form:errors path="originValue"/> </td>--%>
            <td><label for="originValue">Origin Value</label></td>
            <td><input id="originValue" name="originValue" readonly value="${word.word.originValue}"/></td>
        </tr>
        <tr>
            <td><form:label path="translation">Translation</form:label></td>
            <td><form:input path="translation"/></td>
            <td><form:errors path="translation" cssClass="ui-state-error"/></td>
<%--            <td><label for="newTranslation">New translation</label></td>--%>
<%--            <td><input id="newTranslation" name="newTranslation"/></td>--%>
        </tr>
        <tr>
            <td><label for="chooseTranslation">Choose translation</label></td>
            <td>
                <c:if test="${!empty word.word.translationList}">
                    <select id="chooseTranslation" name="translations">
                        <c:forEach items="${word.word.translationList}" var="translation">
                            <option>${translation.translation}</option>
                        </c:forEach>
                    </select>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><label for="dictionaryName">Dictionary</label></td>
            <td><input id="dictionaryName" name="dictionaryName" readonly value="${word.word.dictionary.name}"/></td>
            <td><input name="dictionary" type="text" value="${word.word.dictionary.id}" hidden/></td>
                <%--                <c:forEach items="${dictionaryList}" var="dictionary">--%>
                <%--                    <form:option value="${dictionary.id}" label="${dictionary.name}"/>--%>
                <%--                </c:forEach>--%>
                <%--            </form:select>--%>

        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Add translation"/>"/>
            </td>
        </tr>
    </table>
</form:form>

<div>
    <a href="../../index.jsp">Back to main page</a>
</div>

</body>
</html>
