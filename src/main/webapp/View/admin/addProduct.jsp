<%--
  Created by IntelliJ IDEA.
  User: T14
  Date: 17/12/2024
  Time: 3:24 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<%-- Created by IntelliJ IDEA. User: T14 Date: 17/12/2024 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<form action="/productAdmin?action=add" method="post">
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
            <td>Gía</td>
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
                    <option value="Dưa">Dưa</option>
                    <option value="Nho">Nho</option>
                    <option value="Táo">Táo</option>
                    <option value="Xoài">Xoài</option>
                    <option value="Cam">Cam</option>
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
            <td><input type="submit" value="Add"></td>
        </tr>
    </table>
</form>
</body>
</html>

</body>
</html>
