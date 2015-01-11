<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/resources/css/clientpage_style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <form:form id="new-client" action="newclient" method="post" modelAttribute="client">
        <table>
            <form:hidden path="id" value="${client.id}"/>
            <tr>
                <td>
                    <form:label path="firstName">First name</form:label>
                </td>
                <td>
                    <form:input path="firstName" value="${client.firstName}"></form:input>
                    <form:errors path="firstName" cssClass="error"></form:errors><br>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="lastName">Last name</form:label>
                </td>
                <td>
                    <form:input path="lastName" value="${client.lastName}"></form:input>
                    <form:errors path="lastName" cssClass="error"></form:errors><br>
                </td>
            </tr>
            <tr>\
                <td>
                    <form:label path="address">Address</form:label>
                </td>
                <td>
                    <form:input path="address" value="${client.address}"></form:input>
                    <form:errors path="address" cssClass="error"></form:errors><br>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="city">City</form:label>
                </td>
                <td>
                    <form:input path="city" value="${client.city}"></form:input>
                    <form:errors path="city" cssClass="error"></form:errors><br>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="postalCode">Postal code</form:label>
                </td>
                <td>
                    <form:input path="postalCode" vakue="${client.postalCode}"></form:input>
                    <form:errors path="postalCode" cssClass="error"></form:errors><br>
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit">
    </form:form>
    <a href="<c:url value="/"/>">Back to the previous page</a>
</body>
</html>
