<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dictionary</title>
</head>
<body>
<h3>Dictionary</h3>
<br/>
<a href="<c:url value="/words-list"/>">Show words list</a>
<a href="<c:url value="/create-dictionary"/>">Create dictionary</a>
<%--<a href="<c:url value="/dictionary/create"/>" target="_blank">Create new dictionary</a>--%>
</body>
</html>
