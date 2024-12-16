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
<div class="container">
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
            <li>Logout</li>
        </ul>
    </div>
    <div class="menu">
        <ul>
            <li>
                <span>User and Roles</span>
                <ul>
                    <li> Thêm người dùng</li>
                    <li>
                        <button onclick="loadContent('user')">Quản lý người dùng</button>
                    </li>
                </ul>
            </li>
            <li>
                <span>Manage Product</span>
                <ul>
                    <li>Thêm sản phẩm</li>
                    <li>
                        <button onclick="loadContent('product')">Quản lý sản phẩm</button>
                    </li>
                </ul>
            </li>

        </ul>
    </div>
    <div class="main">
        <div class="detail-container">
            <h2> Nội dung </h2>
        </div>
        <div class="data-container">

        </div>
    </div>
</div>
</body>
<script>
    function loadContent(page) {
        $.ajax({
            url : 'ContentServlet',
            type : 'GET',
            data : {page : page},
            success : function (resp) {
                $('.data-container').html(resp);
            },
            error : function () {
                $('.data-container').html('<p>Lỗi khi tải nội dung</p>');
            }
        })
    }
</script>
</html>