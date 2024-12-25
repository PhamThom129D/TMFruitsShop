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

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,10}$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,20}$/;

    const emailField = document.getElementById('email');
    const emailMessage = document.getElementById('email-message');
    if (!emailRegex.test(email)) {
        emailField.style.borderColor = 'red';
        emailMessage.textContent = 'Hãy nhập đúng định dạng email.\n Ví dụ : vykid9@gmail.com';
        emailMessage.style.color = 'red';
        return false;
    } else {
        emailField.style.borderColor = ' rgb(255, 200, 47)';
        emailMessage.textContent = 'Email hợp lệ';
        emailMessage.style.color = ' rgb(255, 200, 47)';
    }

    const passwordField = document.getElementById('password');
    const passwordMessage = document.getElementById('password-message');
    if (!passwordRegex.test(password)) {
        passwordField.style.borderColor = 'red';
        passwordMessage.textContent = 'Mật khẩu phải từ 8-20 ký tự \n Chứa ít nhất 1 chữ cái hoa, 1 chữ cái thường và 1 số.';
        passwordMessage.style.color = 'red';
        return false;
    } else {
        passwordField.style.borderColor = ' rgb(255, 200, 47)';
        passwordMessage.textContent = 'Mật khẩu hợp lệ';
        passwordMessage.style.color = ' rgb(255, 200, 47)';
    }

    const rePasswordField = document.getElementById('rePassword');
    const rePasswordMessage = document.getElementById('rePassword-message');
    if (password !== rePassword) {
        rePasswordField.style.borderColor = 'red';
        rePasswordMessage.textContent = 'Mật khẩu nhập lại không khớp!';
        rePasswordMessage.style.color = 'red';
        return false;
    } else {
        rePasswordField.style.borderColor = ' rgb(255, 200, 47)';
        rePasswordMessage.textContent = 'Mật khẩu khớp';
        rePasswordMessage.style.color = ' rgb(255, 200, 47)';
    }

    return true;
}

function validateStep2() {
    const username = document.querySelector('input[name="username"]').value;
    const phonenumber = document.querySelector('input[name="phonenumber"]').value;
    const address = document.querySelector('input[name="address"]').value;
    const urlAvatar = document.getElementById('urlAvatar').value.trim();

    const usernameRegex = /^[a-zA-Z0-9_]{3,15}$/;
    const phoneRegex = /^(0[3-9]{1}[0-9]{8})$/;
    const addressRegex = /^.{5,100}$/;

    if (!validateImageUrl(urlAvatar)) {
        const urlAvatarField = document.getElementById('urlAvatar');
        const urlAvatarMessage = document.getElementById('urlAvatar-message');
        urlAvatarField.style.borderColor = 'red';
        urlAvatarMessage.textContent = 'Định dạng URL ảnh không hợp lệ!';
        urlAvatarMessage.style.color = 'red';
        return false;
    } else {
        const urlAvatarField = document.getElementById('urlAvatar');
        const urlAvatarMessage = document.getElementById('urlAvatar-message');
        urlAvatarField.style.borderColor = ' rgb(255, 200, 47)';
        urlAvatarMessage.textContent = 'URL ảnh hợp lệ';
        urlAvatarMessage.style.color = ' rgb(255, 200, 47)';
    }

    const usernameField = document.querySelector('input[name="username"]');
    const usernameMessage = document.getElementById('username-message');
    if (!usernameRegex.test(username)) {
        usernameField.style.borderColor = 'red';
        usernameMessage.textContent = 'Tên người dùng chỉ chứa chữ cái, số, dấu gạch dưới \n Từ 3 đến 15 ký tự!';
        usernameMessage.style.color = 'red';
        return false;
    } else {
        usernameField.style.borderColor = ' rgb(255, 200, 47)';
        usernameMessage.textContent = 'Tên người dùng hợp lệ';
        usernameMessage.style.color = ' rgb(255, 200, 47)';
    }

    const phoneField = document.querySelector('input[name="phonenumber"]');
    const phoneMessage = document.getElementById('phone-message');
    if (!phoneRegex.test(phonenumber)) {
        phoneField.style.borderColor = 'red';
        phoneMessage.textContent = 'Số điện thoại không hợp lệ! \n Số điện thoại gồm 10 số và bắt đầu bằng số 0';
        phoneMessage.style.color = 'red';
        return false;
    } else {
        phoneField.style.borderColor = ' rgb(255, 200, 47)';
        phoneMessage.textContent = 'Số điện thoại hợp lệ';
        phoneMessage.style.color = ' rgb(255, 200, 47)';
    }

    const addressField = document.querySelector('input[name="address"]');
    const addressMessage = document.getElementById('address-message');
    if (!addressRegex.test(address)) {
        addressField.style.borderColor = 'red';
        addressMessage.textContent = 'Địa chỉ phải từ 5 đến 100 ký tự!';
        addressMessage.style.color = 'red';
        return false;
    } else {
        addressField.style.borderColor = ' rgb(255, 200, 47)';
        addressMessage.textContent = 'Địa chỉ hợp lệ';
        addressMessage.style.color = ' rgb(255, 200, 47)';
    }

    return true;
}

function validateImageUrl(url) {
    const imageUrlRegex = /^(https?:\/\/.*\.(?:png|jpg|jpeg|gif|bmp|webp))$/;
    return imageUrlRegex.test(url);
}

function validateForm() {
    function validateForm() {
        if (validateStep2()) {
            alert("Đăng ký thành công. Mời đăng nhập lại!");
            return true;
        }
        return false;
    }

}
