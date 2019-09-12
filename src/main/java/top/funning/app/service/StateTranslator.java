package top.funning.app.service;

public class StateTranslator {

    public static String getNormalOrderStateStr(String state) {
        return getNormalOrderStateStr(Integer.valueOf(state));
    }

    public static String getNormalOrderStateStr(int state) {
        switch (state) {
            case 1:
                return "待付款";
            case 2:
                return "准备中";
            case 3:
                return "已完成";
            case 4:
                return "退款中";
            case 5:
                return "已取消";
            case 6:
                return "已退款";
            case 7:
                return "已付款";
        }
        return null;
    }

    public static String getGroupOrderStateText(String state) {
        return getGroupOrderStateText(Integer.valueOf(state));
    }

    public static String getGroupOrderStateText(int state) {
        switch (state) {
            case 1:
                return "待付款";
            case 2:
                return "拼团中";
            case 3:
                return "待取货";
            case 4:
                return "已完成";
            case 5:
                return "退款中";
            case 6:
                return "已取消";
            case 7:
                return "已退款";
        }
        return null;
    }


    public static String getGroupGoodStateText(String state) {
        return getGroupGoodStateText(Integer.valueOf(state));
    }

    public static String getGroupGoodStateText(int state) {
        switch (state) {
            case 1:
                return "上架中";
            case 2:
                return "已下架";
        }
        return null;
    }

}
