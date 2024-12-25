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
            <a href="/user"><img src="images/logo.png" alt="Logo" style="width: 100px; height: 100px"></a>
        </div>
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
                            <button type="submit" onclick="confirmLogout()" style="background-color: transparent;
                            border: none; color: white; font-size: 15px;">Đăng xuất
                            </button>
                        </a>
                    </div>
                </div>
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
                    <img src="${product.urlImage}" alt="${product.productName}" style="height: 150px; width: 180px">
                    <h3>${product.productName}</h3>
                    <p style="font-size: small">Số lượng : ${product.quantity}</p>
                    <p class="price">
                        <fmt:formatNumber value="${product.price}" type="number" pattern="#,##0" />₫
                    </p>
                    <a href="/cart?action=showProductDetail&id=${product.productID}">
                        <button class="show-detail">Chi tiết sản phẩm</button>
                    </a>
                    <form action="/cart" method="POST">
                        <input type="hidden" name="action" value="addToCart">
                        <input type="hidden" name="id" value="${product.productID}">
                        <input type="hidden" name="quantityBuy" value="1">
                        <button type="submit" class="add-to-cart">Mua ngay</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<footer>
    <p>&copy; 2024 Bán Hoa Quả. Tất cả các quyền được bảo vệ.</p>
</footer>

<script type="text/javascript">
    function confirmLogout(event) {
        var result = confirm("Bạn chắc chắn muốn đăng xuất?\n Dữ liệu mua hàng của bạn sẽ mất sau 1 giờ bạn đăng xuất.");
        if (result) {
            event.target.submit();
        } else {
            event.preventDefault();
        }
    }
</script>
</body>
</html>
