<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bán Hoa Quả</title>
    <!-- Link CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homeUser.css">
</head>
<body>
<header>
    <div class="navbar">
        <!-- Logo -->
        <div class="logo">
            <a href="/user/homeUser.jsp"><img src="images/logo.png" alt="Logo" style="width: 100px; height: 100px"></a>
        </div>
        <!-- Menu -->
        <ul class="menu">
            <li><a href="/user">Trang chủ</a></li>
            <li>
                <div class="search-container">
                    <form action="/user?action=search" method="get">
                        <input type="text" placeholder="Tìm kiếm" name="search">
                        <button type="submit">Tìm kiếm</button>
                    </form>
                </div>
            </li>
            <li><a href="/user?action=showCart">Giỏ hàng</a></li>
            <li><a href="/user?action=showAccount">Tài khoản</a></li>
            <li class="dropdown">
                <a href="#">Sản phẩm</a>
                <ul class="dropdown-content">
                    <li><a href="products.jsp?type=cac-loai">Các loại</a></li>
                    <li><a href="products.jsp?type=combo">Combo</a></li>
                </ul>
            </li>
            <li><a href="/login?action=logout">Đăng xuất</a></li>
        </ul>
    </div>
</header>

<!-- Main Content -->
<main>
    <div class="container">
        <div class="product-list">
            <c:forEach items="${products}" var="product">
                <div class="product-item">
                    <img src="${product.urlImage}" alt="${product.productName}" style="height: 80px; width: 100px">
                    <h3>${product.productName}</h3>
                    <p class="price">
                        <fmt:formatNumber value="${product.price}" type="number" pattern="#,##0" />₫
                    </p>
                    <a href="/cart?action=showProductDetail&id=${product.productID}"><button class="show-detail">Chi tiết sản phẩm</button></a>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<footer>
    <p>&copy; 2024 Bán Hoa Quả. Tất cả các quyền được bảo vệ.</p>
</footer>

</body>
</html>
