<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <title>Home Admin</title>
    <link rel="stylesheet" href="/css/sidebar.css">
</head>
<body>
<!-- Sidebar -->
<div class="sidebar">
    <div class="sidebar-header">
        <img src="${sessionScope.user.urlAvatar}" alt="Lỗi ảnh" class="avatar">
        <div class="user-info">
            <p class="username">${sessionScope.user.username}</p>
            <p class="email">${sessionScope.user.email}</p>
        </div>
    </div>
    <div class="sidebar-menu">
        <a href="/ContentServlet?action=redirectUser">
            <button type="button">Quản lý người dùng</button>
        </a>
        <a href="/ContentServlet">
            <button type="button">Quản lý sản phẩm</button>
        </a>
        <a href="/ContentServlet?action=redirectOrder">
            <button type="button">Quản lý đơn hàng</button>
        </a>
        <a href="/login?action=logout">
            <button type="button" class="logout-btn">Đăng xuất</button>
        </a>
    </div>
</div>

<div class="main-content">
</div>
</body>
</html>
