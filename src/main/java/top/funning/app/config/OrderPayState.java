package top.funning.app.config;


/**
 * state = {"待付款" = 1,"准备中" = 2,"已完成" = 3,"退款中" = 4,"已取消" = 5,"已退款" = 6,"已付款" = 7}
 * 待付款 -> 准备中 -> 已完成
 * 待付款 -> 已取消
 * 待付款 -> 准备中 -> 退款中 -> 已退款
 * 待付款 -> 准备中 -> 退款中 -> 已完成 (收单，准备(开切，包装)，配送)
 **/
public class OrderPayState {

    public final static int waitPay = 1;
    public final static int doing = 2;
    public final static int finish = 3;
    public final static int refunding = 4;
    public final static int cancel = 5;
    public final static int refunded = 6;
    public final static int paid = 7;
}
