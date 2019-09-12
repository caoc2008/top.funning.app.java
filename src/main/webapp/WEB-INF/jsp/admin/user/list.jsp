<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-16
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>用户编号</th>
                <th>微信OpenId</th>
                <th>消费总额</th>
                <th>会员积分</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data.users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.openId}</td>
                    <td>${user.amount}</td>
                    <td>${user.score}</td>
                    <td>
                        <a href="/admin/order/list?userId=${user.id}" target="_blank">查看用户订单</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>