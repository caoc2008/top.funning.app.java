<%@ page import="top.knxy.library.utils.LogUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="icon" href="/image/favicon.ico">
    <title>Admin</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="/admin/css/common.css" rel="stylesheet">

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

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
        feather.replace()
    </script>

    <script src="/js/common.js"></script>
    <script src="/admin/js/index.js"></script>
</head>


<body>

<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/admin/admin/index">饭宁</a>
    <div class="w-100"></div>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" style="color:#fff">${adminName}</a>
        </li>
    </ul>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" onclick="Common.exit()" href="#">退出</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <script>
            $(function () {
                let page = window.document.location.pathname;
                console.log(page);
                $("[href='" + page + "']").addClass("active");
            })
        </script>
        <!-- https://www.flaticon.com/packs/ui-ux-interface -->
        <audio id="sound_remind" class="hide" auto src="/admin/res/remind.mp3"></audio>
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item title">
                        <a class="nav-link">
                            <object data="/admin/image/sidebar/order.svg"
                                    type="image/svg+xml"
                                    codebase="http://www.adobe.com/svg/viewer/install/"></object>
                            订单
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/admin/order/list/undo">
                            未处理列表 <span id="normal_red_point" class="red_point hide"></span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/order/list">
                            <span data-feather="file"></span>
                            所有订单列表
                        </a>
                    </li>
                    <li class="nav-item line">
                        <a class="nav-link" href="/admin/order/search">
                            <span data-feather="file"></span>
                            订单详情
                        </a>
                    </li>
                    <li class="nav-item title">
                        <a class="nav-link">
                            <object data="/admin/image/sidebar/good.svg"
                                    type="image/svg+xml"
                                    codebase="http://www.adobe.com/svg/viewer/install/"></object>
                            商品
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/good/list">
                            <span data-feather="shopping-cart"></span>
                            所有商品列表<br/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/good/add">
                            <span data-feather="shopping-cart"></span>
                            添加商品<br/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/good/search">
                            <span data-feather="shopping-cart"></span>
                            商品详情<br/>
                        </a>
                    </li>

                    <li class="nav-item line">
                        <a class="nav-link" href="/admin/good/type/list">
                            <span data-feather="shopping-cart"></span>
                            分组管理<br/><!-- 商品类型列表 -->
                        </a>
                    </li>

                    <li class="nav-item title">
                        <a class="nav-link">

                            <object data="/admin/image/sidebar/operate.svg"
                                    type="image/svg+xml"
                                    codebase="http://www.adobe.com/svg/viewer/install/"></object>
                            用户
                        </a>
                    </li>
                    <li class="nav-item line">
                        <a class="nav-link" href="/admin/user/list">
                            <span data-feather="shopping-cart"></span>
                            消费者列表<br/>
                        </a>
                    </li>


                    <li class="nav-item title">
                        <a class="nav-link">
                            <object data="/admin/image/sidebar/manager.svg"
                                    type="image/svg+xml"
                                    codebase="http://www.adobe.com/svg/viewer/install/"></object>
                            管理
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/manage/pwd/modify">
                            <span data-feather="shopping-cart"></span>
                            修改密码<br/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/manage/wechatmini">
                            <span data-feather="shopping-cart"></span>
                            小程序信息<br/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/manage/poster">
                            <span data-feather="shopping-cart"></span>
                            修改首页海报<br/>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <jsp:include page="/WEB-INF/jsp/${viewJsp}"></jsp:include>
    </div>
</div>

<div id="LoadingDialog" class="modal" tabindex="-1" role="dialog" aria-hidden="true">
    <view class="lv_floater">
        <div class="lv_container">
            <div class="lv_load_view">
                <view class="lv_point lv_point1"></view>
                <view class="lv_point lv_point2"></view>
                <view class="lv_point lv_point3"></view>
            </div>
            <div class="lv_msg"></div>
        </div>
    </view>
</div>
</body>

</html>