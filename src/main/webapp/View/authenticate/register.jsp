<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký</title>
    <link rel="stylesheet" href="/css/login.css">
    <script src="/js/register.js"></script>
</head>
<body class="container">
<div class="login-box">
    <h1>Đăng ký</h1>
    <form id="register-form" action="/login?action=register" method="post" onsubmit="return validateForm()">
        <div id="step-1" class="step">
            <div class="user-box">
                <input type="text" id="urlAvatar" name="urlAvatar" required>
                <label>Avatar</label>
                <span id="urlAvatar-message" class="error-message"></span>
            </div>
            <div class="user-box">
                <input type="email" id="email" name="email" required>
                <label>Email</label>
                <span id="email-message" class="error-message"></span>
            </div>
            <div class="user-box">
                <input type="password" id="password" name="password" required>
                <label>Mật khẩu</label>
                <span id="password-message" class="error-message"></span>
            </div>
            <div class="user-box">
                <input type="password" id="rePassword" name="rePassword" required>
                <label>Nhập lại mật khẩu</label>
                <span id="rePassword-message" class="error-message"></span>
            </div>
            <button type="button" onclick="nextStep()">Tiếp tục</button>
        </div>

        <!-- Step 2 -->
        <div id="step-2" class="step" style="display: none;">
            <div class="user-box">
                <input type="text" name="username" required>
                <label>Tên người dùng</label>
                <span id="username-message" class="error-message"></span>
            </div>
            <div class="user-box">
                <input type="tel" name="phonenumber" required>
                <label>Số điện thoại</label>
                <span id="phone-message" class="error-message"></span>
            </div>
            <div class="user-box">
                <input type="text" name="address" required>
                <label>Địa chỉ</label>
                <span id="address-message" class="error-message"></span>
            </div>
            <button type="submit">Đăng ký</button>
            <button type="button" onclick="prevStep()">Quay lại</button>
        </div>
    </form>
    <a href="/login?action=redirectLogin" class="sign-up-btn">
        Đã có tài khoản? Đăng nhập
    </a>
</div>
</body>
</html>
