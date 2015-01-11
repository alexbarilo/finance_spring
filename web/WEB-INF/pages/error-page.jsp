<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
    <c:out value="${requestScope.message}"/><br>
    <a href="<c:url value="${requestScope.action}"/>">Back to the previous page</a>
</body>
</html>
