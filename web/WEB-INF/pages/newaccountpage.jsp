<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title></title>
</head>
<body>
    Current client: <c:out value="${sessionScope.client.displayName}"/><br>
    <form:form action="newaccount" method="post" modelAttribute="account">
        <form:hidden path="id" value="${account.id}"/>
        <form:label path="accountNumber">Account number</form:label>
        <form:input path="accountNumber" value="${account.accountNumber}"></form:input><br>
        <form:label path="amount">Amount</form:label>
        <form:input path="amount" value="${account.amount}"></form:input><br>
        <form:label path="currency">Currency</form:label>
        <form:select path="currency" itemValue="${account.currency}">
            <form:option value="USD">USD</form:option>
            <form:option value="EUR">EUR</form:option>
            <form:option value="UAH">UAH</form:option>
        </form:select><br>
        <form:label path="date"><Date></Date></form:label>
        <form:input path="date" type="date" value="${account.date}"></form:input><br>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>
