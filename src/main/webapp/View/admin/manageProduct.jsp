<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: T14
  Date: 16/12/2024
  Time: 8:00 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý sản phẩm</title>
</head>
<body>
<div>
    <h1>Quản lý sản phẩm</h1>
    <form action="/productAdmin" method="get">
        <input type="hidden" name="action" value="search">
        <input type="text" name="keyword" placeholder="Nhập từ khóa tìm kiếm">
        <input type="submit" value="Tìm kiếm">
    </form>
    <div>
        <table border="1">
            <tr>
                <td>STT</td>
                <td>Ảnh</td>
                <td>Tên sản phẩm</td>
                <td>Giá</td>
                <td> Số lượng</td>
                <td>Phân loại</td>
                <td> Mô tả</td>
                <td>Hành động</td>
            </tr>
            <c:forEach var="product" items="${products}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td><img src="${product.urlImage}" style="width: 80px ; height: 60px" alt="image Fruit"></td>
                    <td>${product.productName}</td>
                    <td>${product.price}</td>
                    <td>
                        <c:if test="${product.quantity == 0}">
                            <span style="color: red">Hết hàng</span>
                        </c:if>
                        <c:if test="${product.quantity > 0}">
                            <span style="color: green">${product.quantity}</span>
                        </c:if>
                    </td>
                    <td>${product.type}</td>
                    <td>${product.description}</td>
                    <td>
                        <a href="/productAdmin?action=update&id=${product.productID}" methods="GET">Sửa thông
                            tin</a><br>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
