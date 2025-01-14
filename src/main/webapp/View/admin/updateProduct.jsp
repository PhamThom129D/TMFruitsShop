<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="/css/addProduct.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/View/admin/sidebar.jsp" />

<form action="/productAdmin?action=update" method="post">
    <input type="hidden" name="id" value="${product.productID}">
    <img src="${product.urlImage}" style="height: 180px ; width: 180px ; margin-left: 215px"  alt="image Fruit">
    <table>
        <tr>
            <td>Ảnh</td>
            <td><input type="text" name="image" value="${product.urlImage}"></td>
        </tr>

        <tr>
            <td>Tên sản phẩm</td>
            <td><input type="text" name="name" value="${product.productName}"></td>
        </tr>

        <tr>
            <td>Giá</td>
            <td><input type="text" name="price" value="${product.price}"></td>
        </tr>

        <tr>
            <td>Số lượng</td>
            <td><input type="text" name="quantity" value="${product.quantity}"></td>
        </tr>

        <tr>
            <td>Loại sản phẩm</td>
            <td>
                <select name="type">
                    <option value="Trái cây" ${product.type == 'Trái cây' ? 'selected' : ''}>Trái cây</option>
                    <option value="Rau củ" ${product.type == 'Rau củ' ? 'selected' : ''}>Rau củ</option>
                    <option value="Combo" ${product.type == 'Combo' ? 'selected' : ''}>Combo</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>Mô tả</td>
            <td>
                <textarea name="description" rows="5" cols="30">${product.description}</textarea>
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Sửa sản phẩm"></td>
        </tr>
    </table>
</form>
</body>
</html>
