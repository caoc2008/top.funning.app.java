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
    <meta name="author" content="">
    <link rel="icon" href="/image/favicon.ico">
    <title>饭宁 | 开源的小程序商城</title>
    <meta name="description" content="开源免费的小程序商城">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <!-- Custom styles for this template -->
    <link href="/css/index.css?v=${version}" rel="stylesheet">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top">
        <div class="container">
            <a class="navbar-brand" href="#">饭宁小程序商城</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul style="width: 100%" class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">首页<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#service">其他服务</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#about">关于我们</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#contact">联系方式</a>
                    </li>

                    <li class="nav-btn">
                        <a class="nav-link" href="login">登录</a><a class="nav-link" href="register">注册</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<div class="container-fluid grey_part home">
    <div class="container">
        <div class="row content">
            <div class="col-md-7 left">
                <h1>开源的小程序商城</h1>
                <p class="lead">java,MySQL,Redis,Spring boot<br/>欢迎参与项目，贡献源码.</p>
                <div class="github_button">
                    <!-- -->
                    <button onclick="javascrtpt:window.open('https://github.com/MrNeptune/top.funning.app.mini')"
                            type="button"
                            class="btn btn-warning btn-lg">
                        <svg height="25" viewBox="0 0 16 16" version="1.1" width="25" aria-hidden="true">
                            <path fill-rule="evenodd"
                                  d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z">
                            </path>
                        </svg>
                        <span>微信小程序源码</span>
                    </button>
                    <!---->
                    <button onclick="javascrtpt:window.open('https://github.com/MrNeptune/top.funning.app.java')"
                            type="button"
                            class="btn btn-warning btn-lg">
                        <svg height="25" viewBox="0 0 16 16" version="1.1" width="25" aria-hidden="true">
                            <path fill-rule="evenodd"
                                  d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z">
                            </path>
                        </svg>
                        <span>服务端源码</span>
                    </button>
                </div>
                <br/><br/><br/>

                <h2>查看详情</h2>
                <img style="width: 200px; height:200px;" src="http://image.www.funning.top/image/qr_code.png"/>

                <br/><br/><br/>
                <a class="lead" target="_blank" href="login">服务端体验地址</a>
            </div>
            <div class="right col-md-5">
                <img class="phone" src="http://image.www.funning.top/image/phone.png"/>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div id="demo" class="carousel slide" data-ride="carousel">
        <h2 class="featurette-heading">先睹为快</h2>
        <!-- 轮播图片 -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="carousel-caption row">
                    <div class="col-md-4">
                        <img src="http://image.www.funning.top/image/actors/IMG_0003.png?v=${version}">
                    </div>
                    <div class="col-md-4">
                        <img src="http://image.www.funning.top/image/actors/IMG_0004.png?v=${version}">
                    </div>
                    <div class="col-md-4">
                        <img src="http://image.www.funning.top/image/actors/IMG_0005.png?v=${version}">
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption row">
                    <div class="col-md-4">
                        <img src="http://image.www.funning.top/image/actors/IMG_0006.png?v=${version}">
                    </div>
                    <div class="col-md-4">
                        <img src="http://image.www.funning.top/image/actors/IMG_0007.png?v=${version}">
                    </div>
                    <div class="col-md-4">
                        <img src="http://image.www.funning.top/image/actors/IMG_0008.png?v=${version}">
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption row">
                    <div class="col-md-4">
                        <img src="http://image.www.funning.top/image/actors/IMG_0009.png?v=${version}">
                    </div>
                    <div class="col-md-4">
                        <img src="http://image.www.funning.top/image/actors/IMG_0010.png?v=${version}">
                    </div>
                    <div class="col-md-4">
                        <img src="http://image.www.funning.top/image/actors/IMG_0011.png?v=${version}">
                    </div>
                </div>
            </div>
        </div>

        <!-- 左右切换按钮 -->
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
            <img class="left" src="http://image.www.funning.top/image/arrow.png"/>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
            <img class="right" src="http://image.www.funning.top/image/arrow.png"/>
        </a>
    </div>
