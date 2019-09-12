let Page = {
    save: {
        click: function () {
            let imageFile = document.getElementById("image_file").files[0];
            if (!imageFile) {
                alert("你还没有选择图片");
                return;
            }
            Utils.getImageInfo(imageFile, function (width, height) {
                if (width != 640 || height > 1280) {
                    alert("图片的宽度必须是640，图片的高度必须小于1280");
                } else {
                    Page.save._upload(imageFile);
                }
            });
        },
        _upload: function (imageFile) {
            LoadingDialog.show();
            let upload = new Upload(imageFile);
            upload.listener = {
                next: function (fname, percent) {
                    let message = "正在上传 \"" + fname + "\"<br/>已经上传了: " + percent.toFixed(2) + "%";
                    LoadingDialog.msg(message);
                },
                error: function (code, msg) {
                    LoadingDialog.hide();
                    alert(msg);
                },
                complete: function (res) {
                    Page.save._commit(res.fileName);
                }
            };
            upload.start();
        },
        _commit: function (fileName) {
            Web.request("M1021", {fileName: fileName}, {
                onSuccess: function (response) {
                    window.location.reload();
                },
                onError: function (code, msg) {
                    $("#msg").html(msg);
                    LoadingDialog.hide();
                }
            })

        }
    },
    delete: {
        click: function () {
            LoadingDialog.show();
            Web.request("M1022", {}, {
                onSuccess: function (response) {
                    window.location.reload();
                },
                onError: function (code, msg) {
                    $("#msg").html(msg);
                    LoadingDialog.hide();
                }
            })
        }
    }

}