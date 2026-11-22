<%-- 
    Document   : dashboard.jsp
    Created on : 17-Nov-2026, 7:22:47 pm
    Author     : akki
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.username}">
    <c:redirect url="login.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Dashboard</title>
</head>

<body>

<h1>Employee Management System</h1>

<h2>
    Welcome, ${sessionScope.username}
</h2>

<hr>

<a href="${pageContext.request.contextPath}/employee/list">
    View Employees
</a>

<br><br>

<a href="addEmployee.jsp">
    Add Employee
</a>

<br><br>

<a href="logout">
    Logout
</a>

</body>
</html>

