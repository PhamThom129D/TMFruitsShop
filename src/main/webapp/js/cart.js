document.addEventListener('DOMContentLoaded', function () {
    const selectAllCheckbox = document.getElementById('selectAll');
    const totalAmountElement = document.getElementById('totalAmount');

    function updateTotalAmount() {
        let totalAmount = 0;
        document.querySelectorAll('.product-checkbox:checked').forEach(checkbox => {
            const row = checkbox.closest('tr');
            const price = parseFloat(row.querySelector('.quantity').getAttribute('data-price'));
            const quantity = parseInt(row.querySelector('.quantity').value, 10);
            const totalPrice = price * quantity;

            row.querySelector('.total-price').innerText = totalPrice.toLocaleString('vi-VN') + '₫';
            totalAmount += totalPrice;
        });
        totalAmountElement.innerText = totalAmount.toLocaleString('vi-VN') + '₫';
    }

    selectAllCheckbox.addEventListener('change', function () {
        const checkboxes = document.querySelectorAll('.product-checkbox');
        checkboxes.forEach(checkbox => checkbox.checked = selectAllCheckbox.checked);
        updateTotalAmount();
    });

    document.querySelectorAll('.product-checkbox').forEach(checkbox => {
        checkbox.addEventListener('change', updateTotalAmount);
    });

    document.querySelectorAll('.quantity').forEach(input => {
        input.addEventListener('input', function () {
            const row = input.closest('tr');
            const price = parseFloat(input.getAttribute('data-price'));
            const quantity = parseInt(input.value, 10);

            const totalPrice = price * quantity;
            row.querySelector('.total-price').innerText = totalPrice.toLocaleString('vi-VN') + '₫';

            updateTotalAmount();
        });
    });

    const cartForm = document.getElementById('cartForm');
    cartForm.addEventListener('click', function (event) {
        const target = event.target;

        if (target.classList.contains('decrease-btn')) {
            const input = target.nextElementSibling;
            const currentValue = parseInt(input.value, 10);
            if (currentValue > parseInt(input.min, 10)) {
                input.value = currentValue - 1;
                input.dispatchEvent(new Event('input')); // Trigger input event to update the total amount
            }
        }

        if (target.classList.contains('increase-btn')) {
            const input = target.previousElementSibling;
            input.value = parseInt(input.value, 10) + 1;
            input.dispatchEvent(new Event('input')); // Trigger input event to update the total amount
        }
    });
});
