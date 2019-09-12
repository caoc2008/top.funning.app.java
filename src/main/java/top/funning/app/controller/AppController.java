package top.funning.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.funning.app.config.C;
import top.funning.app.service.pay.C1011;
import top.knxy.library.config.Code;
import top.knxy.library.utils.XmlUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AppController {


    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public String index() {
        return "index";
    }

    @RequestMapping("/pay/confirm")
    @ResponseBody
    public String payConfirm(@RequestBody String body) {
        C1011 service = new C1011();
        service.body = body;
        service.start();
        Map<Object, Object> map = new HashMap<>(2);
        if (service.code == Code.Service.SUCCESS) {
            map.put("return_code", "SUCCESS");
            map.put("return_msg", "OK");
        } else {
            map.put("return_code", "FAIL");
            map.put("return_msg", service.msg);
        }

        return XmlUtils.mapToXmlStr(map, true);
    }
}
