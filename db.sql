create table `User`
(
  id        int auto_increment
    constraint `PRIMARY`
    primary key,
  openId    varchar(32)  null,
  nickName  varchar(32)  null,
  gender    int          null,
  avatarUrl varchar(255) null,
  province  varchar(32)  null,
  city      varchar(32)  null,
  country   varchar(32)  null,
  shopId       int
);

/**
state = {"show" = 1,"hide" = 2}
**/
create table `GoodType`
(
  id    int auto_increment
    constraint `PRIMARY`
    primary key,
  name  varchar(32) null,
  state int         null,
  shopId       int
);


/**
state = {"1","2"}
state = 1 : 上架
state = 2 : 下架

type < 1 代表没有type
**/
create table `Good`
(
  id          int auto_increment
    constraint `PRIMARY`
    primary key,
  name        varchar(32)    null,
  content text null,
  description varchar(128)   null,
  imageUrl    varchar(128)   null,
  price       decimal(14, 2) null,
  stock       int            null,
  state       int default 1  null,
  type        int            null,
  shopId      int
);


/**
state = {"待付款" = 1,"准备中" = 2,"已完成" = 3,"退款中" = 4,"已取消" = 5,"已退款" = 6,"已付款" = 7}
待付款 -> 准备中 -> 已完成
待付款 -> 已取消
待付款 -> 准备中 -> 退款中 -> 已退款
待付款 -> 准备中 -> 退款中 -> 已完成 (收单，准备(开切，包装)，配送)
**/
create table `Order`
(
  id           char(32)       not null
    constraint `PRIMARY`
    primary key,
  goods        text           null,
  price        decimal(14, 2) null,
  poster       decimal(14, 2) null,
  amount       decimal(14, 2) null,
  provinceName varchar(32)    null,
  cityName     varchar(32)    null,
  countyName   varchar(32)    null,
  detailInfo   varchar(255)   null,
  telNumber    varchar(32)    null,
  userName     varchar(32)    null,
  nationalCode varchar(32)    null,
  postalCode   varchar(32)    null,
  note         varchar(255)   null,
  state        int            not null,
  userId       int            null,
  createDT     datetime       not null,
  payDT        datetime       null,
  shopId       int
);

create table `Shop`(
    id  int auto_increment primary key,
    appName varchar(64),
    phoneNum varchar(32),
    logoUrl varchar(256),
    wcpayMchId varchar(16),
    wcpayApiKey varchar(32),
    wechatAppid varchar(18),
    wechatSecret varchar(32),
    p12FileName char(32)
);

 
create table `Address`
(
    id  int auto_increment primary key,
    name varchar(32),
    phone varchar(32),
    province varchar(32),
    city varchar(32),
    area varchar(32),
    detail varchar(128),
    poster varchar(8),
    userId varchar(32),
    shopId varchar(32),
)

create table `Admin`
(
  id           int auto_increment
    primary key,
  username     varchar(64) not null,
  password     char(40)    not null,
  fail         int         null,
  lastFailTime datetime    null,
  salt         char(12)    not null,
  shopId       int
);

insert into Admin(username,password,salt)values ('admin1.xyg.app@funning.top','3f4870db36720549b5da31975febf212cfb33e70','33f1c5df698d');

insert into GoodType(name,state) values
('家居日常',1),
('洗浴护理',1),
('除湿驱蚊',1),
('文具文品',1),
('五金工具',1)

insert into Good(id,name,description,imageUrl,price,stock,type) values
(1,'维达(Vinda) 手帕纸','超韧4层纸巾*36包纸巾(无香)','1.jpg','19.00',300,9),
(2,'洗衣液','','2.jpg','40.00',300,9),
(3,'洗洁精','','3.jpg','11.00',300,9),
(4,'手帕','','4.jpg','15.00',300,9),
(5,'利是封','','5.jpg','5.00',300,9),
(6,'威猛先生厨房去污剂','','6.jpg','25.00',300,9),
(7,'电蚊液','','7.jpg','15.00',300,9),
(8,'电池','','8.jpg','2.00',300,9),
(9,'雨伞','','9.jpg','25.00',300,9),
(10,'卫生棉','','10.jpg','10.00',300,9),
(11,'牙刷','','11.jpg','7.00',300,9),
(12,'牙膏','','12.jpg','21.00',300,9),
(13,'沐浴露','','13.jpg','32.00',300,10),
(14,'香皂','','14.jpg','2.00',300,10),
(15,'洗衣液','','15.jpg','8.00',300,10),
(16,'洗头水','','16.jpg','38.00',300,10),
(17,'漱口水','','17.jpg','10.00',300,10),
(18,'剃须刀','','18.jpg','108.00',300,10),
(19,'电蚊液','','19.jpg','12.00',300,11),
(20,'花露水','','20.jpg','11.00',300,11),
(21,'电蚊器','','21.jpg','15.00',300,11),
(22,'电蚊片','','22.jpg','21.00',300,11),
(23,'电蚊拍','','23.jpg','21.00',300,11),
(24,'蚊香','','24.jpg','10.00',300,11),
(25,'铅笔','','25.jpg','2.00',300,13),
(26,'圆珠笔','','26.jpg','2.00',300,13),
(27,'原稿纸','','27.jpg','1.00',300,13),
(28,'信纸','','28.jpg','1.00',300,13),
(29,'橡皮','','29.jpg','1.00',300,13),
(30,'锤子','','31.jpg','20.00',300,14),
(31,'钉子','','32.jpg','1.00',300,14),
(32,'扳手','','33.jpg','18.00',300,14),
(33,'螺丝','','34.jpg','1.00',300,14),
(34,'螺丝刀','','35.jpg','10.00',300,14),
(35,'铁丝','','36.jpg','4.00',300,14)

insert into GoodDetail(id,goodId,content) values
(1,1,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}'),
(2,2,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}'),
(3,3,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}'),
(4,4,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}'),
(5,5,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}'),
(6,6,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}'),
(7,7,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}'),
(8,8,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}'),
(9,9,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}'),
(10,10,'{"header": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg", "430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}')
