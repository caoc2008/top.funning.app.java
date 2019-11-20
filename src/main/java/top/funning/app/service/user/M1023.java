package top.funning.app.service.user;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.UserDAL;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class M1023 extends FnService {
    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        UserDAL userDal = session.getMapper(UserDAL.class);
        Data data = new Data(userDal.getList(header.shopId));
        this.data = data;

        createSuccess(this);
    }


    public static class Data {
        private List<User> users;

        public Data(List<Map> list) throws Exception {
            users = new ArrayList<>();
            for (Map map : list) {
                users.add(ServiceUtils.mapToBean(map, User.class));
            }
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        public static class User {
            private String id;
            private String openId;
            private String amount;
            private String score;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getScore() {
                return score;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                if (amount == null) return;
                this.score = String.valueOf(Double.valueOf(Double.valueOf(amount) / 100).intValue());
                this.amount = amount;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }
        }
    }
}
