<%-- 
    Document   : updateEmployee.jsp
    Created on : 17-Nov-2026, 7:27:19 pm
    Author     : akki
--%>


<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
</head>

<body>

<h2>Update Employee Details</h2>

<form action="${pageContext.request.contextPath}/employee/update"
      method="post">

    <input type="hidden"
           name="employeeId"
           value="${employee.employeeId}">

    Employee Name:
    <input type="text"
           name="employeeName"
           value="${employee.employeeName}"
           required>
    <br><br>

    Email:
    <input type="email"
           name="email"
           value="${employee.email}"
           required>
    <br><br>

    Phone:
    <input type="text"
           name="phone"
           value="${employee.phone}"
           required>
    <br><br>

    Gender:
    <input type="text"
           name="gender"
           value="${employee.gender}"
           required>
    <br><br>

    Department ID:
    <input type="number"
           name="departmentId"
           value="${employee.departmentId}"
           required>
    <br><br>

    Job ID:
    <input type="number"
           name="jobId"
           value="${employee.jobId}"
           required>
    <br><br>

    Salary:
    <input type="number"
           name="salary"
           value="${employee.salary}"
           min="0"
           step="0.01"
           required>
    <br><br>

    Joining Date:
    <input type="date"
           name="joiningDate"
           value="${employee.joiningDate}"
           required>
    <br><br>

    <button type="submit">Update Employee</button>

</form>

</body>
</html>

