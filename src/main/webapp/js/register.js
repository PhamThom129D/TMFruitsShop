let currentStep = 1;

function nextStep() {
    if (validateStep1()) {
        document.getElementById('step-1').style.display = 'none';
        document.getElementById('step-2').style.display = 'block';
        currentStep = 2;
    }
}

function prevStep() {
    document.getElementById('step-2').style.display = 'none';
    document.getElementById('step-1').style.display = 'block';
    currentStep = 1;
}

function validateStep1() {
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();
    const rePassword = document.getElementById('rePassword').value.trim();

    // Regex cho email và password
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,10}$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,20}$/;

    // Kiểm tra email
    const emailField = document.getElementById('email');
    const emailMessage = document.getElementById('email-message');
    if (!emailRegex.test(email)) {
        emailField.style.borderColor = 'red';
        emailMessage.textContent = 'Email không hợp lệ! Hãy nhập đúng định dạng email.';
        emailMessage.style.color = 'red';
        return false;
    } else {
        emailField.style.borderColor = 'green';
        emailMessage.textContent = 'Email hợp lệ';
        emailMessage.style.color = 'green';
    }

    // Kiểm tra password
    const passwordField = document.getElementById('password');
    const passwordMessage = document.getElementById('password-message');
    if (!passwordRegex.test(password)) {
        passwordField.style.borderColor = 'red';
        passwordMessage.textContent = 'Mật khẩu phải từ 8-20 ký tự \n Chứa ít nhất 1 chữ cái hoa, 1 chữ cái thường và 1 số.';
        passwordMessage.style.color = 'red';
        return false;
    } else {
        passwordField.style.borderColor = 'green';
        passwordMessage.textContent = 'Mật khẩu hợp lệ';
        passwordMessage.style.color = 'green';
    }

    // Kiểm tra khớp mật khẩu
    const rePasswordField = document.getElementById('rePassword');
    const rePasswordMessage = document.getElementById('rePassword-message');
    if (password !== rePassword) {
        rePasswordField.style.borderColor = 'red';
        rePasswordMessage.textContent = 'Mật khẩu nhập lại không khớp!';
        rePasswordMessage.style.color = 'red';
        return false;
    } else {
        rePasswordField.style.borderColor = 'green';
        rePasswordMessage.textContent = 'Mật khẩu khớp';
        rePasswordMessage.style.color = 'green';
    }

    return true;
}

function validateStep2() {
    const username = document.querySelector('input[name="username"]').value;
    const phonenumber = document.querySelector('input[name="phonenumber"]').value;
    const address = document.querySelector('input[name="address"]').value;

    // Regex cho các trường Step 2
    const usernameRegex = /^[a-zA-Z0-9_]{3,15}$/;
    const phoneRegex = /^(\+\d{1,3}[- ]?)?\d{10}$/;
    const addressRegex = /^.{5,100}$/;

    // Kiểm tra tên người dùng
    const usernameField = document.querySelector('input[name="username"]');
    const usernameMessage = document.getElementById('username-message');
    if (!usernameRegex.test(username)) {
        usernameField.style.borderColor = 'red';
        usernameMessage.textContent = 'Tên người dùng chỉ chứa chữ cái, số, dấu gạch dưới, từ 3 đến 15 ký tự!';
        usernameMessage.style.color = 'red';
        return false;
    } else {
        usernameField.style.borderColor = 'green';
        usernameMessage.textContent = 'Tên người dùng hợp lệ';
        usernameMessage.style.color = 'green';
    }

    // Kiểm tra số điện thoại
    const phoneField = document.querySelector('input[name="phonenumber"]');
    const phoneMessage = document.getElementById('phone-message');
    if (!phoneRegex.test(phonenumber)) {
        phoneField.style.borderColor = 'red';
        phoneMessage.textContent = 'Số điện thoại không hợp lệ!';
        phoneMessage.style.color = 'red';
        return false;
    } else {
        phoneField.style.borderColor = 'green';
        phoneMessage.textContent = 'Số điện thoại hợp lệ';
        phoneMessage.style.color = 'green';
    }

    // Kiểm tra địa chỉ
    const addressField = document.querySelector('input[name="address"]');
    const addressMessage = document.getElementById('address-message');
    if (!addressRegex.test(address)) {
        addressField.style.borderColor = 'red';
        addressMessage.textContent = 'Địa chỉ phải từ 5 đến 100 ký tự!';
        addressMessage.style.color = 'red';
        return false;
    } else {
        addressField.style.borderColor = 'green';
        addressMessage.textContent = 'Địa chỉ hợp lệ';
        addressMessage.style.color = 'green';
    }

    return true;
}

function validateForm() {
    return validateStep2();
}
