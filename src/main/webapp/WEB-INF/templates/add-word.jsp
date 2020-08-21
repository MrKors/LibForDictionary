<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add words</title>
</head>
<body>
<h1>Add words</h1>

<form:form action="/words/add" method="post" modelAttribute="word">
    <table>
        <tr>
            <td><form:label path="originValue">Origin Value</form:label></td>
            <td><form:input path="originValue"/></td>
        </tr>
        <tr>
            <td><label for="translation">Translation</label></td>
            <td><input id="translation" name="translation" type="text" value="${translate.translation}"/></td>
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
                <input type="submit" value="<spring:message text="Add Word"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
