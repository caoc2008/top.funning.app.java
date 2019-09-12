package top.funning.app.database.dal;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.funning.app.database.table.Shop;

public interface ShopDAL {

    @Insert("insert into `Shop`(appName,phoneNum,logoUrl) values(#{appName},#{phoneNum},#{logoUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(Shop shop);

    @Select("select * from `Shop` where id=#{id} limit 1")
    Shop get(String id);

    @Update("update `Shop` set " +
            "appName=#{appName}," +
            "phoneNum=#{phoneNum}," +
            "wcpayMchId=#{wcpayMchId}," +
            "wcpayApiKey=#{wcpayApiKey}," +
            "wechatAppid=#{wechatAppid}," +
            "wechatSecret=#{wechatSecret}," +
            "logoUrl=#{logoUrl}," +
            "p12FileName=#{p12FileName} where id=#{id}")
    int update(Shop shop);
}
