package top.funning.app.service.order.list;

import com.google.gson.Gson;
import top.funning.app.service.StateTranslator;
import top.knxy.library.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderCollection {
    public List<Order> orders;

    public OrderCollection(List<top.funning.app.database.table.Order> orders) {
        this.orders = new ArrayList<>();
        Gson gson = new Gson();
        for (top.funning.app.database.table.Order o : orders) {
            this.orders.add(createOrder(o,gson));
        }
    }

    public static Order createOrder(top.funning.app.database.table.Order o, Gson gson) {
        String json = "{ \"goodList\" : " + o.getGoods() + "}";
        Order order = gson.fromJson(json, Order.class);

        order.id = o.getId();

        order.price = o.getPrice();
        order.poster = o.getPoster();
        order.priceAmount = o.getAmount();

        order.provinceName = o.getProvinceName();
        order.cityName = o.getCityName();
        order.countyName = o.getCountyName();
        order.detailInfo = o.getDetailInfo();
        order.telNumber = o.getTelNumber();
        order.userName = o.getUserName();
        order.nationalCode = o.getNationalCode();
        order.postalCode = o.getPostalCode();

        order.note = o.getNote();
        order.state = String.valueOf(o.getState());
        order.stateStr = StateTranslator.getNormalOrderStateStr(o.getState());
        order.userId = o.getUserId();
        if (o.getCreateDT() != null)
            order.createDT = DateUtils.dateToString(o.getCreateDT(), DateUtils.dateStringType2);
        if (o.getPayDT() != null)
            order.payDT = DateUtils.dateToString(o.getPayDT(), DateUtils.dateStringType2);

        int goodAmount = 0;
        for (Order.Good good : order.goodList) {
            goodAmount += Integer.valueOf(good.amount);
        }
        order.goodAmount = String.valueOf(goodAmount);
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public static class Order {
        public String id;
        public String price;
        public String poster;
        public String priceAmount;

        public String provinceName;
        public String cityName;
        public String countyName;
        public String detailInfo;
        public String telNumber;
        public String userName;
        public String nationalCode;
        public String postalCode;

        public String note;
        public String state;
        public String stateStr;
        public String userId;
        public String createDT;
        public String payDT;
        public String goodAmount;

        public List<Good> goodList;



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getPriceAmount() {
            return priceAmount;
        }

        public void setPriceAmount(String priceAmount) {
            this.priceAmount = priceAmount;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCountyName() {
            return countyName;
        }

        public void setCountyName(String countyName) {
            this.countyName = countyName;
        }

        public String getDetailInfo() {
            return detailInfo;
        }

        public void setDetailInfo(String detailInfo) {
            this.detailInfo = detailInfo;
        }

        public String getTelNumber() {
            return telNumber;
        }

        public void setTelNumber(String telNumber) {
            this.telNumber = telNumber;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNationalCode() {
            return nationalCode;
        }

        public void setNationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCreateDT() {
            return createDT;
        }

        public void setCreateDT(String createDT) {
            this.createDT = createDT;
        }

        public String getPayDT() {
            return payDT;
        }

        public void setPayDT(String payDT) {
            this.payDT = payDT;
        }

        public String getGoodAmount() {
            return goodAmount;
        }

        public void setGoodAmount(String goodAmount) {
            this.goodAmount = goodAmount;
        }

        public List<Good> getGoodList() {
            return goodList;
        }

        public void setGoodList(List<Good> goodList) {
            this.goodList = goodList;
        }



        public static class Good {

            /**
             * amount : 1
             * body : {"price":"40.00","imageUrl":"http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg","name":"雪梨","description":"3.0rmb/kg","id":53,"type":"1","stock":"300"}
             */
            public String amount;
            public BodyEntity body;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public BodyEntity getBody() {
                return body;
            }

            public void setBody(BodyEntity body) {
                this.body = body;
            }

            public class BodyEntity {
                /**
                 * price : 40.00
                 * imageUrl : http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg
                 * name : 雪梨
                 * description : 3.0rmb/kg
                 * id : 53
                 * type : 1
                 * stock : 300
                 */
                public String id;
                public String price;
                public String imageUrl;
                public String name;
                public String description;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(String imageUrl) {
                    this.imageUrl = imageUrl;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }
        }
    }
}
