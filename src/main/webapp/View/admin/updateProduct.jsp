<%-- Created by IntelliJ IDEA. User: T14 Date: 17/12/2024 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<form action="/productAdmin?action=update" method="post">
    <input type="hidden" name="id" value="${product.productID}">
    <img src="${product.urlImage}" style="height: 120px ; width: 150px" alt="image Fruit">
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
            <td>Gía</td>
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
                    <option value="Dưa" ${product.type == 'Dưa' ? 'selected' : ''}>Dưa</option>
                    <option value="Nho" ${product.type == 'Nho' ? 'selected' : ''}>Nho</option>
                    <option value="Táo" ${product.type == 'Táo' ? 'selected' : ''}>Táo</option>
                    <option value="Xoài" ${product.type == 'Xoài' ? 'selected' : ''}>Xoài</option>
                    <option value="Cam" ${product.type == 'Cam' ? 'selected' : ''}>Cam</option>
                    <option value="Combo" ${product.type == 'Combo' ? 'selected' : ''}>Dau</option>
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
            <td><input type="submit" value="Update"></td>
        </tr>
    </table>
</form>
</body>
</html>
