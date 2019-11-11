package top.funning.app.service.identify.password.manager.modify;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.AdminDAL;
import top.funning.app.database.table.Admin;
import top.funning.app.service.FnService;
import top.knxy.library.utils.PwdUtils;

import javax.sql.rowset.serial.SerialException;

public class M1019 extends FnService {

    @NotNull
    public String adminId;
    @Length(min = 6,max = 18)
    public String prePassword;
    @Length(min = 6,max = 18)
    public String newPassword;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();;
        AdminDAL dal = session.getMapper(AdminDAL.class);
        Admin admin = dal.getAdminById(adminId);
        if (admin == null) {
            throw new SerialException();
        }

        String salt = admin.getSalt();
        String prePassword = PwdUtils.sha1(this.prePassword + salt);
        if (!prePassword.equals(admin.getPassword())) {
            createError(this, "密码错误");
            return;
        }

        String newSalt = PwdUtils.createSalt();
        admin.setSalt(newSalt);
        String newPassword = PwdUtils.sha1(this.newPassword + newSalt);
        admin.setPassword(newPassword);

        dal.updatePassword(admin);
        session.commit();

        createSuccess(this);
    }
}
