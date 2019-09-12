package top.funning.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.funning.app.database.Redis;
import top.funning.app.service.login.pwd.modify.M1019;
import top.funning.app.utils.ControllerUtils;
import top.knxy.library.config.V;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/")
public class IndexController {

    @GetMapping(value = {"/", "index"})
    public ModelAndView index(HttpServletRequest request) {
        return ControllerUtils.getAdminView("admin/index");
    }

}
