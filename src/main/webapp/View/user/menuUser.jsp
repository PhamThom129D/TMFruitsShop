<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="css/homeUser.css">
<head>
    <title>Title</title>
    <script type="text/javascript">
        // Kiểm tra nếu có thông báo lỗi hoặc thông báo từ servlet
        <c:if test="${not empty error}">
        alert("${error}");
        window.location.href = "/user";
        </c:if>

        <c:if test="${not empty message}">
        alert("${message}");
        window.location.href = "/user";
        </c:if>
    </script>
</head>
<body>
<header>
    <div class="navbar">
        <!-- Logo -->
        <div class="menu-product">
            <a href="/user">Trang chủ</a>
            <li class="dropdown">
                <a href="/user">Sản phẩm</a>
                <ul class="dropdown-content">
                    <li><a href="/user?action=searchWithType&type=fruits">Trái cây</a></li>
                    <li><a href="/user?action=searchWithType&type=vegetables">Rau củ</a></li>
                    <li><a href="/user?action=searchWithType&type=combo">Combo</a></li>
                </ul>
            </li>
        </div>

        <!-- Search -->
        <div class="search-container">
            <form action="/user?action=searchProduct" method="post">
                <input type="text" placeholder="Tìm kiếm sản phẩm" name="keyword">
            </form>
        </div>

        <!-- Menu -->
        <ul class="menu">
            <li>
                <a href="/user?action=showCart" style="position: relative;">
                    <img src="/images/cart.png" alt="Gio hang" style="width: 50px; height: 45px">
                    <c:if test="${not empty sessionScope.cartItemCount and sessionScope.cartItemCount > 0}">
                        <span style="position: absolute; top: -5px; right: -5px; background-color: red; color: white; border-radius: 50%; padding: 3px 7px; font-size: 12px; font-weight: bold;">
                                ${sessionScope.cartItemCount}
                        </span>
                    </c:if>
                </a>
            </li>
            <li>
                <div class="image-user">
                    <button class="dropdown-button">
                        <a href="/user?action=showAccount">
                            <img src="${loggedInUser.urlAvatar}" alt="avt"
                                 style="width: 60px; height: 60px; border-radius: 50%;">
                        </a>
                    </button>
                    <div class="menu-info">
                        <a href="/user?action=showAccount">Thông tin tài khoản</a>
                        <a href="/user?action=showOrderHistory">Lịch sử mua hàng</a>
                        <a href="/login?action=logout">
                            <button type="submit" onclick="confirmLogout()" style="background-color: transparent; border: none;font-size: 18px">Đăng xuất
                            </button>
                        </a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</header>
</body>
</html>
