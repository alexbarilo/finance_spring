<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <link href="/resources/css/clientpage_style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <form:form action="clientselection" method="post" modelAttribute="client">
        <div id="client-buttons">
            <input type="submit" class="client-button" name="client-action" value="Add Client"/>
            <input type="submit" class="client-button" name="client-action" value="Del Client"/>
            <input type="submit" class="client-button" name="client-action" value="Edit Client"/><br>
            <input type="submit" class="show-client-button" name="client-action" value="Show client details"/>
        </div>
        <div id="clients-list">
            <form:select cssClass="clients-list" path="id" size="25">
                <form:options items="${listOfClients}" itemValue="id" itemLabel="displayName"/>
            </form:select>
        </div>
    </form:form>
</body>
</html>
