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
    <a href="/user">Quay lại trang chủ</a>
    <c:if test="${empty cart}">
        <p>Giỏ hàng của bạn đang trống. Hãy thêm sản phẩm vào giỏ hàng!</p>
    </c:if>

    <c:if test="${not empty cart}">
        <form action="/cart" method="POST">
            <label>
                <input type="checkbox" id="selectAll"/> Chọn tất cả
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
                                <input type="checkbox" name="selected" value="${item.productID}"
                                       class="product-checkbox"/>
                            </td>
                            <td><img src="${item.urlImage}" alt="image Fruit" style="width: 180px; height: 150px"></td>
                            <td>
                                    ${item.productName}
                            </td>
                            <td>
                                <fmt:formatNumber value="${item.price}" type="number" pattern="#,##0"/>₫
                            </td>
                            <td>
                                <input type="number" class="quantity" name="quantity_${item.productID}"
                                       value="${item.quantity}" min="1" max="${product.quantity}"
                                       data-price="${item.price}" required>
                            </td>
                            <td class="total-price">
                                <fmt:formatNumber value="${item.price * item.quantity}" type="number" pattern="#,##0"/>₫
                            </td>
                            <td>
                                <form action="/cart" method="POST">
                                    <input type="hidden" name="action" value="removeProduct">
                                    <input type="hidden" name="id" value="${item.productID}">
                                    <button type="submit" style="background-color: white; border : none">
                                        <img src="/images/trash.png" style="width: 30px; height: 35px"/>
                                    </button>
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>


        </form>

        <footer>
            <div class="total">
                <p><strong>Tổng tiền: </strong><span id="totalAmount"></span></p>
            </div>
            <div class="checkout">
                <c:if test="${not empty cart}">
                    <form action="/user" method="POST">
                        <input type="hidden" name="action" value="checkout">
                        <input type="hidden" id="checkedItemsInput" name="checkedItems" value="[]">
                        <button type="submit" class="checkout-btn">Thanh toán</button>
                    </form>
                </c:if>

            </div>
        </footer>
    </c:if>
</div>

</body>
</html>
