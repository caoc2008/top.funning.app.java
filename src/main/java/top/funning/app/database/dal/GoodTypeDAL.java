package top.funning.app.database.dal;

import org.apache.ibatis.annotations.*;
import top.funning.app.database.table.GoodType;

import java.util.List;
import java.util.Map;

public interface GoodTypeDAL {

    @Insert("insert into `GoodType`(name,state,shopId,sort) values(#{name},1,#{shopId},#{sort}) ")
    public int insert(GoodType type);

    @Select("select id,name,state,sort from `GoodType` where shopId = #{shopId}")
    public List<GoodType> getList(@Param("shopId") String shopId);

    @Select("select id,name from `GoodType` where state = 1 and shopId = #{shopId} order by sort")
    public List<GoodType> getUsefulList(@Param("shopId") String shopId);

    @Update("update `GoodType` set name=#{name},state=#{state},sort=#{sort} where id=#{id} and shopId = #{shopId}")
    public int update(GoodType type);

    @Delete("delete from `GoodType` where id=#{id} and shopId = #{shopId}")
    public int delete(@Param("id") String id, @Param("shopId") String shopId);

    @Select("select * from `GoodType` where shopId = #{shopId} and id=#{id}")
    GoodType get(@Param("id") int id, @Param("shopId") String shopId);

}
