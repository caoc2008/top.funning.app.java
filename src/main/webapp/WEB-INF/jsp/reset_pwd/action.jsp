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

    <title>重置密码</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <!-- Bootstrap core JavaScript
       ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

    <script>window.jQuery || document.write('<script src="https://v4.bootcss.com/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>

    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <!-- Custom styles for this template -->
    <link href="/css/login.css" rel="stylesheet">

    <script type="text/javascript" src="/js/reset_pwd.js"></script>
</head>

<body>
<img class="image-logo" src="/image/logo.png">

<form class="form-signin" method="post">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">重置密码</h1>
    </div>

    <div class="form-label-group">
        <input id="inputPassword" name="password" type="password" class="form-control" placeholder="Password" required>
        <label for="inputPassword">密码</label>
    </div>

    <div class="form-label-group">
        <input id="inputPasswordAgain" name="passwordAgain" type="password" class="form-control"
               placeholder="Password Again" required>
        <label for="inputPasswordAgain">再次输入密码</label>
    </div>

    <div id="error_msg" class="form-error-msg">${errorMsg}</div>

    <button id="btn-reset" class="btn btn-lg btn-primary btn-block" type="submit">修改密码</button>

</form>
</body>
</html>
