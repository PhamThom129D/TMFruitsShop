<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử mua hàng</title>
    <link rel="stylesheet" href="/css/orderHistory.css">
</head>
<body>
<h1>Lịch sử mua hàng</h1>

<div class="order-section">
    <h2>Chờ xác nhận</h2>
    <ul class="order-list">
        <c:forEach var="order" items="${pendingOrders}">
            <li>
                <strong>Mã đơn hàng:</strong> ${order.orderID}<br>
                <strong>Ngày đặt:</strong> ${order.orderDate}<br>
                <strong>Trạng thái:</strong> ${order.statusOrder}<br>
                <strong>Phương thức thanh toán:</strong> ${order.paymentMethod}<br>
                <strong>Địa chỉ:</strong> ${order.address}<br>

                <ul class="product-list">
                    <c:forEach var="product" items="${order.productList}">
                        <li>
                            <img src="${product.urlImage}" alt="${product.productName}" class="product-image">
                            <div class="product-info">
                                <strong>Tên sản phẩm:</strong> ${product.productName}<br>
                                <strong>Số lượng:</strong> ${product.quantity}<br>
                                <strong>Giá:</strong> ${product.price} VND<br>
                                <strong>Loại:</strong> ${product.type}<br>
                                <p>${product.description}</p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>
</div>

<!-- Đã duyệt đơn -->
<div class="order-section">
    <h2>Đã duyệt đơn</h2>
    <ul class="order-list">
        <c:forEach var="order" items="${approvedOrders}">
            <li>
                <strong>Mã đơn hàng:</strong> ${order.orderID}<br>
                <strong>Ngày duyệt:</strong> ${order.orderDate}<br>
                <strong>Trạng thái:</strong> ${order.statusOrder}<br>
                <strong>Phương thức thanh toán:</strong> ${order.paymentMethod}<br>
                <strong>Địa chỉ:</strong> ${order.address}<br>

                <ul class="product-list">
                    <c:forEach var="product" items="${order.productList}">
                        <li>
                            <img src="${product.urlImage}" alt="${product.productName}" class="product-image">
                            <strong>Tên sản phẩm:</strong> ${product.productName}<br>
                            <strong>Số lượng:</strong> ${product.quantity}<br>
                            <strong>Giá:</strong> ${product.price} VND<br>
                            <strong>Loại:</strong> ${product.type}<br>
                            <strong>Mô tả:</strong> ${product.description}<br>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>
</div>

<!-- Đã hủy -->
<div class="order-section">
    <h2>Đã hủy</h2>
    <ul class="order-list">
        <c:forEach var="order" items="${canceledOrders}">
            <li>
                <strong>Mã đơn hàng:</strong> ${order.orderID}<br>
                <strong>Ngày hủy:</strong> ${order.orderDate}<br>
                <strong>Trạng thái:</strong> ${order.statusOrder}<br>
                <strong>Phương thức thanh toán:</strong> ${order.paymentMethod}<br>
                <strong>Địa chỉ:</strong> ${order.address}<br>

                <ul class="product-list">
                    <c:forEach var="product" items="${order.productList}">
                        <li>
                            <img src="${product.urlImage}" alt="${product.productName}" class="product-image">
                            <strong>Tên sản phẩm:</strong> ${product.productName}<br>
                            <strong>Số lượng:</strong> ${product.quantity}<br>
                            <strong>Giá:</strong> ${product.price} VND<br>
                            <strong>Loại:</strong> ${product.type}<br>
                            <strong>Mô tả:</strong> ${product.description}<br>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
