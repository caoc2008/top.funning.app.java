package top.funning.app.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.funning.app.service.FnService;
import top.funning.app.service.address.poster.computer.C1013;
import top.funning.app.service.index.C1001;
import top.funning.app.service.index.count.M1017;
import top.funning.app.service.index.poster.put.M1021;
import top.funning.app.service.index.poster.remove.M1022;
import top.funning.app.service.login.pwd.reset.M1027;
import top.funning.app.service.login.register.smscode.C1020;
import top.funning.app.service.login.wechat.C1003;
import top.funning.app.service.good.add.M1014;
import top.funning.app.service.good.delete.M1012;
import top.funning.app.service.good.get.C1009;
import top.funning.app.service.good.modify.M1011;
import top.funning.app.service.good.search.C1012;
import top.funning.app.service.good.type.add.M1006;
import top.funning.app.service.good.type.delete.M1009;
import top.funning.app.service.good.type.modify.M1008;
import top.funning.app.service.order.cancel.C1007;
import top.funning.app.service.order.confirm.C1004;
import top.funning.app.service.order.create.C1002;
import top.funning.app.service.order.finish.M1005;
import top.funning.app.service.order.get.C1006;
import top.funning.app.service.order.list.C1005;
import top.funning.app.service.order.pay.C1010;
import top.funning.app.service.order.refund.admin.M1018;
import top.funning.app.service.order.refund.client.C1008;
import top.funning.app.service.pay.C1011;
import top.funning.app.service.qiniu.uploadtoken.get.M1015;
import top.funning.app.service.SessionInfo;
import top.funning.app.service.shop.modify.M1029;
import top.funning.app.service.shop.modify.logo.M1032;
import top.funning.app.service.shop.modify.p12.M1031;
import top.funning.app.utils.ControllerUtils;
import top.knxy.library.config.Code;
import top.knxy.library.config.V;
import top.knxy.library.utils.LogUtils;
import top.knxy.library.utils.TextUtils;
import top.knxy.library.vehicle.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class ApiController {
    public static final String TAG = "ApiController";

    public static Class[] AndroidServiceList = {
            C1020.class
    };

    @PostMapping("/android_api")
    public Object android(HttpServletRequest request, @RequestBody String bodyStr) throws Exception {
        LogUtils.i(TAG, bodyStr);

        Gson gson = new Gson();
        Body body = gson.fromJson(bodyStr, Body.class);

        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(V.userInfo);

        if ("C1001".equals(body.cmd) || "C1002".equals(body.cmd)
                || "C1004".equals(body.cmd) || "C1005".equals(body.cmd)
                || "C1006".equals(body.cmd) || "C1009".equals(body.cmd)
                || "C1010".equals(body.cmd) || "C1011".equals(body.cmd)) {
            if (sessionInfo == null) {
                return Response.create(Code.Client.NEED_LOGIN, "还没有登录");
            }
        }

        if (sessionInfo != null && !TextUtils.isEmpty(sessionInfo.userId)) {
            body.data.addProperty(V.userId, sessionInfo.userId);
            body.data.addProperty(V.sessionKey, sessionInfo.sessionKey);
            body.data.addProperty(V.openId, sessionInfo.openId);
        }

        if (C1003.class.getSimpleName().equals(body.cmd)) {
            C1003 c1003 = gson.fromJson(body.data, C1003.class);
            c1003.start();
            if (c1003.code == Code.Service.SUCCESS) {
                HttpSession session = request.getSession();
                C1003.Data d = (C1003.Data) c1003.data;
                session.setAttribute(V.userInfo, new SessionInfo(d.openid, d.sessionKey, d.userId));
                return Response.createSuccess();
            } else {
                return Response.create(Code.Client.ERROR, c1003.msg);
            }
        }

        for (Class cls : AndroidServiceList) {
            String claName = cls.getSimpleName();
            String cmd = body.cmd;
            if (claName.equals(cmd)) {
                return ControllerUtils.doService(gson, cls, body.data);
            }
        }

        return Response.createError();
    }


    public static Class[] clientServiceList = {
            C1001.class, C1002.class, C1003.class, C1004.class, C1005.class,
            C1006.class, C1007.class, C1008.class, C1009.class, C1010.class,
            C1011.class, C1012.class, C1013.class};

    @PostMapping("/client_api")
    public Object client(HttpServletRequest request, @RequestBody String bodyStr) throws Exception {
        LogUtils.i(TAG, bodyStr);

        Gson gson = new Gson();
        Body body = gson.fromJson(bodyStr, Body.class);

        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(V.userInfo);

        if ("C1001".equals(body.cmd) || "C1002".equals(body.cmd)
                || "C1004".equals(body.cmd) || "C1005".equals(body.cmd)
                || "C1006".equals(body.cmd) || "C1009".equals(body.cmd)
                || "C1010".equals(body.cmd) || "C1011".equals(body.cmd)) {
            if (sessionInfo == null) {
                return Response.create(Code.Client.NEED_LOGIN, "还没有登录");
            }
        }

        if (sessionInfo != null && !TextUtils.isEmpty(sessionInfo.userId)) {
            body.data.addProperty(V.userId, sessionInfo.userId);
            body.data.addProperty(V.sessionKey, sessionInfo.sessionKey);
            body.data.addProperty(V.openId, sessionInfo.openId);
        }

        if (C1003.class.getSimpleName().equals(body.cmd)) {
            C1003 c1003 = gson.fromJson(body.data, C1003.class);
            c1003.start();
            if (c1003.code == Code.Service.SUCCESS) {
                HttpSession session = request.getSession();
                C1003.Data d = (C1003.Data) c1003.data;
                session.setAttribute(V.userInfo, new SessionInfo(d.openid, d.sessionKey, d.userId));
                return Response.createSuccess();
            } else {
                return Response.create(Code.Client.ERROR, c1003.msg);
            }
        }

        for (Class cls : clientServiceList) {
            String claName = cls.getSimpleName();
            String cmd = body.cmd;
            if (claName.equals(cmd)) {
                return ControllerUtils.doService(gson, cls, body.data);
            }
        }

        return Response.createError();
    }


    public static Class[] adminServiceList = {
            M1005.class, M1006.class, M1008.class, M1009.class, M1011.class, M1012.class,
            M1014.class, M1015.class, M1017.class, M1018.class, M1021.class, M1022.class,
            M1029.class, M1031.class, M1032.class
    };

    @PostMapping("/admin_api")
    public Object admin(HttpServletRequest request, @RequestBody String bodyStr) {

        Object objAdminId = request.getSession().getAttribute(V.adminId);
        Object objShopId = request.getSession().getAttribute(V.shopId);
        if (objAdminId == null || objShopId == null) {
            return Response.create(Code.Client.NEED_LOGIN);
        }

        String shopId = String.valueOf(objShopId);
        String adminId = String.valueOf(objAdminId);

        LogUtils.i(TAG, bodyStr);

        Gson gson = new Gson();
        Body body = gson.fromJson(bodyStr, Body.class);

        body.data.addProperty(V.shopId, shopId);

        if ("M1020".equals(body.cmd)) {
            //exit
            request.getSession().removeAttribute(V.adminId);
            return Response.createSuccess();
        }

        for (Class cls : adminServiceList) {
            String claName = cls.getSimpleName();
            String cmd = body.cmd;
            if (claName.equals(cmd)) {
                return ControllerUtils.doService(gson, cls, body.data);
            }
        }

        return Response.createError();
    }

    public static class Body {
        public String cmd;
        public JsonObject data;
    }
}
