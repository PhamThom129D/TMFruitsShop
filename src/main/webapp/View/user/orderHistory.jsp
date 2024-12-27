<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử mua hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .order-section {
            margin-bottom: 30px;
        }
        .order-section h2 {
            color: #333;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
        }
        .order-list {
            list-style-type: none;
            padding: 0;
        }
        .order-list li {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h1>Lịch sử mua hàng</h1>

<!-- Chờ xác nhận -->
<div class="order-section">
    <h2>Chờ xác nhận</h2>
    <ul class="order-list">
        <c:forEach var="order" items="${pendingOrders}">
            <li>
                <strong>Mã đơn hàng:</strong> ${order.orderID}<br>
                <strong>Ngày đặt:</strong> ${order.orderDate}<br>
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
            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
