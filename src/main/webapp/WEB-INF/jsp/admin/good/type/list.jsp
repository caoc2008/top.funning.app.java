<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/admin/good/type/css/list.css" rel="stylesheet">
<script src="/admin/good/type/js/list.js?version=${version}"></script>
<div role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4 main">

    <div class="type_add">
        <label>添加类型: </label>
        <input type="text" name="name" class="form-control form-control-sm col-md-3" placeholder="类型名称"/>
        <input type="number" name="sort" class="form-control form-control-sm col-md-3" placeholder="排序"/>
        <button type="submit" onclick="Type.add()" class="btn btn-primary btn-sm col-md-2">添加</button>
    </div>
    <hr/>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>状态</th>
                <th>排序</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data.typeList}" var="type">
                <tr id="tr_${type.id}">
                    <td>${type.id}</td>
                    <td>
                        <span role="sp_name">${type.name}</span>
                        <input role="input_name" type="text" value="${type.name}"
                               class="form-control form-control-sm col-md-3 hide"
                               style="margin: 0"
                               placeholder="请输入名称"/>
                    </td>
                    <td>
                        <span role="sp_state" state="${type.state}">
                        </span>
                        <div class="dropdown hide" role="input_state">
                            <button state="${type.state}" class="btn btn-secondary dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Dropdown
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                <button class="dropdown-item" onclick="Dropdown.itemClick(1,${type.id})" type="button">
                                    显示
                                </button>
                                <button class="dropdown-item" onclick="Dropdown.itemClick(2,${type.id})" type="button">
                                    隐藏
                                </button>
                            </div>
                        </div>
                    </td>
                    <td>
                        <span role="sp_sort">${type.sort}</span>
                        <input role="input_sort" type="number" value="${type.sort}"
                               class="form-control form-control-sm col-md-3 hide"
                               style="margin: 0"
                               placeholder="请输入序号"/>
                    </td>
                    <td>
                        <button role="btn_edit" onclick="Mode.edit(${type.id})" class="btn btn-sm btn-primary">
                            编辑
                        </button>
                        <button role="btn_cancel" onclick="Mode.cancel(${type.id})" class="btn btn-sm btn-primary hide">
                            取消
                        </button>
                        <button role="btn_commit" onclick="Type.modify(${type.id})" class="btn btn-sm btn-success hide">
                            提交
                        </button>
                        <button role="btn_delete" onclick="Type.delete(${type.id})" class="btn btn-sm btn-danger">
                            删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>