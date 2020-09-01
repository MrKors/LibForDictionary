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
    <script src="/resources/static/js/filterWords-ajax.js"></script>
    <title>Words list</title>
</head>
<body>
<div>
    <a href="../../index.jsp">Back to main page</a>
</div>

<div>
    <h1>Words list</h1>
</div>

<div>
    <form:form action="/word-data" method="post">
        <div>
            <label for="filterDictionary">Dictionary for search: </label>
            <select id="filterDictionary" name="filterDictionary">
                <option disabled selected>Choose dictionary</option>
                <c:forEach items="${dictionaryList}" var="dictionaryName">
                    <option value="${dictionaryName.id}">${dictionaryName.name}</option>
                </c:forEach>
                <option value="0">All dictionaries</option>
            </select>
        </div>
        <div>
            <label for="search">Search</label>
            <input id="search" name="originValue" type="text"/>
            <input id="searchBtn" type="submit" value="show">
        </div>
    </form:form>
</div>


<div id="div-table">
    <c:if test="${!empty wordsList}">
        <table border="1" cellspacing="1" cellpadding="2">
            <tr>
                <th rowspan="2">Origin Value</th>
                <th rowspan="2">Translation</th>
                <th colspan="2">Action for translation</th>
                <th rowspan="2">Dictionary</th>
                <th rowspan="2">Delete word</th>
            </tr>
            <tr>
                <th>Edit/Delete</th>
                <th>Add</th>
            </tr>
            <c:forEach items="${wordsList}" var="word">
                <tr>
                    <td><a href="/word-data/${word.originValue}">${word.originValue}</a></td>
                    <td>
                        <c:if test="${!empty word.translationList}">
                            <select><c:forEach items="${word.translationList}" var="translation">
                                <option>${translation.translation}</option>
                            </c:forEach>
                            </select>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${!empty word.translationList}">
                            <a href="/words/editTranslation/${word.originValue}">Edit/Delete</a>
                        </c:if>
                    </td>
                    <td><a href="/words/addTranslation/${word.originValue}">Add</a></td>
                    <td>${word.dictionary.name}</td>
                    <td><a href="/words/delete/${word.originValue}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div>
    <p><a href="<c:url value="/addWord"/>">Add</a> word to dictionary</p>
</div>



</body>
</html>
