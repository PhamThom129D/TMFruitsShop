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
    <script src="${pageContext.request.contextPath}/js/cart.js">
    </script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/View/user/menuUser.jsp" />
<div class="container">
    <c:if test="${not empty error}">
        <script>
            alert("${error}");
        </script>
    </c:if>
    <c:if test="${empty cart}">
        <script>
            alert("Giỏ hàng của bạn đang trống. Hãy thêm sản phẩm vào giỏ hàng!");
            window.location.href = "/user";
        </script>
    </c:if>

    <c:if test="${not empty cart}">
        <form action="/user?action=checkout" method="post" id="cartForm">
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
                                <input type="checkbox" name="selectedProduct" value="${item.productID}"
                                       class="product-checkbox">
                            </td>
                            <td><img src="${item.urlImage}" alt="image Fruit" style="width: 180px; height: 150px"></td>
                            <td>
                                    ${item.productName}
                            </td>
                            <td>
                                <fmt:formatNumber value="${item.price}" type="number" pattern="#,##0"/>₫
                            </td>
                            <td>
                                <div class="quantity-container">
                                    <button type="button" class="decrease-btn">-</button>
                                    <input type="number" class="quantity" name="quantity_${item.productID}"
                                           value="${item.quantity}" min="1" max="${item.quantity}" data-price="${item.price}" required>
                                    <button type="button" class="increase-btn">+</button>
                                </div>
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

            <footer>
                <div class="total">
                    <p><strong>Tổng tiền: </strong><span id="totalAmount"></span></p>
                </div>
                <div class="checkout">
                    <button class="checkout-btn" type="button" onclick="return confirmOrder()">Đặt hàng</button>
                </div>
            </footer>
        </form>
    </c:if>
</div>

</body>
<script>
    function confirmOrder() {
        const confirmation = confirm("Bạn có chắc chắn muốn đặt hàng không?");
        if (confirmation) {
            document.getElementById("cartForm").submit();
        }
        return false;
    }
</script>

</html>
