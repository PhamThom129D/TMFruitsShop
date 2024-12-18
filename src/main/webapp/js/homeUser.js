
function formatCurrency(amount) {
    return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."); // Thêm dấu chấm mỗi 3 số
}

$(document).ready(function() {
    // Lặp qua tất cả các giá sản phẩm và định dạng
    $(".price").each(function() {
        var price = parseInt($(this).text());  // Lấy giá từ element
        if (!isNaN(price)) {
            $(this).text(formatCurrency(price) + "₫");  // Định dạng giá và thêm ký hiệu đồng
        }
    });

    // Hiển thị modal khi click vào 'Mua hàng'
    $(".show-detail").click(function () {
        $("#modal-img").attr("src", $(this).data("img"));
        $("#modal-name").text($(this).data("name"));
        $("#modal-price").text(formatCurrency($(this).data("price")));
        $("#modal-description").text($(this).data("description"));
        $("#product-modal").fadeIn();
    });

    // Thêm sản phẩm vào giỏ hàng
    $("#add-to-cart").click(function () {
        const product = {
            name: $("#modal-name").text(),
            price: parseFloat($("#modal-price").text().replace("₫", "").replace(/\./g, "")),
            quantity: parseInt($("#quantity").val())
        };

        let cart = JSON.parse(localStorage.getItem("cart")) || [];

        // Kiểm tra sản phẩm đã tồn tại chưa
        const existing = cart.find(p => p.name === product.name);
        if (existing) {
            existing.quantity += product.quantity;
        } else {
            cart.push(product);
        }

        localStorage.setItem("cart", JSON.stringify(cart));
        alert("Đã thêm vào giỏ hàng!");
        $("#product-modal").fadeOut();
    });

    // Đóng modal
    $(".close").click(function () {
        $("#product-modal").fadeOut();
    });
});
