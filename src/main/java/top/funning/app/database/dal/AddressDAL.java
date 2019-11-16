package top.funning.app.database.dal;

import org.apache.ibatis.annotations.*;
import top.funning.app.database.table.Address;
import top.funning.app.database.table.Admin;

import java.util.List;

public interface AddressDAL {

    @Insert("insert into `Address`(name,phone,province,city,area,detail,poster,userId,shopId) values " +
            "(#{name},#{phone},#{province},#{city},#{area},#{detail},#{poster},#{userId}),#{shopId})")
    int add(Address address);

    @Delete("delete from `Address` where id=#{id} and userId=#{userId} and shopId=#{shopId}")
    int delete(@Param("id") String id, @Param("userId") String userId, @Param("shopId") String shopId);

    @Update("update `Address` set name=#{name},phone=#{phone},province=#{province},city=#{city},area=#{area},detail=#{detail},poster=#{poster},poster=#{userId},poster=#{shopId} " +
            "where id=#{id}")
    int update(Address address);

    @Select("select id,name,phone,province,city,area,detail,poster,userId,shopId from `Address` where id=#{id} and userId=#{userId} and shopId=#{shopId} limit 1")
    Address get(@Param("id") String id, @Param("userId") String userId, @Param("shopId") String shopId);

    @Select("select id,name,phone,province,city,area,detail,poster,userId,shopId from `Address` where userId=#{userId} and shopId=#{shopId}")
    List<Address> getList(@Param("userId") String userId, @Param("shopId") String shopId);
}
