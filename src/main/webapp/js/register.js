$(function () {
    $("#btn_register").click(function () {
        let inputPassword = $("#inputPassword").val();
        let inputPasswordAgain = $("#inputPasswordAgain").val();
        if (inputPassword != inputPasswordAgain) {
            $("#error_msg").html("两次密码输入不一致");
            return false;
        } else if (inputPassword.length < 6 || inputPassword.length > 18) {
            $("#error_msg").html("密码长度需是6到18位");
            return false;
        } else {
            return true;
        }
    })
})