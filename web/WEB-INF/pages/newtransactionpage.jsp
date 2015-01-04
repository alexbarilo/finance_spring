<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title></title>
</head>
<body>
    <form:form action="newtransaction" method="post" modelAttribute="transaction">
        <form:label path="benBankName">Beneficiary's bank name</form:label>
        <form:input path="benBankName"></form:input><br>
        <form:label path="benAccountNum">Beneficiary's account number</form:label>
        <form:input path="benAccountNum"></form:input><br>
        <form:label path="benAmount">Amount to remit</form:label>
        <form:input path="benAmount"></form:input><br>
        <form:label path="transactionDate">Date of transaction</form:label>
        <form:input path="transactionDate" type="date"></form:input><br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
