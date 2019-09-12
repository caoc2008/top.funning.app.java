$(function () {
    $("#btn-login").click(function () {
        let inputPassword = $("#inputPassword").val();

        if (inputPassword.length < 6 || inputPassword.length > 18) {
            $("#error_msg").html("密码长度需是6到18位");
            return false;
        } else {
            return true;
        }
    })
})