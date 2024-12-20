// Hàm để cập nhật tổng tiền khi thay đổi số lượng hoặc checkbox
function updateTotalPrice() {
    var total = 0;
    // Lặp qua tất cả các checkbox và kiểm tra nếu chúng được chọn
    var selectedItems = document.querySelectorAll('.product-checkbox:checked');

    selectedItems.forEach(function (checkbox) {
        var row = checkbox.closest('tr');
        var price = parseFloat(row.querySelector('.quantity').getAttribute('data-price'));
        var quantity = parseInt(row.querySelector('.quantity').value);
        var totalPrice = price * quantity;

        // Cập nhật giá trị của cột "Tổng"
        var totalCell = row.querySelector('.total-price');
        totalCell.textContent = totalPrice.toLocaleString('vi-VN') + '₫';

        // Cộng tổng vào
        total += totalPrice;
    });

    // Cập nhật tổng tiền vào phần tổng
    document.getElementById('totalAmount').textContent = total.toLocaleString('vi-VN') + '₫';
}

// Lắng nghe sự thay đổi khi người dùng thay đổi số lượng, checkbox hoặc trạng thái "Chọn tất cả"
document.addEventListener('DOMContentLoaded', function () {
    var quantityInputs = document.querySelectorAll('.quantity');
    quantityInputs.forEach(function (input) {
        input.addEventListener('input', updateTotalPrice);
    });

    var checkboxes = document.querySelectorAll('.product-checkbox');
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', updateTotalPrice);
    });

    // Lắng nghe sự thay đổi của checkbox "Chọn tất cả"
    document.getElementById("selectAll").addEventListener("change", function () {
        var checkboxes = document.querySelectorAll(".product-checkbox");
        checkboxes.forEach(function (checkbox) {
            checkbox.checked = document.getElementById("selectAll").checked;
        });
        updateTotalPrice();
    });

    // Cập nhật tổng tiền ngay khi trang được tải
    updateTotalPrice();
});
