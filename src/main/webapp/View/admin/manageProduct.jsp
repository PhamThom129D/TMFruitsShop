<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý sản phẩm</title>
    <link rel="stylesheet" type="text/css" href="/css/manageProduct.css">
</head>
<body>
<div class="container">
    <h1 class="header">Quản lý sản phẩm</h1>
    <div class="actions">
        <a href="/ContentServlet?action=homeAdmin">
            <button type="button" class="btn">Quay lại</button>
        </a>
        <form action="/productAdmin" method="get" class="search-form">
            <input type="hidden" name="action" value="search">
            <input type="text" name="keyword" class="input-search" placeholder="Nhập từ khóa tìm kiếm">
            <input type="submit" class="btn" value="Tìm kiếm">
        </form>
        <a href="/productAdmin?action=showAddProduct">
            <button type="button" class="btn btn-add">Thêm sản phẩm</button>
        </a>
    </div>
    <div class="table-container">
        <table class="product-table">
            <thead>
            <tr>
                <th>STT</th>
                <th>Ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Phân loại</th>
                <th>Mô tả</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td><img src="${product.urlImage}" class="product-image" alt="image"></td>
                    <td>${product.productName}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.type}</td>
                    <td>${product.description}</td>
                    <td>
                        <a href="/productAdmin?action=update&id=${product.productID}" class="link-action">Sửa thông tin</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
