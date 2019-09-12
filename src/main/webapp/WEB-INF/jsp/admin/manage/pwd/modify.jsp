<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-08
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .form-signin {
        width: 100%;
        max-width: 420px;
        padding: 15px;
        margin: 0 auto;
    }

    .form-label-group {
        padding-bottom: 20px;
    }

    #success_view {
        text-align: center;
    }

</style>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <script>
        $(function () {
            let code = "${code}";

            if (code == "1") {
                $("#success_view").show()
                $("[class='form-signin']").hide()
            }
        });

        function changePwd() {
            let newPassword1 = $("#inputNewPassword").val();
            let newPassword2 = $("#inputNewPasswordAgain").val();
            if (newPassword1 != newPassword2) {
                $("#error_msg").html("两次密码输入不一致");
                return false;
            }

            if (newPassword1.length < 6 || newPassword1.length > 18) {
                $("#error_msg").html("密码的长度是 6 ～ 18 位");
                return false;
            }
        }
    </script>
    <div id="success_view" class="hide" >
        <h1>
            修改成功
        </h1>
    </div>
    <div>
        <form class="form-signin" method="post">
            <div class="form-label-group">
                <label for="inputPrePassword">当前密码</label>
                <input name="prePassword" type="password" id="inputPrePassword" class="form-control"
                       placeholder="请输入当前密码" required autofocus>
            </div>

            <div class="form-label-group">
                <label for="inputNewPassword">新密码</label>
                <input name="newPassword" type="password" id="inputNewPassword" class="form-control"
                       placeholder="请输入新密码" required>
            </div>

            <div class="form-label-group">
                <label for="inputNewPasswordAgain">新密码</label>
                <input type="password" id="inputNewPasswordAgain" class="form-control"
                       placeholder="请再次输入新密码" required>
            </div>

            <div id="error_msg" style="color:#e51c23">${errorMsg}</div>

            <button onclick="return changePwd()" class="btn btn-lg btn-primary btn-block" type="submit">修改</button>
        </form>
    </div>
</main>