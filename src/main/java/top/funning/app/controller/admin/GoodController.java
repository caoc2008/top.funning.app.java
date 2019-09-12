package top.funning.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.funning.app.bean.Page;
import top.funning.app.service.good.get.M1013;
import top.funning.app.service.good.list.M1010;
import top.funning.app.service.good.type.list.M1007;
import top.funning.app.service.good.type.get.M1016;
import top.funning.app.utils.ControllerUtils;
import top.knxy.library.config.Code;
import top.knxy.library.config.V;
import top.knxy.library.utils.TextUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/good/")
public class GoodController {

    @GetMapping("/add")
    public ModelAndView add(HttpServletRequest request) {
        return ControllerUtils.doService(request, ControllerUtils.newService(request, M1016.class));
    }

    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest request, String page) {
        M1010 service = ControllerUtils.newService(request, M1010.class);
        service.page = page;
        service.start();
        ModelAndView modelAndView = ControllerUtils.getAdminView(request);
        if (service.code == Code.Service.SUCCESS) {
            modelAndView.addObject(V.data, service.data);
            modelAndView.addObject(V.page, new Page(page));
        } else {
            modelAndView.addObject(V.errorMsg, service.msg);
            modelAndView.setViewName("/error/500");
        }
        return modelAndView;
    }


    @GetMapping("/type/list")
    public ModelAndView typeList(HttpServletRequest request, String page) {
        M1007 service = ControllerUtils.newService(request, M1007.class);
        service.start();
        ModelAndView mv = ControllerUtils.getAdminView(request);
        if (service.code == Code.Service.SUCCESS) {
            mv.addObject(V.data, service.data);
        } else {
            mv.addObject(V.errorMsg, service.msg);
            mv.setViewName("/error/500");
        }
        return mv;
    }


    @GetMapping("/search")
    public ModelAndView search(HttpServletRequest request, String id) {
        if (TextUtils.isEmpty(id)) {
            return ControllerUtils.getAdminView(request);
        } else {
            M1013 service = ControllerUtils.newService(request,M1013.class);
            service.id = id;
            return ControllerUtils.doService(request, service);
        }
    }
}
