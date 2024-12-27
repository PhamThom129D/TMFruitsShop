<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" type="text/css" href="/css/addProduct.css">
</head>
<body>
<form action="ContentProduct" method="post">
    <h1>Thêm Sản Phẩm</h1>
    <table>
        <tr>
            <td>Ảnh</td>
            <td><input type="text" name="image"></td>
        </tr>

        <tr>
            <td>Tên sản phẩm</td>
            <td><input type="text" name="name"></td>
        </tr>

        <tr>
            <td>Gíá</td>
            <td><input type="text" name="price"></td>
        </tr>

        <tr>
            <td>Số lượng</td>
            <td><input type="text" name="quantity"></td>
        </tr>

        <tr>
            <td>Loại sản phẩm</td>
            <td>
                <select name="type">
                    <option value="Hoa quả">Hoa Quả</option>
                    <option value="Rau củ">Rau Củ</option>
                    <option value="Combo">Combo</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>Mô tả</td>
            <td>
                <textarea name="description" rows="5" cols="30"></textarea>
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Thêm sản phẩm"></td>
        </tr>
    </table>
</form>
</body>
</html>
