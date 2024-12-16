<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: T14
  Date: 16/12/2024
  Time: 4:48 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bán Hoa Quả</title>
    <link rel="stylesheet" href="css/homeUser.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<!-- Header -->
<header>
    <div class="container">
        <div class="logo">
            <h1>Bán Hoa Quả</h1>
        </div>
        <div class="cart">
            <button id="view-cart">Xem Giỏ Hàng (0)</button>
        </div>
    </div>
</header>

<!-- Main Content -->
<main>
    <div class="container">
        <div class="product-list">
            <c:forEach items="${products}" var="product">
                <div class="product-item">
                    <img src="${product.urlImage}" alt="Táo">
                    <h3>${product.productName}</h3>
                    <p class="price">${(product.price)}</p>
                    <button class="add-to-cart" data-name="${product.productName}" data-price="${(product.price)}">Thêm vào giỏ</button>
                </div>
            </c:forEach>


        </div>
    </div>
</main>

<!-- Footer -->
<footer>
    <p>&copy; 2024 Bán Hoa Quả. Tất cả các quyền được bảo vệ.</p>
</footer>

<!-- Modal Giỏ Hàng -->
<div id="cart-modal" class="modal">
    <div class="modal-content">
        <h3>Giỏ Hàng</h3>
        <div id="cart-items"></div>
        <div id="total"></div>
        <button id="checkout">Thanh toán</button>
        <button id="close-cart">Đóng</button>
    </div>
</div>

<script>
    let cart = [];

    // Thêm sản phẩm vào giỏ hàng
    $(".add-to-cart").click(function() {
        const product = {
            name: $(this).data("name"),
            price: $(this).data("price"),
            quantity: 1
        };
        cart.push(product);
        updateCart();
    });

    // Cập nhật giỏ hàng
    // Cập nhật giỏ hàng
    function updateCart() {
        let cartContent = "";
        let total = 0;
        cart.forEach(item => {
            cartContent += `<p>${item.name} - ${item.quantity} x ${(item.price)}</p>`;
            total += item.price * item.quantity;
        });
        $("#cart-items").html(cartContent);
        $("#total").html("Tổng: " + (total));
        $("#view-cart").text(`Xem Giỏ Hàng (${cart.length})`);
    }


    // Hiển thị giỏ hàng
    $("#view-cart").click(function() {
        $("#cart-modal").show();
    });

    // Đóng giỏ hàng
    $("#close-cart").click(function() {
        $("#cart-modal").hide();
    });

    // Thanh toán
    $("#checkout").click(function() {
        alert("Thanh toán thành công!");
        cart = [];
        updateCart();
        $("#cart-modal").hide();
    });
</script>
</body>
</html>

