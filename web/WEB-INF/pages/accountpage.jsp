<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/resources/css/accountpage_style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <jsp:include page="clientpage.jsp"/>
    <div id="client-details">
        Client name:<c:out value="${sessionScope.client.displayName}"/><br>
        Client address:<c:out value="${sessionScope.client.address}"/><br>
        Client city:<c:out value="${sessionScope.client.city}"/> / <c:out value="${client.postalCode}"/>
        <form:form action="accountselection" method="post" modelAttribute="account">
            <form:select id="account-popup" path="id">
                <form:options items="${sessionScope.client.setOfAccounts}" itemValue="id" itemLabel="accountNumber" />
            </form:select>
            <input type="submit" class="account-buttons" name="account-buttons" value="Add account">
            <input type="submit" class="account-buttons" name="account-buttons" value="Del account">
            <input type="submit" class="account-buttons" name="account-buttons" value="Edit account">
            <input type="submit" class="account-buttons" name="account-buttons" value="Show account details">
            <input type="submit" class="account-buttons" name="account-buttons" value="Add transaction"><br>
        </form:form>
    </div>
</body>
</html>
