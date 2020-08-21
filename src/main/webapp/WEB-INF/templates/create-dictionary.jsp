<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dictionaries</title>
</head>
<body>
<h1>Create dictionary</h1>

<a href="../../index.jsp">Back to index</a>

<form:form action="/dictionary/create" method="post" modelAttribute="dictionary">
    <form:label path="name">Dictionary name:</form:label>
    <form:input path="name"/>
    <form:label path="consistenceCriteria">Type dictionary: </form:label>
    <form:select path="consistenceCriteria">
        <c:forEach items="${criteria.criteriaMap}" var="label">
            <form:option value="${label.value}" label="${label.key}"/>
        </c:forEach>
    </form:select>
    <form:label path="lengthCriteria">Words length: </form:label>
    <form:input path="lengthCriteria"/>
    <input type="submit" name="add_Dictionary" value="Add dictionary"/>

</form:form>

</body>
</html>
