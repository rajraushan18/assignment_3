$(function () {

    //forms
    const fpForm = $("#fpForm");
    const loginForm = $("#loginForm");
    const registrationForm = $("#registrationForm");

    //Msg
    const fpValidationMsg = $("#fpValidationMsg");
    const loginValidationMsg = $("#loginValidationMsg");
    const RegistrationValidationMsg = $("#RegistrationValidationMsg");

    //input form controls
    const registerPass = $("#registerPass");
    const registerRePass = $("#registerRePass");
    const registerFullName = $("#registerFullName");
    const registerUserName = $("#registerUserName");
    const registerEmail = $("#registerEmail");

    $('#login-form-link').click(function (e) {
        $("#loginForm").delay(100).fadeIn(100);
        $("#registrationForm").fadeOut(100);
        $("#fpForm").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $('#forget-pass-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#registrationForm").delay(100).fadeIn(100);
        $("#loginForm").fadeOut(100);
        $("#fpForm").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $('#forget-pass-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

    $('#forget-pass-link').click(function (e) {
        $("#fpForm").delay(100).fadeIn(100);
        $("#loginForm").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

    fpForm.on("submit", function (e) {
        let a = $("#forgotPasswordUserName").val();
        let b = $("#forgotPasswordEmail").val();
        e.preventDefault();
        $.ajax({
            url: "ForgotPass", method: "post", data: {Username: a, email: b}, success: function (data) {
                fpValidationMsg.html(data);
            }
        });
    });

    loginForm.on("submit", function (e) {
        let a = $("#loginUserName").val();
        let b = $("#loginPass").val();
        e.preventDefault();
        $.ajax({
            url: "Login", method: "post", data: {loginuserName: a, loginpass: b}, success: function (data) {
                loginValidationMsg.html(data);
                if (data === "Login successful")
                    window.location.replace("ImageUtility");
            }
        });
    });

    registrationForm.on("submit", function (event) {
        let a = registerFullName.val();
        let b = registerUserName.val();
        let c = registerEmail.val();
        let d = registerPass.val();
        let e = registerRePass.val();

        event.preventDefault();
        RegistrationValidationMsg.css("display", "block");
        if (d !== e) {
            RegistrationValidationMsg.html("Password don't match");
        } else {
            $.ajax({
                url: "RegisterUser",
                method: "post",
                data: {name: a, userName: b, email: c, pass: d},
                success: function (data) {
                    RegistrationValidationMsg.html(data);
                    console.log(data);
                    if (data === "Registered successfully")
                        window.location.replace("ImageUtility");
                }
            });
        }

    });

    function getCookie(cname) {
        let name = cname + "=";
        let ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    function init() {
        let a = getCookie("username");
        if (a !== "")
            window.location.replace("Welcome");
    }

    init();
});