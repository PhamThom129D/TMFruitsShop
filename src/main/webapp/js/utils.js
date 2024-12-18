// Hàm định dạng tiền tệ Việt Nam
function formatCurrency(amount) {
    return amount.toLocaleString('vi-VN') + ' VND';
}

// Để có thể sử dụng lại hàm này trong nhiều tệp khác
export { formatCurrency };
