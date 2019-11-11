package top.funning.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import top.funning.app.database.Redis;
import top.funning.app.service.identify.password.manager.modify.M1019;
import top.funning.app.service.shop.get.M1030;
import top.funning.app.service.shop.modify.p12.M1031;
import top.funning.app.utils.ControllerUtils;
import top.knxy.library.config.Code;
import top.knxy.library.config.V;
import top.knxy.library.vehicle.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/manage/")
public class ManageController {


    @GetMapping("/poster")
    public ModelAndView poster(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String shopId = session.getAttribute(V.shopId).toString();
        ModelAndView mv = ControllerUtils.getAdminView(request);
        mv.addObject(V.postImageUrl, Redis.get(shopId + "_" + V.postImageUrl));
        return mv;
    }

    @GetMapping("/pwd/modify")
    public ModelAndView passwordModify() {
        return ControllerUtils.getAdminView("/admin/manage/pwd/modify");
    }

    @PostMapping("/pwd/modify")
    public ModelAndView doPasswordModify(HttpServletRequest request, M1019 service) {
        service.adminId = request.getSession().getAttribute(V.adminId).toString();
        service.start();
        ModelAndView mv = ControllerUtils.getAdminView("/admin/manage/pwd/modify");
        mv.addObject(V.code, service.code);
        mv.addObject(V.errorMsg, service.msg);
        return mv;
    }

    @GetMapping("/wechatmini")
    public ModelAndView passwordModify(HttpServletRequest request, HttpServletResponse response) {
        M1030 service = ControllerUtils.newService(request, M1030.class);
        ModelAndView view;
        service.start();
        if (service.code == Code.Service.SUCCESS) {
            view = ControllerUtils.getAdminView(request);
            view.addObject(V.data, service.data);
        } else {
            view = ControllerUtils.getAdminView("/error/500");
            view.addObject(V.errorMsg, service.msg);
        }
        return view;
    }

    @PostMapping("/wechatmini/p12upload")
    @ResponseBody
    public Object p12Upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        M1031 service = ControllerUtils.newService(request, M1031.class);
        service.file = file;
        service.start();
        Response response = new Response();
        if (service.code == Code.Service.SUCCESS) {
            return Response.createSuccess();
        } else {
            return Response.createError(service.msg);
        }
    }
}
