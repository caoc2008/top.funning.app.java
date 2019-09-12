package top.funning.app.database.dal;

import org.apache.ibatis.annotations.*;
import top.funning.app.database.model.Page;
import top.funning.app.database.table.Good;

import java.util.List;
import java.util.Map;

public interface GoodDAL {

    @Select("select g.id,g.name,g.description,g.imageUrl,g.price,g.content " +
            "from Good g " +
            "where g.id=#{id} and g.state=1 and g.shopId=#{shopId}" +
            "limit 1 ")
    Map getGoodForUser(@Param("id") String id, @Param("shopId") String shopId);


    @Select("select g.id,g.name,g.description,g.imageUrl,g.price,g.content,g.state,gt.id type, gt.name typeStr " +
            "from `Good` g left join `GoodType` gt on g.type= gt.id " +
            "where g.id=#{id}  and g.shopId=#{shopId}" +
            "limit 1")
    Map getGoodForAdmin(@Param("id") String id, @Param("shopId") String shopId);

    @Select("select id,name,description,imageUrl,price,stock,type from `Good` where id=#{id} and shopId=#{shopId} and state = 1")
    Good get(@Param("id") String id, @Param("shopId") String shopId);

    @Select("select g.id,g.name,g.price,g.type,gt.name typeName,g.state " +
            "from `Good` g left join `GoodType` gt on gt.id = g.type " +
            "where g.shopId = #{shopId} " +
            "order by g.id desc limit #{index},#{size}")
    List<Map> getList(@Param("index") int index, @Param("size") int size, @Param("shopId") String shopId);

    @Select({"select g.id," +
            "g.name," +
            "g.description," +
            "g.imageUrl," +
            "g.price," +
            "g.stock," +
            "g.type " +
            "from Good g,GoodType gd " +
            "where g.type = gd.id and gd.state = 1 and g.state = 1 and g.shopId = #{shopId} order by id desc"})
    List<Good> getUsefulList(@Param("shopId") String shopId);

    @Select("select id,name,price,imageUrl " +
            "from `Good` where state=1 and shopId = #{shopId} " +
            "order by name ")
    List<Map> getSearchList(@Param("shopId") String shopId);

    @Delete("delete from `Good` where id = #{id} and shopId = #{shopId} ")
    int delete(@Param("id") String id, @Param("shopId") String shopId);

    @Update("update `Good` set content=#{content},name=#{name},description=#{description},imageUrl=#{imageUrl},price=#{price},type=#{type},state=#{state} " +
            "where id=#{id} and shopId = #{shopId}")
    int update(Good good);

    @Insert("insert into `Good`(id,name,description,imageUrl,price,type,state,content,shopId) values " +
            "(#{id},#{name},#{description},#{imageUrl},#{price},#{type},#{state},#{content},#{shopId})")
    int insert(Good good);
}
