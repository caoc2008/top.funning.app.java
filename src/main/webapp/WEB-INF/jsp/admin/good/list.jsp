<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/admin/good/js/list.js"></script>
<div role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>商品编号</th>
                <th>名称</th>
                <th>价格</th>
                <th>类型</th>
                <th>状态</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data.dataList}" var="item">
                <tr id="tr_${item.id}">
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.typeName}</td>
                    <td>${item.stateStr}</td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm"
                                onclick="Page.search('${item.id}')" target="_blank">详情
                        </button>
                        <button type="button" class="btn btn-danger btn-sm"
                                onclick="Page.delete('${item.id}')" target="_blank">删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <ul class="pagination">
            <li class="page-item <c:if test="${!page.previous.active}">disabled</c:if>">
                <a class="page-link" href="?page=${page.previous.value}">上一页</a>
            </li>
            <c:forEach items="${page.items}" var="item">
                <li class="page-item <c:if test="${item.active}">active</c:if>">
                    <a class="page-link" href="?page=${item.value}">${item.value}</a>
                </li>
            </c:forEach>
            <li class="page-item <c:if test="${!page.next.active}">disabled</c:if>">
                <a class="page-link" href="?page=${page.next.value}">下一页</a>
            </li>
        </ul>
    </div>
</div>