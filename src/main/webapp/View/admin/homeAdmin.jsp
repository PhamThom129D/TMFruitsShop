<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomeAdmin</title>
    <link rel="stylesheet" href="css/homeAdmin.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="header">
    <c:if test="${sessionScope.user != null}">
        <ul class="user-info">
            <li class="user-info-item">
                <img src="${sessionScope.user.urlAvatar}" class="user-avatar" alt="image">
            </li>
            <li class="user-info-item">${sessionScope.user.username}</li>
            <li class="user-info-item">${sessionScope.user.email}</li>
        </ul>
    </c:if>

    <ul class="user-settings">
        <li>profile</li>
        <li>Setting</li>
        <li><a href="/?action=logout">Đăng xuất</a></li>
    </ul>
</div>
<div>
    <ul class="menu">
        <li><a href="/ContentServlet?action=redirectUser">Quản lý người dung</a></li>
        <li><a href="/ContentServlet?action=redirectProduct">Quản lý sản phẩm</a></li>
        <li><a href="/ContentServlet?action=redirectOrder">Quản lý đơn hàng</a></li>
    </ul>
</div>

</body>
</html>
