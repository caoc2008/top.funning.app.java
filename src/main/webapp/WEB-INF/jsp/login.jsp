<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-01-31
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/image/favicon.ico">

    <title>登录</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">


    <!-- Custom styles for this template -->
    <link href="/css/login.css?v=${version}" rel="stylesheet">

</head>

<body>

<img class="image-logo" src="/image/logo.png">

<form class="form-signin" method="post">

    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">登录到饭宁</h1>
    </div>
    <div class="form-label-group">
        <input name="username" value="${username}" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputEmail">电子邮箱地址</label>
    </div>

    <div class="form-label-group">
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <label for="inputPassword">密码</label>
    </div>

    <div class="form-error-msg">${errorMsg}</div>

    <button id="btn-login" class="btn btn-lg btn-primary btn-block" type="submit">登录</button>

    <div class="form-register" ><a href="reset_pwd">忘记密码</a> <a href="register">立刻注册</a></div>
</form>
</body>
</html>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/login.js?v=${version}"></script>