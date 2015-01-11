<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
    <form:form action="newtransaction" method="post" modelAttribute="transaction">
        <table>
            <tr>
                <td>
                    <form:label path="benBankName">Beneficiary's bank name</form:label>
                </td>
                <td>
                    <form:input path="benBankName"></form:input><br>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="benAccountNum">Beneficiary's account number</form:label>
                </td>
                <td>
                    <form:input path="benAccountNum"></form:input><br>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="benAmount">Amount to remit</form:label>
                </td>
                <td>
                    <form:input path="benAmount"></form:input><br>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="transactionDate">Date of transaction</form:label>
                </td>
                <td>
                    <form:input path="transactionDate" type="date"></form:input><br>
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit">
    </form:form>
    <a href="<c:url value="accountdetails"/>">Back to the previous page</a>
</body>
</html>
