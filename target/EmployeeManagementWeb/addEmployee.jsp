<%-- 
    Document   : addEmployee.jsp
    Created on : 17-Nov-2026, 7:23:28 pm
    Author     : akki
--%>


<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>
</head>

<body>

<h2>Add New Employee</h2>

<p style="color:red">${error}</p>

<form action="employee/add" method="post"
      onsubmit="return validateEmployee()">

    Employee Name:
    <input type="text" name="employeeName"
           id="employeeName" required>
    <br><br>

    Email:
    <input type="email" name="email"
           id="email" required>
    <br><br>

    Phone:
    <input type="text" name="phone"
           id="phone" maxlength="11" required>
    <br><br>

    Gender:
    <select name="gender" required>
        <option value="">Select Gender</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
    </select>
    <br><br>

    Department ID:
    <input type="number" name="departmentId"
           min="1" required>
    <br><br>

    Job ID:
    <input type="number" name="jobId"
           min="1" required>
    <br><br>

    Salary:
    <input type="number" name="salary"
           min="0" step="0.01" required>
    <br><br>

    Joining Date:
    <input type="date" name="joiningDate" required>
    <br><br>

    <button type="submit">Add Employee</button>

</form>

<script>
function validateEmployee() {

    let name =
        document.getElementById("employeeName").value.trim();

    let email =
        document.getElementById("email").value.trim();

    let phone =
        document.getElementById("phone").value.trim();

    if (name.length < 3) {
        alert("Name must contain at least 3 characters.");
        return false;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        alert("Enter a valid email.");
        return false;
    }

    if (!/^[0-9]{10,11}$/.test(phone)) {
        alert("Enter a valid 10 or 11 digit phone number.");
        return false;
    }

    return true;
}
</script>

</body>
</html>

