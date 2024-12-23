function updateTotalPrice() {
    var total = 0;
    var selectedItems = document.querySelectorAll('.product-checkbox:checked');

    selectedItems.forEach(function (checkbox) {
        var row = checkbox.closest('tr');
        var price = parseFloat(row.querySelector('.quantity').getAttribute('data-price'));
        var quantity = parseInt(row.querySelector('.quantity').value);
        var totalPrice = price * quantity;

        var totalCell = row.querySelector('.total-price');
        totalCell.textContent = totalPrice.toLocaleString('vi-VN') + '₫';

        total += totalPrice;

        var productID = row.querySelector('.product-id').value;
        setCheckedStatus(productID, true);
    });

    var unselectedItems = document.querySelectorAll('.product-checkbox:not(:checked)');
    unselectedItems.forEach(function (checkbox) {
        var row = checkbox.closest('tr');a
        var productID = row.querySelector('.product-id').value;
        setCheckedStatus(productID, false);
    });

    document.getElementById('totalAmount').textContent = total.toLocaleString('vi-VN') + '₫';
}

function setCheckedStatus(productID, isChecked) {
    var cart = JSON.parse(sessionStorage.getItem('cart')) || [];
    var product = cart.find(item => item.productID === productID);

    if (product) {
        product.isChecked = isChecked;
    }

}
