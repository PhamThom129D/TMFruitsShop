<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ Hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
    <script src="${pageContext.request.contextPath}/js/cart.js"></script>
</head>
<body>

<div class="container">
    <c:if test="${empty cart}">
        <p>Giỏ hàng của bạn đang trống. Hãy thêm sản phẩm vào giỏ hàng!</p>
    </c:if>

    <!-- Nếu giỏ hàng không rỗng, hiển thị các sản phẩm -->
    <c:if test="${not empty cart}">
        <form action="/cart" method="POST">
            <!-- Thêm class cart-table-container để tạo vùng cuộn -->
            <label>
                <input type="checkbox" id="selectAll" /> Chọn tất cả
            </label>
            <div class="cart-table-container">
                <table class="cart-table">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th></th>
                        <th>Ảnh</th>
                        <th>Tên Sản Phẩm</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Tổng</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cart}" var="item" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>
                                <!-- Checkbox cho từng sản phẩm -->
                                <input type="checkbox" name="selected_${item.productID}" value="${item.productID}" class="product-checkbox" />
                            </td>
                            <td><img src="${item.urlImage}" alt="image Fruit" style="width: 150px; height: 120px"></td>
                            <td>
                                    ${item.productName}
                            </td>
                            <td>
                                <fmt:formatNumber value="${item.price}" type="number" pattern="#,##0" />₫
                            </td>
                            <td>
                                <!-- Sử dụng class quantity và data-price để lắng nghe thay đổi -->
                                <input type="number" class="quantity" name="quantity_${item.productID}"
                                       value="${item.quantity}" min="1" data-price="${item.price}">
                            </td>
                            <td class="total-price">
                                <fmt:formatNumber value="${item.price * item.quantity}" type="number" pattern="#,##0" />₫
                            </td>
                            <td>
                                <a href="/cart?action=removeProduct&id=${item.productID}">
                                    <button type="button">Xóa</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>


        </form>


    </c:if>
</div>
<footer>
    <!-- Tổng tiền -->
    <div class="total">
        <p><strong>Tổng tiền: </strong><span id="totalAmount"></span></p>
    </div>
    <!-- Thanh toán -->
    <div class="checkout">
        <a href="/checkout.jsp"><button class="checkout-btn">Thanh toán</button></a>
    </div>
</footer>


</body>
</html>
