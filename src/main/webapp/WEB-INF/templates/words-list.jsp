<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>
</head>
<body>
<a href="../../index.jsp">Back to index</a>

<h1>Words list</h1>

<c:if test="${!empty wordsList}">
    <table border="1" cellspacing="1" cellpadding="2">
        <tr>
            <th>Origin Value</th>
            <th>Translation</th>
            <th>Add translation</th>
            <th>Dictionary</th>
            <th colspan="2">Actions</th>
        </tr>
        <c:forEach items="${wordsList}" var="word">
            <tr>
                <td>${word.originValue}</td>
                <td>
                    <select><c:forEach items="${word.translationList}" var="translation">
                        <option>${translation.translation}"</option>
                    </c:forEach>
                    </select>
                </td>
                <td><a href="/words/add/${word.originValue}/addTranslation">add translation</a></td>
                <td>${word.dictionary.name}</td>
                <td><a href="/words/edit/${word.originValue}">Edit</a> </td>
                <td><a href="/words/delete/${word.originValue}">Delete</a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="<c:url value="/add-word"/>">Add word to dictionary</a>


</body>
</html>
