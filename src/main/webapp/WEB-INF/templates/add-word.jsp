<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css"/>

    <title>Add words</title>
</head>
<body>
<h1>Add words</h1>

<form:form action="/words/add" method="post" modelAttribute="word">
    <table>
        <tr>
            <td><form:label path="originValue">Origin Value</form:label></td>
            <td><form:input path="originValue"/></td>
            <td><form:errors path="originValue" cssClass="ui-state-error"/> </td>
        </tr>
        <tr>
            <td><form:label path="translation">Translation</form:label></td>
            <td><form:input path="translation"/></td>
            <td><form:errors path="translation" cssClass="ui-state-error"/></td>
<%--            <td><label for="translation">Translation</label></td>--%>
<%--            <td><input id="translation" name="translation" type="text" value=""/></td>--%>
        </tr>
        <tr>
            <td><form:label path="dictionary"/>Dictionary</td>
            <td><form:select path="dictionary">
                <option selected disabled>Choose dictionary</option>
                <c:forEach items="${dictionaryList}" var="dictionary">
                    <form:option value="${dictionary.id}" label="${dictionary.name}"/>
                </c:forEach>
            </form:select></td>
            <td><form:errors path="dictionary" cssClass="ui-state-error"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Add Word"/>"/>
            </td>
        </tr>
    </table>
</form:form>

<div>
    <a href="../../index.jsp">Back to main page</a>
</div>

</body>
</html>
