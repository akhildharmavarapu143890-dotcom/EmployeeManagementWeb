<%-- 
    Document   : login.jsp
    Created on : 17-Nov-2026, 7:21:46 pm
    Author     : akki
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Management Login</title>

    <style>
        body {
            font-family: Arial;
            background: #eef2f7;
            text-align: center;
            padding-top: 100px;
        }

        .box {
            background: white;
            padding: 30px;
            width: 320px;
            margin: auto;
            border-radius: 10px;
        }

        input {
            padding: 12px;
            margin: 10px;
            width: 85%;
        }

        button {
            background: #2563eb;
            color: white;
            padding: 12px;
            width: 95%;
            border: none;
        }

        .error {
            color: red;
        }
    </style>
</head>

<body>

<div class="box">

    <h2>Employee Management System</h2>

    <h3>Admin Login</h3>

    <p class="error">${error}</p>

    <form action="login" method="post"
          onsubmit="return validateLogin()">

        <input type="text"
               name="username"
               id="username"
               placeholder="Enter Username"
               required>

        <input type="password"
               name="password"
               id="password"
               placeholder="Enter Password"
               required>

        <button type="submit">Login</button>

    </form>

</div>

<script>
function validateLogin() {

    let username =
        document.getElementById("username").value.trim();

    let password =
        document.getElementById("password").value;

    if (username === "" || password === "") {
        alert("All fields are required.");
        return false;
    }

    return true;
}
</script>

</body>
</html>
