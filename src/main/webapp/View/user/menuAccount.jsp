<%--
  Created by IntelliJ IDEA.
  User: T14
  Date: 26/12/2024
  Time: 2:14 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<style>

    .menuInfo a {
        display: block;
        text-decoration: none;
        color: #333; /* Màu chữ */
        padding: 10px;
        margin: 20px 0;
        border-radius: 5px;
        background-color: transparent;
        transition: background-color 0.3s;
    }

    /* Hiệu ứng hover khi di chuột qua */
    .menuInfo a:hover {
        background-color: #f3cfcf;
    }


    .menuInfo ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
    }

</style>

<div class="menuInfo">
    <ul>
        <li><a href="account">Hồ sơ tài khoản</a></li>
        <li><a href="bankAccount">Tài khoản ngân hàng</a> </li>
        <li><a href="password">Đổi mật khẩu</a></li>
        <li><a href="address">Thông tin địa chỉ</a> </li>
        <li><a href="deleteAccount">Những thiết lập riêng tư</a></li>
    </ul>
</div>
</body>
</html>
