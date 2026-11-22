<%-- 
    Document   : viewEmployees.jsp
    Created on : 17-Nov-2026, 7:26:02 pm
    Author     : akki
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Records</title>

    <style>
        body {
            font-family: Arial;
            margin: 30px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        th {
            background: #2563eb;
            color: white;
        }
    </style>
</head>

<body>

<h2>Employee Details</h2>

<c:if test="${param.success == 'added'}">
    <p style="color:green">Employee added successfully!</p>
</c:if>

<c:if test="${param.success == 'updated'}">
    <p style="color:green">Employee updated successfully!</p>
</c:if>

<c:if test="${param.success == 'deleted'}">
    <p style="color:green">Employee deleted successfully!</p>
</c:if>

<a href="${pageContext.request.contextPath}/addEmployee.jsp">
    Add New Employee
</a>

<br><br>

<table>

    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Gender</th>
        <th>Department ID</th>
        <th>Job ID</th>
        <th>Salary</th>
        <th>Joining Date</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="e" items="${employees}">

        <tr>

            <td>${e.employeeId}</td>
            <td>${e.employeeName}</td>
            <td>${e.email}</td>
            <td>${e.phone}</td>
            <td>${e.gender}</td>
            <td>${e.departmentId}</td>
            <td>${e.jobId}</td>
            <td>${e.salary}</td>
            <td>${e.joiningDate}</td>

            <td>

                <a href="${pageContext.request.contextPath}/employee/edit?id=${e.employeeId}">
                    Edit
                </a>

                <form action="${pageContext.request.contextPath}/employee/delete"
                      method="post">

                    <input type="hidden"
                           name="employeeId"
                           value="${e.employeeId}">

                    <button type="submit"
                            onclick="return confirm('Delete this employee?')">
                        Delete
                    </button>

                </form>

            </td>

        </tr>

    </c:forEach>

</table>

<br>

<a href="${pageContext.request.contextPath}/dashboard.jsp">
    Back to Dashboard
</a>

</body>
</html>

