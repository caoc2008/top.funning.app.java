package top.funning.app.database.dal;

import org.apache.ibatis.annotations.*;
import top.funning.app.database.table.User;

import java.util.List;
import java.util.Map;

public interface UserDAL {


    @Select({"select u.id,u.openId, SUM(o.amount) amount " +
            "from `User` u left join `Order` o " +
            "on u.id = o.userId and  o.state=3 and u.shopId=#{shopId} group by u.id order by u.id desc"})
    List<Map> getList(@Param("shopId") String shopId);

    @Select({"select id,openId from `User` where openId = #{openId} and shopId=#{shopId} limit 1"})
    User getUserByOpenId(@Param("openId") String openId,@Param("shopId") String shopId);

    @Select({"select * from `User` where id = #{id} and shopId = #{shopId} limit 1"})
    User getUser(@Param("id") String id,@Param("shopId") String shopId);

    @Insert({"insert into User(openId,shopId) values(#{openId},#{shopId})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Integer.class, before = false)
    int insert(User user);

    @Update("update `User` set nickName=#{nickName},gender=#{gender},avatarUrl=#{avatarUrl},province=#{province},city=#{city},country=#{country} where id=#{id} and shopId=#{shopId}")
    void update(User user);
}
