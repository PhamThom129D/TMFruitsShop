<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body class="container">
<div class="login-box">
    <h1>Đăng nhập</h1>
    <form action="/login?action=login" method="post">
        <div class="user-box">
            <input type="text" name="email" required>
            <label>Email</label>
        </div>
        <div class="user-box">
            <input type="password" id="password" name="password" required>
            <label>Mật khẩu</label>
            <img id="toggle-password" src="/images/eye-closed.png" onclick="togglePassword()">
        </div>

        <div>
            <span id="error-message" class="error-message">
                <%
                    String errorMessage = (String) session.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                    <%= errorMessage %>
                <%
                        session.removeAttribute("errorMessage");
                    }
                %>
            </span>
        </div>

        <button type="submit" name="submit">Đăng nhập</button>
    </form>
    <a href="/login?action=redirectRegister" class="sign-up-btn" methods="GET">
        Chưa có tài khoản? Đăng ký
    </a>
</div>
</body>
<script>

    function togglePassword() {
        var passwordField = document.getElementById("password");
        var toggleIcon = document.getElementById("toggle-password");
        if (passwordField.type === "password") {
            passwordField.type = "text";
            toggleIcon.src = "/images/eye-open.png";
        } else {
            passwordField.type = "password";
            toggleIcon.src = "/images/eye-closed.png";
        }
    }

</script>
</html>
