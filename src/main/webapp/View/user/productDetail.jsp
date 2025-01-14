<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Sản Phẩm</title>
    <link rel="stylesheet" href="/css/productDetail.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/View/user/menuUser.jsp" />
<div class="main-container">
    <!-- Container bên trái chứa ảnh sản phẩm -->
    <div class="left-container">
        <div class="product-image">
            <img src="${product.urlImage}" alt="Ảnh sản phẩm">
        </div>
    </div>

    <!-- Container ở giữa chứa thông tin sản phẩm và nút mua hàng -->
    <div class="center-container">
        <div class="product-details">
            <form action="/cart?action=addToCart&id=${product.productID}" method="post">
                <h1 class="product-name">${product.productName}</h1>
                <div style="height: 5px;background-color: #4CAF50;margin: 15px 0 15px 0"></div>
                <p class="product-price">Giá: <fmt:formatNumber value="${product.price}" type="number" pattern="#,###"/>
                    đ</p>
                <p class="product-unit">
                    Đơn vị:
                    <c:if test="${product.type.equals('Combo')}">Combo</c:if>
                    <c:if test="${product.type.equals('Trái cây')}">0.5kg</c:if>
                    <c:if test="${product.type.equals('Rau củ')}">kg</c:if>
                </p>
                <p>Số lượng trong kho : ${product.quantity}</p>
                <p class="product-description">Mô tả : ${product.description}</p>
                <div class="quantity-controls">
                    <label for="quantity">Số lượng: </label>
                    <input type="number" id="quantity" name="quantityBuy" value="1" min="1" max="${product.quantity}"
                           required>
                </div>
                <div style="padding-top: 18px">
                    <label>Xuất xứ: Nhập khẩu</label>
                </div>
                <div style="height: 5px;background-color: #4CAF50; margin-top: 20px;margin-bottom: 20px"></div>
                <div class="add-to-cart-container">
                    <button id="add-to-cart" class="btn-add-to-cart" type="submit">Thêm vào giỏ hàng</button>
                </div>

            </form>
        </div>
    </div>

    <!-- Container bên phải chứa cam kết và thông tin liên hệ -->
    <div class="right-container">
        <div class="commitment">
            <div style="background-color: #4CAF50;height: 35px">
                <h2>CAM KẾT</h2>
            </div>
            <ul>
                <li><img src="/images/star.png" style="width: 25px;height: 25px">Trái cây sạch và tươi ngon</li>
                <li><img src="/images/star.png" style="width: 25px;height: 25px">Không chất bảo quản</li>
                <li><img src="/images/star.png" style="width: 25px;height: 25px">Dịch vụ chuyên nghiệp</li>
                <li><img src="/images/star.png" style="width: 25px;height: 25px">Không biến đổi gen</li>
                <li><img src="/images/star.png" style="width: 25px;height: 25px">Không có hàng Trung Quốc</li>
                <li><img src="/images/star.png" style="width: 25px;height: 25px">Giá cả cạnh tranh</li>
            </ul>
        </div>
        <div class="contact-info">
            <div style="background-color: #4CAF50;height: 35px">
                <h2>Liên hệ tư vấn</h2>
            </div>
            <p>Số điện thoại: 0867536601</p>
            <p>Liên hệ qua Facebook: <a href="https://www.facebook.com/profile.php?id=100047868483999" target="_blank">Facebook</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
