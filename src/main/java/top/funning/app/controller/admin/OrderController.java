package top.funning.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.funning.app.bean.Page;
import top.funning.app.service.order.list.M1001;
import top.funning.app.service.order.search.M1002;
import top.funning.app.service.order.undo.M1003;
import top.funning.app.utils.ControllerUtils;
import top.knxy.library.config.Code;
import top.knxy.library.config.V;
import top.knxy.library.utils.TextUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/order/")
public class OrderController {

    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest request, String page) throws Exception {
        M1001 service = ControllerUtils.newService(request,M1001.class);

        service.page = page;
        ModelAndView modelAndView = ControllerUtils.getAdminView(request);

        service.start();
        if (service.code == Code.Service.SUCCESS) {
            modelAndView.addObject(V.data, service.data);
            modelAndView.addObject(V.page, new Page(page));
        } else {
            modelAndView.setViewName("/error/500");
        }
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(HttpServletRequest request, String id) {

        ModelAndView modelAndView = ControllerUtils.getAdminView(request);
        if (!TextUtils.isEmpty(id)) {
            M1002 service = ControllerUtils.newService(request,M1002.class);
            service.id = id;
            service.start();
            if (service.code == Code.Service.SUCCESS) {
                request.setAttribute(V.data, service.data);
                modelAndView.addObject(V.data, service.data);
            } else {
                modelAndView.addObject(V.errorMsg, service.msg);
                modelAndView.setViewName("/error/500");
            }
        }
        return modelAndView;
    }

    @GetMapping("/list/undo")
    public ModelAndView listUnDo(HttpServletRequest request, String page) {
        M1003 service = ControllerUtils.newService(request, M1003.class);
        ModelAndView modelAndView = ControllerUtils.getAdminView(request);
        service.page = page;
        service.start();
        if (service.code == Code.Service.SUCCESS) {
            modelAndView.addObject(V.page, new Page(page));
            modelAndView.addObject(V.data, service.data);
        } else {
            modelAndView.setViewName("/error/500");
        }
        return modelAndView;
    }

}
