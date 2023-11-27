/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


/* global firebase */

// Khởi tạo Firebase
var firebaseConfig = {
//    apiKey: "AIzaSyAx7ov7ujohRV_L00O61zcByk46964b-74",
//    authDomain: "flm-g3.firebaseapp.com",
//    projectId: "flm-g3",
//    storageBucket: "flm-g3.appspot.com",
//    messagingSenderId: "689184383903",
//    appId: "1:689184383903:web:3f77f8f8efb06a6974d3aa",
//    measurementId: "G-KHC5HN0EC3"

    apiKey: "AIzaSyB2xs-xs50NQF9ngkv_yHZf1sAHuCpTE1M",
    authDomain: "deep-dynamics-387110.firebaseapp.com",
    projectId: "deep-dynamics-387110",
    storageBucket: "deep-dynamics-387110.appspot.com",
    messagingSenderId: "611071181550",
    appId: "1:611071181550:web:ce1614ed387d138fb5a9ff",
    measurementId: "G-V87K31NZWB"

//    apiKey: "AIzaSyAh197NSOD59vr8BE73gNSIINADM05FkSA",
//    authDomain: "elemental-alloy-387110.firebaseapp.com",
//    projectId: "elemental-alloy-387110",
//    storageBucket: "elemental-alloy-387110.appspot.com",
//    messagingSenderId: "839881426214",
//    appId: "1:839881426214:web:6af89e84450fe0fc2c2de7",
//    measurementId: "G-C1YVE6KH8S"
};

firebase.initializeApp(firebaseConfig);

// Tạo đối tượng xác thực số điện thoại
var auth = firebase.auth();

// Hàm gửi mã xác thực OTP
function sendOTP() {
    document.getElementById("recaptcha-container").style.display = 'inline-block';
    document.getElementById("recaptcha-container").innerHTML = '';
    var appVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container');
    var phoneNumber = document.getElementById("mobile").value;
    document.getElementById("btn-send-otp").value = 'click';

    phoneNumber = "+84" + phoneNumber.slice(1);
    auth.signInWithPhoneNumber(phoneNumber, appVerifier)
            .then(function (confirmationResult) {

                // Lưu trữ thông tin xác thực để sử dụng trong hàm xác thực OTP
                window.confirmationResult = confirmationResult;
                document.getElementById("recaptcha-container").style.display = 'none';
            })
            .catch(function (error) {
                console.error("Lỗi khi gửi mã xác thực:", error);
                document.getElementById("err-otp").innerHTML = 'Error sending verification code!';
            });
}
function confirmOTP() {
    var verificationCode = document.getElementById("code").value;
    window.confirmationResult.confirm(verificationCode)
            .then(function (result) {
                // Xác thực OTP thành công, result.user chứa thông tin người dùng
                document.getElementById("registerForm").submit();
            })
            .catch(function (error) {
                console.error("Lỗi khi xác thực OTP:", error);
                if (error.message === 'The SMS code has expired. Please re-send the verification code to try again.') {
                    document.getElementById("err-otp").innerHTML = 'The SMS code has expired.';
                } else {
                    document.getElementById("err-otp").innerHTML = 'Verification code is invalid.';
                }
            });
}

function checkEmptyRegister() {
    var check = false;
    // check phone 
    if (document.getElementById('mobile').value === '') {
        document.getElementById("err-phone").innerHTML = 'Not be empty!';
        check = true;
    } else {
        document.getElementById("err-phone").innerHTML = '';
    }
    // check otp 
    if (document.getElementById('code').value === '') {
        document.getElementById("err-otp").innerHTML = 'Not be empty!';
        check = true;
    } else {
        document.getElementById("err-otp").innerHTML = '';
    }
    // check pass 
    if (document.getElementById('newPass').value === '') {
        document.getElementById("err-pass").innerHTML = 'Not be empty!';
        check = true;
    } else {
        document.getElementById("err-pass").innerHTML = '';
    }
    // check re-pass 
    if (document.getElementById('reNewPass').value === '') {
        document.getElementById("err-repass").innerHTML = 'Not be empty!';
        check = true;
    } else {
        document.getElementById("err-repass").innerHTML = '';
    }
    if (check) {
        return false;
    } else {
        return true;
    }
}

function checkConfirmPassword() {
    var password = document.getElementById("newPass").value;
    var confirmPassword = document.getElementById("reNewPass").value;
//    var regexPassword = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,15})$/;
//    if (!password.match(regexPassword)) {
//        document.getElementById("err-pass").innerHTML = "Must contain at least one number and one uppercase and lowercase letter, and at least 8 characters!";
//        return false;
//    }
    if (password !== confirmPassword) {
        document.getElementById("err-repass").innerHTML = "Re-New Password does not match!";
        return false;
    } else {
        document.getElementById("err-repass").innerHTML = '';
    }
    return true;
}

function checkRregister() {
    if (checkEmptyRegister() === true) {
        var check = true;
        var checkSendCode = document.getElementById("btn-send-otp").value;
        if (checkSendCode === 'noclick') {
            document.getElementById("err-otp").innerHTML = 'You have not clicked on Send Code';
            check=false;
        }
//        if (document.getElementById("checkCode").value === 'falseee') {
//            alert("checkkkkk");
//            check = false;
//        }
        if (checkConfirmPassword() === true && check) {
            confirmOTP();
        }
//        alert(document.getElementById("checkCode").value);
//        if (check) {
////            document.getElementById("registerForm").submit();
//        }
    }
}