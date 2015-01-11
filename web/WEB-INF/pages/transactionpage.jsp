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
        <table>
            <tr>
                <td>
                    <b>Account number:</b>
                </td>
                <td>
                    <c:out value="${account.accountNumber}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Account amount:</b>
                </td>
                <td>
                    <c:out value="${account.amount}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Account currency:</b>
                </td>
                <td>
                    <c:out value="${account.currency}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Date of Issue:</b>
                </td>
                <td>
                    <c:out value="${account.date}"/>
                </td>
            </tr>
        </table>
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
