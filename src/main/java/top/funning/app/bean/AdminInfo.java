package top.funning.app.bean;

import top.funning.app.database.table.Admin;

import java.io.Serializable;

public class AdminInfo implements Serializable {

    public String id;
    public String name;
    public String shopId;

    public AdminInfo(String id, String name, String shopId) {
        this.id = id;
        this.name = name;
        this.shopId = shopId;
    }

    public AdminInfo(Admin admin) {
        this.id = String.valueOf(admin.getId());
        this.name = String.valueOf(admin.getUsername());
        this.shopId = String.valueOf(admin.getShopId());
    }
}
