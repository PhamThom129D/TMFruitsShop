<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đơn đặt hàng</title>
    <link rel="stylesheet" type="text/css" href="/css/order.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/View/user/menuUser.jsp" />
<div class="container">
    <h1>Chi tiết đơn đặt hàng</h1>

    <div class="order-info">
        <p><label>Mã đơn hàng:</label> ${orderID}</p>
        <p><label>Tên người nhận:</label> ${loggedInUser.username}</p>
        <p><label>Địa chỉ:</label> ${loggedInUser.address}</p>
        <p><label>Số điện thoại:</label> ${loggedInUser.phonenumber}</p>
        <p><label>Ngày đặt hàng:</label> ${orderDate}</p>
    </div>

    <table>
        <tr>
            <th>STT</th>
            <th>Ảnh sản phẩm</th>
            <th>Tên sản phẩm</th>
            <th>Số lượng</th>
            <th>Giá</th>
            <th>Thành tiền</th>
        </tr>
        <c:forEach items="${orderList}" var="item" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td><img src="${item.urlImage}" alt="image Fruit"></td>
                <td>${item.productName}</td>
                <td>${item.quantity}</td>
                <td>
                    <fmt:formatNumber value="${item.price}" type="number" pattern="#,##0"/>đ
                </td>
                <td>
                    <fmt:formatNumber value="${item.price * item.quantity}" type="number" pattern="#,##0"/>đ
                </td>
            </tr>
        </c:forEach>
    </table>

    <h3>Tổng tiền: <fmt:formatNumber value="${totalAmount}" type="number" pattern="#,##0"/>đ</h3>
    <form action="/user?action=payOrder" method="POST">
        <input type="hidden" name="orderID" value="${orderID}">
        <div class="payment-method">
            <label>Phương thức thanh toán:
                <select name="paymentMethod">
                    <option value="COD">Thanh toán sau khi nhận hàng</option>
                    <option value="BANKING">Chuyển khoản ngân hàng</option>
                </select>
            </label>
        </div>
        <div class="button-group">
            <button type="submit">Thanh toán</button>
            <a href="/user?action=cancelOrder&orderID=${orderID}"><button type="button">Hủy đơn</button></a>
        </div>
    </form>

</div>
</body>
</html>
