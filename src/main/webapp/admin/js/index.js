$(function () {
    Reminder.start();
});


let Common = {
    exit: function () {
        if (!window.confirm("确认退出吗?")) return;

        LoadingDialog.show();
        Web.request("M1020", {}, {
            onSuccess: function (rp) {
                window.location.href = "/login";
            },
            onError: function (rp) {
                alert(rp.msg);
                LoadingDialog.hide();
            }
        })
    }
}


let Qiniu = {
    uploadImage: function (imageFile, name, upToken, callback) {
        let putExtra = {
            fname: imageFile["name"],
            params: {}
        };
        let config = {
            useCdnDomain: true
        };
        let observable = qiniu.upload(imageFile, name, upToken, putExtra, config)
        let subscription = observable.subscribe(callback);
    }
};


let LoadingDialog = {
    show: function () {
        $("#LoadingDialog").modal('show');
    },
    hide: function () {
        $("#LoadingDialog").modal('hide');
        $("#LoadingDialog [class='lv_msg']").hide();
    },
    msg: function (msg) {
        $("#LoadingDialog [class='lv_msg']").show();
        $("#LoadingDialog [class='lv_msg']").html(msg);
    }
};


/*let Reminder = {
    isFirst: true,
    init: function () {
        let host;
        if (window.location.protocol == 'http:') {
            host = 'ws://' + window.location.host + '/admin/order/reminder';
        } else {
            host = 'wss://' + window.location.host + '/admin/order/reminder';
        }

        let socket;
        if ('WebSocket' in window) {
            socket = new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            socket = new MozWebSocket(host);
        } else {
            Console.log('Error: WebSocket is not supported by this browser.');
            return;
        }
        socket.onopen = function () {
            console.log("WebSocket", "open");
        };
        socket.onclose = function () {
            console.log("WebSocket", "close");
        };
        socket.onmessage = function (message) {
            console.log("WebSocket", "message : " + message);
            let data = JSON.parse(message.data);
            Manager.remind(data)
        };
    },
}*/

let Reminder = {
    isFirst: true,
    start: function () {
        Reminder.run();
    },
    run: function () {
        console.log("Reminder.run");
        Web.request("M1017", {}, {
            onSuccess: function (data) {
                Manager.remind(data.data);
                setTimeout(function () {
                    Reminder.run();
                }, 10000);
            },
            onError: function (rp) {
                //alert(rp.msg);
                LoadingDialog.hide();
            }
        });
    }
}

let Manager = {
    remind: function (data) {

        let groupUnDoCount = data.groupUnDoCount;
        let normalUnDoCount = data.normalUnDoCount;
        normalUnDoCount = Number(normalUnDoCount);
        groupUnDoCount = Number(groupUnDoCount);
        if (!Reminder.isFirst) {
            if (normalUnDoCount > 1 || groupUnDoCount > 1) {
                let m = document.getElementById('sound_remind');
                m.play();//播放
            }
        } else {
            Reminder.isFirst = false;
        }


        $("#normal_red_point").html(data.normalUnDoCount);
        if (normalUnDoCount < 1) {
            $("#normal_red_point").hide();
        } else {
            $("#normal_red_point").show();
        }

        $("#group_red_point").html(data.groupUnDoCount);
        if (groupUnDoCount < 1) {
            $("#group_red_point").hide();
        } else {
            $("#group_red_point").show();
        }
    }
}

class Upload {
    //_file;
    //_suffix;
    //_name;
    //_upToken;

    //listener;

    constructor(file) {
        this._file = file;
    }

    start() {
        this._getUploadToken();
    }

    _getUploadToken() {
        let that = this;
        this._suffix = Utils.getSuffix(that._file["name"]);
        Web.request("M1015", {
            "suffix": that._suffix
        }, {
            onSuccess: function (rp) {
                let data = rp.data;
                that._name = data.name;
                that._upToken = data.token;
                that._upload();
            },
            onError: function (rp) {
                that.listener.error("response.code", rp.msg);
            }
        });
    }

    _upload() {
        let that = this;
        Qiniu.uploadImage(that._file, that._name, that._upToken, {
            next: function (res) {
                console.log("       upload:", res);
                that.listener.next(that._file["name"], res.total.percent);
            },
            error: function (err) {
                that.listener.error("-1", err);
            },
            complete: function (res) {
                res.fileName = that._name;
                that.listener.complete(res);
            }
        });
    }
}
