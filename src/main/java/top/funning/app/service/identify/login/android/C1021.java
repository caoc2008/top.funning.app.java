package top.funning.app.service.identify.login.android;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.UserDAL;
import top.funning.app.database.table.User;
import top.funning.app.service.FnService;
import top.knxy.library.utils.PwdUtils;

import java.util.Calendar;
import java.util.Date;

public class C1021 extends FnService {

    @NotNull
    public String phone;

    @Length(min = 6, max = 18)
    public String password;

    @Override
    public void run() throws Exception {

        SqlSession session = getSqlSession();
        UserDAL mapper = session.getMapper(UserDAL.class);
        User user = mapper.getUserByPhone(phone, shopId);

        if (user == null) {
            createError(this, "user is not exist");
            return;
        }

        long now = new Date().getTime();

        if (user.getLastFailTime() != null) {
            if (now > user.getLastFailTime().getTime() * 24 * 60 * 60 * 1000) {
                user.setLastFailTime(getInitData());
                user.setFail(0);
                mapper.updateFail(user);
                session.commit();
            } else if (user.getFail() > 3) {
                createError(this, "input wrong password is too much, you can try 24h after.");
                return;
            }
        }

        password = PwdUtils.sha1(password + user.getSalt());
        if (password.equals(user.getPassword())) {
            if (user.getFail() > 0 || user.getLastFailTime() != null) {
                user.setLastFailTime(getInitData());
                user.setFail(0);
                mapper.updateFail(user);
                session.commit();
            }
            this.data = user;
            createSuccess(this);
        } else {
            int fail = user.getFail();
            fail++;
            user.setFail(fail);
            if (user.getLastFailTime() == null) {
                user.setLastFailTime(new Date());
            }
            mapper.updateFail(user);
            session.commit();
            createError(this, "password is wrong, it is the " + user.getFail() + " time you input wrong password");
        }
    }

    public static Date getInitData() {

        Calendar cal = Calendar.getInstance();
        // 如果想设置为某个日期，可以一次设置年月日时分秒，由于月份下标从0开始赋值月份要-1
        // cal.set(year, month, date, hourOfDay, minute, second);
        cal.set(2000, 0, 1, 0, 0, 0);
        return cal.getTime();

    }
}
