<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <title>Home Admin</title>
    <link rel="stylesheet" href="/css/homeAdmin.css">
</head>
<body>
<div class="container">
    <div class="menu-info">
        <div>
            <img src="${sessionScope.user.urlAvatar}" alt="Lỗi ảnh">
        </div>
        <span>
            <div>
                <p>Name: ${sessionScope.user.username}</p>
                <p>Email: ${sessionScope.user.email}</p>
            </div>
        </span>
        <div class="menu-account">
            <a href="#">
                <button type="submit">Thông tin tài khoản</button>
            </a>
            <br>
            <a href="/login?action=logout">
                <button style="width: 140px" type="submit">Đăng xuất</button>
            </a>
        </div>
    </div>
    <div class="menu-manage">
        <a href="/ContentServlet?action=redirectUser">
            <button type="submit">Quản lý người dùng</button>
        </a>
        <a href="/ContentServlet?action=redirectProduct">
            <button type="submit">Quản lý sản phẩm</button>
        </a>
        <a href="/ContentServlet?action=redirectOrder">
            <button type="submit">Quản lý đơn hàng</button>
        </a>
    </div>
</div>
</body>
</html>
