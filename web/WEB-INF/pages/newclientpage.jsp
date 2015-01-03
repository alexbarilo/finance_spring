<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title></title>
</head>
<body>
    <form:form id="new-client" action="newclient" method="post" modelAttribute="client">
        <form:hidden path="id" value="${client.id}"/>
        <form:label path="firstName">First name</form:label>
        <form:input path="firstName" value="${client.firstName}"></form:input><br>
        <form:label path="lastName">Last name</form:label>
        <form:input path="lastName" value="${client.lastName}"></form:input><br>
        <form:label path="address">Address</form:label>
        <form:input path="address" value="${client.address}"></form:input><br>
        <form:label path="city">City</form:label>
        <form:input path="city" value="${client.city}"></form:input><br>
        <form:label path="postalCode">Postal code</form:label>
        <form:input path="postalCode" vakue="${client.postalCode}"></form:input><br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
