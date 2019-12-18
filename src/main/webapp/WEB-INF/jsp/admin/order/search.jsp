<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/admin/order/css/search.css" rel="stylesheet">
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <form method="get" class="row form">
        <input name="id" type="text" placeholder="订单编号" class="form-control col-md-3" value="${data.id}"/>
        <button type="submit" class="btn btn-primary col-md-1">查询</button>
    </form>
    <hr/>

    <c:if test="${data.id!=null}">
        <div id="content">
            <div><label>订单编号：</label>${data.id}</div>
            <div class="row">
                <div class="col-md-3"><label>商品价格：</label>${data.price}</div>
                <div class="col-md-3"><label>运费：</label>${data.poster}</div>
                <div class="col-md-3 text_color_red"><label>总价：</label>${data.priceAmount}</div>
                <div class="col-md-3 text_color_red"><label>状态：</label>${data.stateStr}</div>
            </div>
            <div class="row">
                <div class="col-md-3"><label>消费者编号：</label>${data.userId}</div>
                <div class="col-md-3"><label>下单时间：</label>${data.createDT}</div>
                <div class="col-md-3"><label>支付时间：</label>${data.payDT}</div>
                <div class="col-md-3"><label>支付方式：</label>${data.payMethodText}</div>
            </div>
            <div>
                <label>运送地址：</label>
                    ${data.provinceName} ${data.cityName} ${data.countyName}
                    ${data.detailInfo}
                    ${data.telNumber}
                    ${data.userName}
            </div>

            <div>
                <label>备注：</label>${data.note}
            </div>

            <label>商品列表</label>
            <div class="good_list row">
                <c:forEach items="${data.goodList}" var="good">
                    <div class="col-md-2">
                        <div goodId="${good.body.id}" class="item">
                            <img alt="${good.body.name}" src="${imageHost}${good.body.imageUrl}"/>
                            <div><label>商品名称：</label>${good.body.name}</div>
                            <div class="text_color_orange"><label>商品价格：</label>${good.body.price}</div>
                            <div class="text_color_orange"><label>购买数量：</label>${good.amount}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <form class="operation" method="post">
                <c:if test="${data.state==2}">
                    <button type="button" id="btn_finish" onclick="finish()"
                            class="btn btn-success col-md-3">订单已完成
                    </button>
                </c:if>
                <c:if test="${data.state==4}">
                    <button type="button" id="btn_refund" onclick="refund()"
                            class="btn btn-danger col-md-3">退款
                    </button>
                </c:if>
            </form>
        </div>
    </c:if>
</main>

<script>
    function finish() {
        if (window.confirm('你确定吗?')) {
            LoadingDialog.show()
            Web.request("M1005", {
                id: "${data.id}"
            }, {
                onSuccess: function (res) {
                    LoadingDialog.hide();
                    window.location.reload();
                },
                onError: function (res) {
                    LoadingDialog.hide();
                    alert(res.msg);
                }
            });
        }
    }

    function refund() {
        if (window.confirm('你确定吗?')) {
            LoadingDialog.show()
            Web.request("M1018", {
                id: "${data.id}"
            }, {
                onSuccess: function (res) {
                    LoadingDialog.hide();
                    window.location.reload();
                },
                onError: function (res) {
                    LoadingDialog.hide();
                    alert(res.msg);
                }
            });
        }
    }
</script>