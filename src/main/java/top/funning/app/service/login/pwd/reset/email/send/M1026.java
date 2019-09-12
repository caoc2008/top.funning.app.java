package top.funning.app.service.login.pwd.reset.email.send;

import com.sun.mail.util.MailSSLSocketFactory;
import net.sf.oval.constraint.Email;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.C;
import top.funning.app.database.Redis;
import top.funning.app.database.dal.AdminDAL;
import top.funning.app.database.dal.UserDAL;
import top.funning.app.database.table.Admin;
import top.funning.app.service.FnService;
import top.knxy.library.utils.MyBatisUtils;
import top.knxy.library.utils.ServiceUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class M1026 extends FnService {

    @Email
    public String email;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        AdminDAL userDAL = sqlSession.getMapper(AdminDAL.class);
        Admin admin = userDAL.getAdminByUserName(email);
        if (admin == null) {
            createError(this, "用户不存在");
            return;
        }

        String to = email;// 收件人的电子邮件 ID
        String from = "app.funning@knxy-inc.com";// 发件人的电子邮件 ID
        String host = "smtp.qq.com";// 假设您是从本地主机发送电子邮件
        Properties properties = System.getProperties();// 获取系统的属性
        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.put("mail.smtp.auth", "true");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("app.funning@knxy-inc.com", "dxcnyhkuklkmfghc"); //发件人邮件用户名、授权码
            }
        });


        MimeMessage message = new MimeMessage(session);// 创建一个默认的 MimeMessage 对象
        message.setFrom(new InternetAddress(from));// 设置 From: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));// 设置 To: header field of the header.
        message.setSubject("[饭宁] 重置密码");// 设置 Subject: header field

        String code = ServiceUtils.getUUid();

        StringBuffer text = new StringBuffer();
        text.append("点击以下链接可以重置您的密码 \n");

        text.append(C.App.domain + "/reset_pwd/" + code);
        text.append("\n\n");

        text.append("链接有效期：3小时 \n\n\n");
        text.append("饭宁 -- 开源的小程序\n");
        text.append("愿世界和平！");

        message.setText(text.toString());// 现在设置实际消息
        try {
            Transport.send(message);// 发送消息
            Redis.set(code, email, 10800);
            createSuccess(this);
        } catch (Exception e) {
            createError(this, e.getMessage());
        }
    }
}
