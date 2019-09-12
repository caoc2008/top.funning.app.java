<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-08
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/admin/manage/css/wechatmini.css?version=${version}" rel="stylesheet"/>
<script src="/js/qiniu.min.js"></script>
<script src="/admin/manage/js/wechatmini.js?version=${version}"></script>


<script type="text/javascript">


    let logoUrl = "${data.logoUrl}";
    let p12FileName = "${data.p12FileName}";
</script>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <div>
        <p class="lead">
            小程序源码请到 <a href="https://github.com/MrNeptune/top.funning.app.mini">github</a> 中下载，
            如需一条龙服务，请给我们邮件 ywj-1991@qq.com
        </p>

        <hr />
        <div class="row">
            <div class="form-label-group col-md-6">
                <label for="wechat_appid">小程序ID (appid)</label>
                <input name="wechat_appid" type="text" id="wechat_appid" class="form-control"
                       value="${data.wechatAppid}"
                       placeholder="小程序ID" required autofocus>
            </div>

            <div class="form-label-group col-md-6">
                <label for="wechat_secret">小程序密钥 (secret)</label>
                <input name="wechat_secret" type="password" id="wechat_secret" class="form-control"
                       value="${data.wechatSecret}"
                       placeholder="请输入新密码" required>
            </div>
        </div>
        <div class="row">
            <div class="form-label-group col-md-6">
                <label for="wcpay_mchId">微信支付 商户ID (appid)</label>
                <input name="wcpay_mchId" type="text" id="wcpay_mchId" class="form-control" value="${data.wcpayMchId}"
                       placeholder="小程序ID" required autofocus>
            </div>

            <div class="form-label-group col-md-6">
                <label for="wcpay_apiKey">微信支付 API密钥 (apiKey)</label>
                <input name="wcpay_apiKey" type="password" id="wcpay_apiKey" class="form-control"
                       value="${data.wcpayApiKey}"
                       placeholder="请输入新密码" required>
            </div>
        </div>
        <div class="row">
            <div class="form-label-group col-md-6">
                <label for="app_name">商铺名称 (小程序名称)</label>
                <input name="app_name" type="text" id="app_name" class="form-control" value="${data.appName}"
                       placeholder="请输入新密码" required>
            </div>

            <div class="form-label-group col-md-6">
                <label for="phone_num">客服电话</label>
                <input name="phone_num" type="number" id="phone_num" class="form-control" value="${data.phoneNum}"
                       placeholder="请输入新密码" required>
            </div>
        </div>

        <hr/>

        <div class="row">
            <div class="col-md-6">
                <div class="form-label-title">
                    <label>logo 上传</label>
                </div>

                <div class="div-logo">
                    <img id="img_logo" onclick="ImageUpload.action()" src="/image/add.png">
                    <div id="btn_change_logo" class="btn-change">
                        <div class="text">点击修改</div>
                    </div>
                    <input type="file" id="imageUrl_input" class="hide">
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-label-title">
                    <label>微信支付密钥文件上传</label>
                </div>

                <div class="div-logo">
                    <img id="img_p12" onclick="P12Upload.action()" src="/image/add.png">
                    <div id="btn_change_p12" class="btn-change">
                        <div class="text">点击修改</div>
                    </div>
                    <input type="file" id="p12_input" class="hide">
                </div>
            </div>
        </div>


        <div id="error-msg" style="color:#e51c23">${errorMsg}</div>

        <button id="btn_commit" onclick="Page.commit()" class="btn btn-lg btn-primary btn-block" type="button">
            提交保存
        </button>
    </div>
</main>