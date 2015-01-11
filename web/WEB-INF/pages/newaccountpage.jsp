<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title></title>
</head>
<body>
    <b>Current client: <c:out value="${sessionScope.client.displayName}"/></b><br>
    <form:form action="newaccount" method="post" modelAttribute="account">
        <table id="new-account">
            <tbody>
                <form:hidden path="id" value="${account.id}"/>
                <tr>
                    <td>
                        <form:label path="accountNumber">Account number</form:label>
                    </td>
                    <td>
                        <form:input path="accountNumber" value="${account.accountNumber}"></form:input><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="amount">Amount</form:label>
                    </td>
                    <td>
                        <form:input path="amount" value="${account.amount}"></form:input><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="currency">Currency</form:label>
                    </td>
                    <td>
                        <form:select path="currency" itemValue="${account.currency}">
                            <form:option value="USD">USD</form:option>
                            <form:option value="EUR">EUR</form:option>
                            <form:option value="UAH">UAH</form:option>
                        </form:select><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="date">Date</form:label>
                    </td>
                    <td>
                        <form:input path="date" type="date" value="${account.date}"></form:input><br>
                    </td>
                </tr>
            </tbody>
        </table>
        <input type="submit" value="Submit"/>
    </form:form>
    <a href="<c:url value="clientdetails"/>">Back to previous page</a>
</body>
</html>
