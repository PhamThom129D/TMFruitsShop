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
<jsp:include page="${pageContext.request.contextPath}/View/user/menuUser.jsp" />
<%-- --%>
<!-- Main Content -->
<main>
    <c:if test="${not empty error}">
        alert("Vui lòng đăng nhập để mua hàng");
        window.location.href = "/login";
    </c:if>
    <div class="container">
        <div class="product-list">
            <c:forEach items="${products}" var="product">
                <div class="product-item">
                    <img src="${product.urlImage}" alt="${product.productName}">
                    <h3>${product.productName}</h3>
                    <c:if test="${product.quantity == 0}">
                        <span style="color: red">Hết hàng</span>
                    </c:if>
                    <c:if test="${product.quantity > 0}">
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
                    </c:if>
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
