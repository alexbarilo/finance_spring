<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <link href="/resources/css/transactionpage_style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <jsp:include page="accountpage.jsp"/>
    <div id="account-details">
        Account number: <c:out value="${account.accountNumber}"/><br>
        Account amount: <c:out value="${account.amount}"/><br>
        Account currency: <c:out value="${account.currency}"/><br>
        Date of Issue: <c:out value="${account.date}"/><br>
    </div>
    <div id="transaction-list">
        <table id="transactions-table">
        <table-head>
            <tr>
                <th>Beneficiary's account number</th>
                <th>Beneficiary's bank name</th>
                <th>Remitted amount</th>
                <th>Date of transaction</th>
            </tr>
        </table-head>
        <table-body>
            <c:forEach items="${account.setOfTransactions}" var="item">
                <tr>
                    <td>${item.benAccountNum}</td>
                    <td>${item.benBankName}</td>
                    <td>${item.benAmount}</td>
                    <td>${item.transactionDate}</td>
                </tr>
            </c:forEach>
        </table-body>
        </table>
    </div>
</body>
</html>
