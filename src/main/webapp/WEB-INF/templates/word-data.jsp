<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Word data</title>
</head>
<body>

<h1>Word data</h1>

<table>
    <tr>
        <th>Origin value:</th>
        <td>${word.originValue}</td>
    </tr>
    <tr>
        <th>Translations:</th>
        <td>
            <c:if test="${!empty word.translationList}">
                <select><c:forEach items="${word.translationList}" var="translation">
                    <option>${translation.translation}</option>
                </c:forEach>
                </select>
            </c:if>
        </td>
    </tr>
    <tr>
        <th>Dictionary:</th>
        <td>${word.dictionary.name}</td>
    </tr>
    <tr>
        <th>Edit/Delete translation:</th>
        <td><a href="/words/editTranslation/${word.originValue}">Edit/Delete</a></td>
    </tr>
    <tr>
    <tr>
        <th>Add translation:</th>
        <td><a href="/words/addTranslation/${word.originValue}">Add</a></td>
    </tr>
    <tr>
        <th>Delete word:</th>
        <td><a href="/words/delete/${word.originValue}">Delete</a></td>
    </tr>

</table>

<div>
    <a href="../../index.jsp">Back to main page</a>
</div>

</body>
</html>
