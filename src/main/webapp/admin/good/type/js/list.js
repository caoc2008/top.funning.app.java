$(function () {
    $("[state='1']").html("显示");
    $("[state='2']").html("隐藏");
});

let Dropdown = {
    itemClick: function (state, id) {

        let ddl = $("#tr_" + id + " [role='input_state'] button:eq(0)");
        $(ddl).attr({"state": state});

        if (state == "1") {
            $(ddl).html("显示");
        } else if (state == "2") {
            $(ddl).html("隐藏");
        }
    }
}


let Mode = {
    edit: function (id) {
        $("#tr_" + id + " td span").hide();

        $("#tr_" + id + " [role='input_state']").show();
        $("#tr_" + id + " [role='input_name']").show();

        $("#tr_" + id + " [role='btn_edit']").hide();
        $("#tr_" + id + " [role='btn_delete']").hide();
        $("#tr_" + id + " [role='btn_cancel']").show();
        $("#tr_" + id + " [role='btn_commit']").show();
    },
    cancel: function (id) {
        $("#tr_" + id + " td span").show();

        $("#tr_" + id + " [role='input_state']").hide();
        $("#tr_" + id + " [role='input_name']").hide();

        $("#tr_" + id + " [role='btn_edit']").show();
        $("#tr_" + id + " [role='btn_delete']").show();
        $("#tr_" + id + " [role='btn_cancel']").hide();
        $("#tr_" + id + " [role='btn_commit']").hide();
    }
}

let Type = {
    add: function () {
        let name = $("[class='type_add'] [name='name']").val();
        if (!name) {
            alert("请输入类型名称");
            return;
        }

        LoadingDialog.show();
        Web.request("M1006", {"name": name}, {
            onSuccess: function (response) {
                window.location.reload();
            },
            onError(response) {
                alert(response.msg);
                LoadingDialog.hide();
            }
        })
    },
    delete: function (id) {
        if (window.confirm("确定删除吗?")) {
            LoadingDialog.show();
            Web.request("M1009", {"id": id}, {
                onSuccess: function (response) {
                    LoadingDialog.hide();
                    $("#tr_" + id).hide();
                },
                onError(response) {
                    LoadingDialog.hide();
                    alert(response.msg);
                }
            })
        }
    },
    modify: function (id) {
        let name = $("#tr_" + id + " [role='input_name']").val();
        let state = $("#tr_" + id + " [role='input_state'] button:eq(0)").attr("state");

        LoadingDialog.show();
        Web.request("M1008", {
            "id": id,
            "name": name,
            "state": state
        }, {
            onSuccess: function (response) {
                Mode.cancel(id);

                $("#tr_" + id + " [role='sp_name']").html(name);

                if (state == "1") {
                    $("#tr_" + id + " [role='sp_state']").html("显示");
                } else if (state == "2") {
                    $("#tr_" + id + " [role='sp_state']").html("隐藏");
                }
                LoadingDialog.hide();
            },
            onError(response) {
                alert(response.msg);
                LoadingDialog.hide();
            }
        });
    }
}

