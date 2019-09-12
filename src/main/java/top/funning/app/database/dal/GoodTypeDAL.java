package top.funning.app.database.dal;

import org.apache.ibatis.annotations.*;
import top.funning.app.database.table.GoodType;

import java.util.List;
import java.util.Map;

public interface GoodTypeDAL {

    @Insert("insert into `GoodType`(name,state,shopId) values(#{name},1,#{shopId}) ")
    public int insert(@Param("name") String name, @Param("shopId") String shopId);

    @Select("select id,name,state from `GoodType` where shopId = #{shopId}")
    public List<GoodType> getList(@Param("shopId") String shopId);

    @Select("select id,name from `GoodType` where state = 1 and shopId = #{shopId}")
    public List<Map> getUsefulList(@Param("shopId") String shopId);

    @Update("update `GoodType` set name=#{name},state=#{state} where id=#{id} and shopId = #{shopId}")
    public int update(GoodType type);

    @Delete("delete from `GoodType` where id=#{id} and shopId = #{shopId}")
    public int delete(@Param("id") String id, @Param("shopId") String shopId);
}
