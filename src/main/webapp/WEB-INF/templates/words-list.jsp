<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="/resources/static/js/search-autocomplete.js"></script>
    <title>Words list</title>
</head>
<body>
<a href="../../index.jsp">Back to index</a>

<h1>Words list</h1>

<c:if test="${!empty wordsList}">
    <table border="1" cellspacing="1" cellpadding="2">
        <tr>
            <th>Origin Value</th>
            <th>Translation</th>
            <th>Edit/Delete translation</th>
            <th>Dictionary</th>
            <th colspan="2">Actions</th>
        </tr>
        <c:forEach items="${wordsList}" var="word">
            <tr>
                <td>${word.originValue}</td>
                <td>
                    <select><c:forEach items="${word.translationList}" var="translation">
                        <option>${translation.translation}</option>
                    </c:forEach>
                    </select>
                </td>
                <td><a href="/words/editTranslation/${word.originValue}">Edit/Delete</a></td>
                <td>${word.dictionary.name}</td>
                <td><a href="/words/edit/${word.originValue}">Edit</a> </td>
                <td><a href="/words/delete/${word.originValue}">Delete</a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<div>
    <p><a href="<c:url value="/add-word"/>">Add</a> word to dictionary</p>
</div>

<div class="ui-autocomplete-category">
    <label for="search">Search</label>
    <input id="search" type="text"/>
</div>

<form:form action="" method="post">

</form:form>

</body>
</html>
