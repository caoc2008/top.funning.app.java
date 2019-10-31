package top.funning.app.service.login.register.smscode;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.NotNull;
import top.funning.app.bean.SmsInfo;
import top.funning.app.config.C;
import top.funning.app.database.Redis;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.utils.LogUtils;
import top.knxy.library.utils.ServiceUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;


public class C1020 extends FnService {

    private static final String TAG = "C1020";

    @Digits
    public String phone;

    @Override
    protected void run() throws Exception {

        Gson gson = new Gson();
        String codeId = ServiceUtils.getUUid();

        String code = getCode();
        SmsInfo smsInfo = new SmsInfo();
        smsInfo.setPhone(phone);
        smsInfo.setCode(code);
        smsInfo.setData(new Date().getTime());
        smsInfo.setErrorTime(0);
        String str = gson.toJson(smsInfo);
        Redis.set(codeId, str, 900);

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", C.Aliyun.id, C.Aliyun.secrety);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);

        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "饭宁");
        request.putQueryParameter("TemplateCode", "SMS_175571331");

        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        String param = gson.toJson(map);
        request.putQueryParameter("TemplateParam", param);

        CommonResponse response = client.getCommonResponse(request);
        String data = response.getData();
        SendMsgResponse sendMsgResponse = gson.fromJson(data, SendMsgResponse.class);
        if (!"OK".equals(sendMsgResponse.Code)) {
            LogUtils.i(TAG, data);
            createError(this, sendMsgResponse.Message);
        } else {
            Data d = new Data();
            this.data = d;
            d.codeId = codeId;
            createSuccess(this);
        }
    }

    public static class SendMsgResponse {

        /**
         * Message : OK
         * RequestId : 873043ac-bcda-44db-9052-2e204c6ed20f
         * BizId : 607300000000000000^0
         * Code : OK
         */

        private String Message;
        private String RequestId;
        private String BizId;
        private String Code;

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String RequestId) {
            this.RequestId = RequestId;
        }

        public String getBizId() {
            return BizId;
        }

        public void setBizId(String BizId) {
            this.BizId = BizId;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }
    }

    public static class Data {
        public String codeId;
    }

    private static String getCode() {
        String result = "";
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            result += r.nextInt(10);
        }
        return result;
    }
}
