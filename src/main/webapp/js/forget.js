$(function () {
    $("#btn_send_email").click(function () {
        LoadingDialog.show();
        
        Web.request("",{},{
            onSuccess:function (rp) {
                LoadingDialog.hide();
            },
            onError:function (rp) {
                LoadingDialog.hide();
                alert(rp.msg);
            }
        });
    })
})