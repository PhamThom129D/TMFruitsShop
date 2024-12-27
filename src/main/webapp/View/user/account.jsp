<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tài khoản người dùng</title>
    <link rel="stylesheet" href="css/account.css">
</head>
<body>

<!-- Header (Menu User) -->
<header>
    <jsp:include page="${pageContext.request.contextPath}/View/user/menuUser.jsp" />
</header>

<div class="container">
    <aside>
        <div class="sidebar">
            <jsp:include page="${pageContext.request.contextPath}/View/user/menuAccount.jsp" />
        </div>
    </aside>
        <div class="content">
            <div class="user-info">
                <h2>Thông tin tài khoản</h2>
                <table>
                    <tr>
                        <th>Tên người dùng</th>
                        <td>${loggedInUser.username}</td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td>${loggedInUser.email}</td>
                    </tr>
                    <tr>
                        <th>Địa chỉ</th>
                        <td>${loggedInUser.address}</td>
                    </tr>
                    <tr>
                        <th>Số điện thoại</th>
                        <td>${loggedInUser.phonenumber}</td>
                    </tr>
                </table>
            </div>
        </div>
    <div class="avatar">
        <img src="${loggedInUser.urlAvatar}" alt="Avatar">
        <input type="hidden" name="urlAvatar" value="${loggedInUser.urlAvatar}">
    </div>

</div>

</body>
</html>