</div>

<div class="container">

    <hr id="service" class="featurette-divider">
    <div class="row service featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading">其他服务</h2>
            <br/>
            <p class="lead">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您希望我们帮您<strong>申请微信小程序</strong>，<strong>帮你申请微信支付</strong>，<strong>帮你安装部署</strong>，帮你处理其他杂事，请联系我们！
            </p>
            <p class="lead">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您希望<strong>添加自定义功能</strong>，<strong>添加更多运营工具</strong>，请联系我们！
            </p>
            <p class="lead">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您希望获得更好的<strong>UI设计</strong>，<strong>更多的技术支持</strong>，请联系我们！
            </p>
            <div style="text-align: center;margin-top: 30px;">
                <button onclick="javascrtpt:location.href='#contact'" type="button" class="btn btn-warning btn-lg">
                    联系我们
                </button>
            </div>
        </div>
        <div class="col-md-5">
            <!--https://unsplash.com/photos/IyaNci0CyRk-->
            <!--https://unsplash.com/photos/Pd585pphU-4-->

            <img class="featurette-image img-fluid mx-auto" style="border-radius:5px; margin: 10px 0px 10px 0px"
                 src="http://image.www.funning.top/image/service.jpg" alt="Generic placeholder image">
        </div>
    </div>

    <hr id="about" class="featurette-divider">

    <div class="row about featurette">

        <div class="col-md-5">
            <!--https://unsplash.com/photos/IyaNci0CyRk-->
            <!--https://unsplash.com/photos/Pd585pphU-4-->

            <img class="featurette-image img-fluid mx-auto" style="border-radius:5px; margin: 10px 0px 10px 0px"
                 src="http://image.www.funning.top/image/about.jpg" alt="Generic placeholder image">
        </div>
        <div class="col-md-7">
            <h2 class="featurette-heading">关于我们</h2>
            <br/>
            <p class="lead">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广州科宁新远科技有限公司成立于2018年11月，是一家从事互联网应用开发和运营的企业，致力于为线下企业提供信息化技术支持，帮助企业挖掘新的线上业务渠道，提高企业管理效率，为客户创造更多价值。
            </p>
            <p class="lead">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;同时自行研发运营互联网产品。注重用户体验，挖掘用户需求，提高普罗大众的生活质量与精神文明。
            </p>
            <p class="lead">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本着我为人人，人人为我的精神，我们开发并开源了<strong>饭宁小程序商城</strong>这个项目，希望有需要的人能得到满足。
            </p>
            <p class="lead">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们相信，科技创造美好生活。
            </p>
        </div>
    </div>
    <hr id="contact" class="featurette-divider">
    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading">联系方式</h2>
            <br/>
            <p class="lead">广州科宁新远科技有限公司</p>
            <p class="lead">
                联系人: <a href="tel:18826050039">尹先生</a>
            </p>
            <p class="lead">
                电话: <a href="tel:18826050039">186 2055 2310</a>
            </p>
            <p class="lead">
                邮箱: <a href="mailto:#">me@knxy-inc.com</a>
            </p>
            <p class="lead">地址: 广州市天河区黄埔大道中路336号御发商务中心6楼</p>
        </div>
        <div class="col-md-5">
            <img class="featurette-image img-fluid mx-auto" style="border-radius:5px; margin: 10px 0px 10px 0px"
                 src="http://image.www.funning.top/image/contact.jpg" alt="Generic placeholder image">
        </div>
    </div>

    <hr class="featurette-divider">
</div>


<!-- FOOTER -->
<footer class="container">
    <p class="float-right"><a href="#">Back to top</a></p>
    <p>
        <a href="http://www.miitbeian.gov.cn/">备案/许可证编号为：粤ICP备18156851号</a><br/>
        广州科宁新远科技有限公司
    </p>
</footer>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</body>

</html>
<script>
    $(function () {
        $(".nav-item").click(function () {
            $(".nav-item").removeClass("active");
            $(this).addClass("active");
        });
    });

    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?fb2e6de31fd863aebfb7550d3fda8f15";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>