<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css"/>

    <title>Dictionaries</title>
</head>
<body>
<h1>Create dictionary</h1>

<a href="../../index.jsp">Back to main page</a>

<form:form action="/dictionary/create" method="post" modelAttribute="dictionary">
    <form:label path="name">Dictionary name:</form:label>
    <form:input path="name"/>
    <form:errors path="name" cssClass="ui-state-error"/> <br>
    <form:label path="consistenceCriteria">Type dictionary: </form:label>
    <form:select path="consistenceCriteria">
        <c:forEach items="${criteria.criteriaMap}" var="label">
            <form:option value="${label.value}" label="${label.key}"/>
        </c:forEach>
    </form:select>
    <form:errors path="consistenceCriteria" cssClass="ui-state-error"/> <br>
    <form:label path="lengthCriteria">Words length: </form:label>
    <form:input path="lengthCriteria"/>
    <form:errors path="lengthCriteria" cssClass="ui-state-error"/> <br>
    <input type="submit" name="add_Dictionary" value="Add dictionary"/>

</form:form>

</body>
</html>
