package top.funning.app.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.funning.app.database.table.Admin;
import top.funning.app.service.identify.password.manager.reset.M1027;
import top.funning.app.service.identify.password.manager.reset.email.check.M1028;
import top.funning.app.service.identify.password.manager.reset.email.send.M1026;
import top.funning.app.service.identify.login.manager.M1004;
import top.funning.app.service.identify.register.manager.M1025;
import top.knxy.library.config.Code;
import top.knxy.library.config.V;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
    public String login() {
        return "login";
    }


    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    public String doLogin(HttpServletRequest request, HttpServletResponse response,
                          @Param("username") String username,
                          @Param("password") String password) throws Exception {
        M1004 services = new M1004();
        services.username = username;
        services.password = password;
        services.start();
        if (services.code == Code.Service.SUCCESS) {
            Admin admin = (Admin) services.data;
            HttpSession session = request.getSession();
            session.setAttribute(V.adminId, admin.getId());
            session.setAttribute(V.username, admin.getUsername());
            session.setAttribute(V.shopId, admin.getShopId());
            return "redirect:/admin/index";
        } else {
            request.setAttribute(V.username, username);
            request.setAttribute(V.password, password);
            request.setAttribute(V.errorMsg, services.msg);
            return "login";
        }
    }


    @RequestMapping(value = {"/register"}, method = {RequestMethod.GET})
    public String register() {
        return "register";
    }


    @RequestMapping(value = {"/register"}, method = {RequestMethod.POST})
    public String doRegister(HttpServletRequest request, HttpServletResponse response,
                             @Param("username") String username,
                             @Param("password") String password) {
        M1025 services = new M1025();
        services.username = username;
        services.password = password;
        services.start();
        if (services.code == Code.Service.SUCCESS) {
            Admin admin = (Admin) services.data;
            HttpSession session = request.getSession();
            session.setAttribute(V.adminId, admin.getId());
            session.setAttribute(V.username, admin.getUsername());
            session.setAttribute(V.shopId, admin.getShopId());
            return "redirect:/admin/index";
        } else {
            request.setAttribute(V.username, username);
            request.setAttribute(V.password, password);
            request.setAttribute(V.errorMsg, services.msg);
            return "register";
        }
    }

    @RequestMapping(value = {"/reset_pwd"}, method = {RequestMethod.GET})
    public String sendEmail() {
        return "reset_pwd/send_email";
    }


    @RequestMapping(value = {"/reset_pwd"}, method = {RequestMethod.POST})
    public String sendEmail(HttpServletRequest request, HttpServletResponse response,
                            @Param("username") String username) throws Exception {
        M1026 services = new M1026();
        services.email = username;
        services.start();
        if (services.code == Code.Service.SUCCESS) {
            return "reset_pwd/send_email_success";
        } else {
            request.setAttribute(V.username, username);
            request.setAttribute("errorMsg", services.msg);
            return "reset_pwd/send_email";
        }
    }

    @RequestMapping(value = {"/reset_pwd/{code}"}, method = {RequestMethod.GET})
    public String resetPwd(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("code") String code) {
        M1028 service = new M1028();
        service.redisCode = code;
        service.start();
        if (service.code == Code.Service.SUCCESS) {
            return "reset_pwd/action";
        } else {
            request.setAttribute(V.errorMsg, service.msg);
            return "error/500";
        }
    }

    @RequestMapping(value = {"/reset_pwd/{code}"}, method = {RequestMethod.POST})
    public String resetPwd(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("code") String code,
                           @Param("password") String password) throws Exception {
        M1027 services = new M1027();
        services.redisCode = code;
        services.password = password;
        services.start();
        if (services.code == Code.Service.SUCCESS) {
            return "reset_pwd/reset_success";
        } else {
            request.setAttribute("errorMsg", services.msg);
            return "reset_pwd/action";
        }
    }
}
