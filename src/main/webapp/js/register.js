let currentStep = 1;

function nextStep() {
    if (validateStep1()) {
        document.getElementById('step-1').style.display = 'none';
        document.getElementById('step-2').style.display = 'block';
        currentStep = 2;
    }
}

function prevStep() {
    if (validateStep2()){
        document.getElementById('step-2').style.display = 'none';
        document.getElementById('step-1').style.display = 'block';
        currentStep = 1;
    }
}

function validateStep1() {
    const password = document.getElementById('password').value;
    const rePassword = document.getElementById('rePassword').value;
    if (password !== rePassword) {
        alert('Mật khẩu nhập lại không khớp!');
        return false;
    }
    return true;
}

function validateStep2() {
    const username = document.querySelector('input[name="username"]').value;
    const phonenumber = document.querySelector('input[name="phonenumber"]').value;
    const address = document.querySelector('input[name="address"]').value;

    const usernameRegex = /^[a-zA-Z0-9_]{3,15}$/;
    const phoneRegex = /^(\\+\\d{1,3}[- ]?)?\\d{10}$/;
    const addressRegex = /^.{5,100}$/;

    if (!usernameRegex.test(username)) {
        alert('Tên người dùng chỉ chứa chữ cái, số, dấu gạch dưới, từ 3 đến 15 ký tự!');
        return false;
    }

    if (!phoneRegex.test(phonenumber)) {
        alert('Số điện thoại không hợp lệ!');
        return false;
    }

    if (!addressRegex.test(address)) {
        alert('Địa chỉ phải từ 5 đến 100 ký tự!');
        return false;
    }

    return true;
}
