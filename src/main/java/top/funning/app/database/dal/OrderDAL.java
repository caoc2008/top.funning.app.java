package top.funning.app.database.dal;

import org.apache.ibatis.annotations.*;
import top.funning.app.database.model.Page;
import top.funning.app.database.table.Order;
import top.knxy.library.utils.TextUtils;

import java.util.List;

public interface OrderDAL {


    @Insert("insert into `Order`(id,goods,price,createDT,userId,state,shopId)" +
            " values(#{id},#{goods},#{price},#{createDT},#{userId},#{state},#{shopId})")
    int insert(Order order);

    @Select("select id,goods,price,poster,amount,provinceName,cityName,countyName,shopId," +
            "detailInfo,telNumber,userName,nationalCode,postalCode,note,state,userId,createDT,payDT " +
            "from `Order` where id = #{id} and userId = #{userId} and shopId = #{shopId} limit 1")
    Order getOrderByUser(Order order);

    @Select("select id,goods,price,poster,amount,provinceName,cityName,countyName,shopId," +
            "detailInfo,telNumber,userName,nationalCode,postalCode,note,state,userId,createDT,payDT,alipayTradeNo,payMethod " +
            "from `Order` where id=#{id} and shopId = #{shopId} limit 1")
    Order getOrder(@Param("id") String id, @Param("shopId") String shopId);

    @Select("select id,goods,price,poster,amount,provinceName,cityName,countyName,shopId," +
            "detailInfo,telNumber,userName,nationalCode,postalCode,note,state,userId,createDT,payDT " +
            "from `Order` where id=#{id} limit 1")
    Order getOrderByPayRefund(@Param("id") String id);

    @Update("update `Order` set note = #{note}," +
            "poster = #{poster}," +
            "amount = #{amount}," +
            "provinceName = #{provinceName}," +
            "cityName = #{cityName}," +
            "countyName = #{countyName}," +
            "detailInfo = #{detailInfo}," +
            "telNumber = #{telNumber}," +
            "userName = #{userName}," +
            "nationalCode = #{nationalCode}," +
            "postalCode = #{postalCode}," +
            "payDT = #{payDT}," +
            "userName = #{userName}," +
            "state = #{state}, " +
            "payMethod = #{payMethod}, " +
            "alipayTradeNo = #{alipayTradeNo} " +
            "where id = #{id} and userId = #{userId} and shopId = #{shopId} ")
    int update(Order order);

    @Select("select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
            "from `Order` where state=2 or state=4 and shopId = #{shopId}  order by payDT desc " +
            "limit #{index},#{size}")
    List<Order> getUndoList(@Param("index") int index,@Param("size") int size, @Param("shopId") String shopId);

    @Select("select id,goods,price,poster,amount,provinceName,cityName,countyName," +
            "detailInfo,telNumber,userName,nationalCode,postalCode,note,state,userId,createDT,payDT " +
            "from `Order` where userId = #{userId} and shopId = #{shopId} order by createDT desc")
    List<Order> getListByUserId(@Param("userId") String userId, @Param("shopId") String shopId);

    @Select("select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
            "from `Order` " +
            "where shopId = #{shopId} " +
            "order by createDT desc " +
            "limit #{index},#{size}")
        // @SelectProvider(type = SqlProvider.class, method = "getList")
    List<Order> getList(@Param("index") int index,
                        @Param("size") int size,
                        @Param("shopId") String shopId);


    @Select("select id,state from `Order` where id=#{id} and userId=#{userId} and shopId = #{shopId} limit 1")
    Order getState(@Param("id") String id, @Param("shopId") String shopId);


    @Select("select count(*) from `Order` where state=2 or state=4 and shopId=#{shopId}")
    int getUnDoNumber(@Param("shopId") String shopId);

    @Update("update `Order` set state = #{state} " +
            "where id = #{id} and shopId = #{shopId}")
    int changeState(Order order);

    @Update("update `Order` set state = #{state} " +
            "where id = #{id} and userId = #{userId}")
    int changeStateByUser(Order order);

    /**
     * Reference:https://www.cnblogs.com/guoyafenghome/p/9123442.html
     */
    class SqlProvider {
        public String getList(@Param("index") int index,
                              @Param("size") int size,
                              @Param("userId") String userId) {
            String sql =
                    "select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
                            "from `Order` "
                            + (TextUtils.isEmpty(userId) ? "" : "where userId = #{userId} ") +
                            "order by createDT desc " +
                            "limit #{index},#{size}";
            return sql;
        }
    }
}
