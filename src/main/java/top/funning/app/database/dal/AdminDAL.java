package top.funning.app.database.dal;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.funning.app.database.table.Admin;

public interface AdminDAL {

    @Select("select id,username,password,fail,lastFailTime,salt,shopId from Admin where username=#{username} limit 1")
    Admin getAdminByUserName(String username);

    @Select("select id,username,password,fail,lastFailTime,salt,shopId from Admin where id=#{id} limit 1")
    Admin getAdminById(String id);

    @Update("update `Admin` set fail=#{fail}, lastFailTime=#{lastFailTime} where id = #{id} and shopId = #{shopId}")
    void updateFail(Admin admin);

    @Update("update `Admin` set password=#{password}, salt=#{salt} where id = #{id} and shopId = #{shopId}")
    int updatePassword(Admin admin);

    @Insert("insert into `Admin`(username,password,salt,shopId) values (#{username},#{password},#{salt},#{shopId})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int add(Admin admin);
}
