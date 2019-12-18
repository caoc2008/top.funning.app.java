<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/admin/order/css/list.css?" rel="stylesheet">
<link href="/admin/order/css/common.css?" rel="stylesheet">
<div role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <form method="get" class="row search">
        <input type="text" name="id" class="form-control col-md-2" placeholder="订单编号"/>
        <input type="text" name="telNumber" class="form-control col-md-2" placeholder="电话号码"/>
        <input type="text" name="name" class="form-control col-md-2" placeholder="收件人"/>
        <input type="hidden" id="state_value" name="state"/>
        <div id="state_choice" class="dropdown">
            <button class="btn btn-info dropdown-toggle" type="button" id="state" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                状态: <span id="state_text"> - - </span>
            </button>
            <script>
                $(function () {
                    $("#state_choice a").click(function () {
                        let state = $(this).attr("state");
                        let text = $(this).html();
                        $("#state_value").val(state);
                        $("#state_text").html(text);
                    });
                });
            </script>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" state=""> - - </a>
                <a class="dropdown-item" state="1">待付款</a>
                <a class="dropdown-item" state="2">准备中</a>
                <a class="dropdown-item" state="3">已完成</a>
                <a class="dropdown-item" state="4">退款中</a>
                <a class="dropdown-item" state="5">已取消</a>
                <a class="dropdown-item" state="6">已退款</a>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">查询</button>
    </form>

    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>订单编号</th>
                <th>商品价格</th>
                <th>运费</th>
                <th>总价</th>
                <th>电话</th>
                <th>收件人</th>
                <th>状态</th>
                <th>用户编号</th>
                <th>下单时间</th>
                <th>支付时间</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data.orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.price}</td>
                    <td>${order.poster}</td>
                    <td>${order.priceAmount}</td>
                    <td>${order.telNumber}</td>
                    <td>${order.userName}</td>
                    <td>${order.stateStr}</td>
                    <td>${order.userId}</td>
                    <td>${order.createDT}</td>
                    <td>${order.payDT}</td>
                    <td>
                        <a href="search?id=${order.id}" target="_blank">详情</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <ul class="pagination">
            <li class="page-item <c:if test="${!page.previous.active}">disabled</c:if>">
                <a class="page-link" role="page_btn" page="${page.previous.value}">上一页</a>
            </li>
            <c:forEach items="${page.items}" var="item">
                <li class="page-item <c:if test="${item.active}">active</c:if>">
                    <a class="page-link" role="page_btn" page="${item.value}">${item.value}</a>
                </li>
            </c:forEach>
            <li class="page-item <c:if test="${!page.next.active}">disabled</c:if>">
                <a class="page-link" role="page_btn" page="${page.next.value}">下一页</a>
            </li>
            <script>
                $(function () {
                    let url = window.location.href;
                    let mapper = {};
                    let index = url.indexOf("?");

                    if (index != -1) {
                        let str = url.substr(index + 1);
                        let ps = str.split("&");

                        for (let j = 0; j < ps.length; j++) {
                            let p = ps[j];
                            let position = p.indexOf("=");
                            if (position > 0) {
                                let name = p.substring(0, position);
                                let value = p.substr(position + 1);
                                mapper[name] = value;
                            }
                        }
                    }

                    console.log(mapper);

                    url = url.substring(0, index);
                    let as = $("[role='page_btn']");
                    for (let i = 0; i < as.length; i++) {
                        let href = url + "?";
                        mapper["page"] = $(as[i]).attr("page");
                        for (let name in mapper) {
                            href += name + "=" + mapper[name] + "&";
                        }
                        $(as[i]).attr({"href": href});
                    }
                })

            </script>
        </ul>
    </div>
</div>