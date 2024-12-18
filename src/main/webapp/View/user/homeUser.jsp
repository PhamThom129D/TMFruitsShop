<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<!-- Header với Menu Bar -->
<header>
    <div class="navbar">
        <!-- Logo -->
        <div class="logo">
            <a href="/user/homeUser.jsp"><img src="images/logo.png" alt="Logo" style="width: 200px;height: 250px"></a>
        </div>
        <!-- Menu -->
        <ul class="menu">
            <li><a href="home.jsp">Trang chủ</a></li>
            <li><a href="search.jsp">Tìm kiếm sản phẩm</a></li>
            <li><a href="/user?action=showCart">Giỏ hàng</a></li>
            <li><a href="orders.jsp">Đơn hàng</a></li>
            <li><a href="account.jsp">Tài khoản</a></li>
            <li class="dropdown">
                <a href="#">Sản phẩm</a>
                <ul class="dropdown-content">
                    <li><a href="products.jsp?type=cac-loai">Các loại</a></li>
                    <li><a href="products.jsp?type=combo">Combo</a></li>
                </ul>
            </li>
        </ul>
    </div>
</header>

<!-- Main Content -->
<main>
    <div class="container">
        <div class="product-list">
            <c:forEach items="${products}" var="product">
                <div class="product-item">
                    <img src="${product.urlImage}" alt="${product.productName}">
                    <h3>${product.productName}</h3>
                    <p class="price">${product.price}₫</p>
                    <button class="show-detail"
                            data-name="${product.productName}"
                            data-price="${product.price}"
                            data-img="${product.urlImage}"
                            data-description="${product.description}">Mua hàng</button>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<!-- Modal Chi Tiết Sản Phẩm -->
<div id="product-modal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <img id="modal-img" src="" alt="Product Image">
        <h3 id="modal-name"></h3>
        <p>Giá: <span id="modal-price"></span>₫</p>
        <p>Đơn vị : kg</p>
        <p>Mô tả: <span id="modal-description"></span></p>
        <label for="quantity">Số lượng:</label>
        <input type="number" id="quantity" value="1" min="1">
        <button id="add-to-cart">Thêm vào giỏ hàng</button>
    </div>
</div>

<!-- Footer -->
<footer>
    <p>&copy; 2024 Bán Hoa Quả. Tất cả các quyền được bảo vệ.</p>
</footer>

<!-- Link JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/homeUser.js"></script>
</body>
</html>
