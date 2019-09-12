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



    <!-- Custom styles for this template -->
    <link href="/css/login.css?v=${version}" rel="stylesheet">



</head>

<body>
<img class="image-logo" src="/image/logo.png">

<form class="form-signin" method="post">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">重置密码</h1>
    </div>

    <p>查看您的邮箱，点击我们发给您的链接，然后修改密码。</p>

    <button onclick="javascrtpt:window.location.href='login'" target="_self" id="btn_send_email" class="btn btn-lg btn-primary btn-block" type="button">返回登录页</button>

</form>
</body>
</html>
