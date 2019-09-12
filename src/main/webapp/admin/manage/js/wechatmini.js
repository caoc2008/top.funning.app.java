$(function () {
    console.log(logoUrl);
    if (logoUrl) {
        Page.hasImageFile = true;
        $("#img_logo").attr({"src": logoUrl});
        $("#btn_change_logo").css({"display": "flex"});
    }

    console.log(p12FileName);
    if (p12FileName) {
        Page.hasP12File = true;
        $("#img_p12").attr({"src": "/admin/manage/image/p12.png"});
        $("#btn_change_p12").css({"display": "flex"});
    }
})

let Page = {
    commit: function() {
        Page.commit.data = {
            "wechatAppid": $("#wechat_appid").val(),
            "wechatSecret": $("#wechat_secret").val(),
            "wcpayMchId": $("#wcpay_mchId").val(),
            "wcpayApiKey": $("#wcpay_apiKey").val(),
            "appName": $("#app_name").val(),
            "phoneNum": $("#phone_num").val()
        };

        console.log(Page.commit.data);
        if (!Page.commit.data.appName) {
            alert("您没有填入App名称");
            return;
        }

        if (Page.commit.data.appName.length > 32) {
            alert("App名称不能多于32个字符");
            return;
        }

        if (!Page.commit.data.phoneNum) {
            alert("您没有填入客服电话");
            return;
        }

        if (Page.commit.data.phoneNum.length > 32) {
            alert("客服电话不能多于32个字符");
            return;
        }

        if (!Page.commit.data.wcpayMchId) {
            alert("您没有填入微信支付 商户ID");
            return;
        }

        if (Page.commit.data.wcpayMchId.length > 32) {
            alert("商户ID不能多于32个字符");
            return;
        }

        if (!Page.commit.data.wcpayApiKey) {
            alert("您没有填入API密钥");
            return;
        }

        if (Page.commit.data.wcpayApiKey.length > 32) {
            alert("API密钥不能多于32个字符");
            return;
        }


        if (!Page.commit.data.wechatSecret) {
            alert("您没有填入小程序密钥");
            return;
        }

        if (Page.commit.data.wechatSecret.length > 32) {
            alert("小程序密钥能多于32个字符");
            return;
        }


        if (!Page.commit.data.wechatAppid) {
            alert("您没有填入小程序ID");
            return;
        }

        if (Page.commit.data.wechatAppid.length > 18) {
            alert("小程序ID不能多于18个字符");
            return;
        }

        LoadingDialog.show();

        console.log("commit", Page.commit.data);
        let data = Page.commit.data;

        Web.request("M1029", data, {
            onSuccess: function (p) {
                LoadingDialog.hide();
                // window.location.reload();
            },
            onError: function (p) {
                LoadingDialog.hide();
                alert(p.msg);
            }
        })
    }
}

let P12Upload = {
    action: function () {
        let ele = document.getElementById("p12_input");
        ele.value = null;
        ele.onchange = function () {
            P12Upload.p12File = document.getElementById("p12_input").files[0];
            P12Upload._upload();
        };
        $("#p12_input").trigger("click");
    },
    _upload: function () {
        LoadingDialog.show();
        LoadingDialog.msg("正在上传证书");
        let formData = new FormData();
        formData.append("file", P12Upload.p12File);    //生成一对表单属性
        $.ajax({
            type: "POST",           //因为是传输文件，所以必须是post
            url: '/admin/manage/wechatmini/p12upload',         //对应的后台处理类的地址
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.code == 1) {
                    LoadingDialog.hide();

                    Page.hasP12File = true;
                    $("#img_p12").attr({"src": "/admin/manage/image/p12.png"});
                    $("#btn_change_p12").css({"display": "flex"});
                } else {
                    LoadingDialog.hide();
                    alert(data.msg);
                }
            },
            error: function (rq, msg, e) {
                LoadingDialog.hide();
                alert(msg);
            }
        });
    }
};

let ImageUpload = {
    action: function () {
        let ele = document.getElementById("imageUrl_input");
        ele.value = null;
        ele.onchange = function () {
            let imageFile = document.getElementById("imageUrl_input").files[0];
            Utils.getImageInfo(imageFile, function (width, height) {
                if (width == height) {
                    ImageUpload.imageFile = imageFile;
                    ImageUpload._upload();
                } else {
                    alert("请选择一张正方形的图片");
                }
            });
        };
        $("#imageUrl_input").trigger("click");
    },
    _upload: function () {
        console.log("ImageUpload", "upload");
        let imageFile = ImageUpload.imageFile;
        if (!imageFile) {
            return;
        }
        LoadingDialog.show();
        let upload = new Upload(imageFile);
        upload.listener = {
            next: function (fname, percent) {
                let message = "正在上传图片 \"" + fname + "\"<br/>已经上传了: " + percent.toFixed(2) + "%";
                LoadingDialog.msg(message);
            },
            error: function (code, msg) {
                LoadingDialog.hide();
                alert(msg);
            },
            complete: function (res) {

                let path = window.URL.createObjectURL(imageFile);
                $("#img_logo").attr({"src": path});
                $("#btn_change_logo").css({"display": "flex"});

                ImageUpload.fileName = res.fileName;//imageHost
                ImageUpload._commit();
            }
        };
        upload.start();
    },
    _commit: function () {
        let data = {"fileName": ImageUpload.fileName};

        Web.request("M1032", data, {
            onSuccess: function (p) {
                LoadingDialog.hide();
            },
            onError: function (p) {
                LoadingDialog.hide();
                alert(p.msg);
            }
        });
    }
}
