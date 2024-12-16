<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body class="container">
<div class="login-box">
    <h1 style="color: white">Đăng nhập</h1>
    <form action="/login?action=login" method="post">
        <div class="user-box">
            <input type="text" name="email" required>
            <label>Email</label>
        </div>
        <div class="user-box">
            <input type="password" name="password" required>
            <label>Mật khẩu</label>
        </div>
        <button type="submit" name="submit" >Đăng nhập</button>
    </form>
    <a href="/login?action=redirectRegister" class="sign-up-btn" methods="GET">Chưa có tài khoản? Đăng ký</a>
</div>
</body>
</html>
