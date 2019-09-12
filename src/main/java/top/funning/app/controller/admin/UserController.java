package top.funning.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.funning.app.service.user.M1023;
import top.funning.app.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/admin/user/")
public class UserController {

    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest request) throws IOException {
        return ControllerUtils.doService(request, ControllerUtils.newService(request,M1023.class));
    }
}
