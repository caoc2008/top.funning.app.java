package top.funning.app.controller;

import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.funning.app.config.C;
import top.funning.app.service.pay.confirm.alipay.C1035;
import top.funning.app.service.pay.confirm.wechat.C1011;
import top.knxy.library.config.Code;
import top.knxy.library.utils.XmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class AppController {


    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public String index() {
        return "index";
    }

    @RequestMapping("/pay/wechat/confirm")
    @ResponseBody
    public String payWechatConfirm(@RequestBody String body) {
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

    @RequestMapping("/pay/alipay/confirm")
    @ResponseBody
    public void payAlipayConfirm(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        String charset = "utf-8";
        boolean flag = AlipaySignature.rsaCheckV1(params, C.AliPay.publicKey, charset, "RSA2");
        PrintWriter writer = response.getWriter();
        if (!flag) {
            writer.write("failure");
        }

        C1035 service = new C1035();

        service.outTradeNo = params.get("out_trade_no");
        service.totalAmount = params.get("total_amount");
        service.buyerPayAmount = params.get("buyer_pay_amount");
        service.tradeNo = params.get("trade_no");

        service.start();
        if (service.code == Code.Service.SUCCESS) {
            writer.write("success");
        } else {
            writer.write("failure");
        }
    }
}
