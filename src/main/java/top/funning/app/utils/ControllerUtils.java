package top.funning.app.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.servlet.ModelAndView;
import top.funning.app.bean.PostBody;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.config.Code;
import top.knxy.library.config.V;
import top.knxy.library.utils.LogUtils;
import top.knxy.library.vehicle.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ControllerUtils {
    public final static String TAG = "ControllerUtils";

    private ControllerUtils() {
    }

    public static ModelAndView getAdminView(HttpServletRequest request) {
        String path = request.getRequestURI().substring(1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewJsp", path + ".jsp");
        modelAndView.setViewName("admin/common");
        return modelAndView;
    }

    public static ModelAndView getAdminView(String viewName) {
        String path = viewName;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewJsp", path + ".jsp");
        modelAndView.setViewName("admin/common");
        return modelAndView;
    }

    public static ModelAndView doService(HttpServletRequest request, BaseService service) {
        service.start();
        ModelAndView modelAndView = ControllerUtils.getAdminView(request);
        if (service.code == Code.Service.SUCCESS) {
            modelAndView.addObject(V.data, service.data);
        } else {
            modelAndView.addObject(V.errorMsg, service.msg);
            modelAndView.setViewName("/error/500");
        }
        return modelAndView;
    }


    public static <T extends FnService> Map doService(Gson gson, Class<T> tClass, JsonObject data, String shopId) {
        if (data == null) {
            data = new JsonObject();
        }
        T t = gson.fromJson(data, tClass);
        t.header = new FnService.Header();
        t.header.shopId = shopId;
        return doService(t, tClass);
    }

    public static <T extends FnService> Map doService(T t, Class<T> tClass) {
        t.start();
        if (t.code == Code.Service.SUCCESS) {
            return Response.createSuccess(t.data);
        } else if (t.code == Code.Service.ERROR) {
            return Response.create(Code.Client.ERROR, t.msg);
        } else if (t.code > 1000 || t.code < -1000) {
            return Response.create(String.valueOf(t.code), t.msg);
        } else {
            LogUtils.e("Service", "unknown service code = " + t.code + " , cmd = " + tClass.getSimpleName());
            return Response.createError();
        }
    }

    public static void toErrorPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/error/500");
    }

    public static <T extends FnService> T newService(HttpServletRequest request, Class<T> tClass) {
        try {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute(V.shopId);
            T t = tClass.newInstance();
            if (obj != null) {
                t.header = new FnService.Header();
                t.header.shopId = obj.toString();
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
