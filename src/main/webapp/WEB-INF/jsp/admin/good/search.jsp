<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="/admin/good/css/search.css" rel="stylesheet">
<script src="/js/qiniu.min.js"></script>
<script src="/admin/good/js/search.js?time=${version}"></script>
<script>
    let imageHost = "${imageHost}";
    let imageUrl = "${data.imageUrl}";
    $(function () {
        Page.data = ${data.detail==null?"null":data.detail};
        console.log(Page.data);
        Page.init();
        let id = "${data.id}";
    });
</script>
<div role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <form method="get" class="row form">
        <input name="id" type="text" placeholder="商品编号" class="form-control col-md-3" value="${data.id}"/>
        <button type="submit" class="btn btn-primary col-md-1">查询</button>
    </form>

    <hr/>
    <c:if test="${data.id!=null}">
        <div id="content">
            <div>
                <label>商品编号：</label>
                    ${data.id}
                <input id="id" type="hidden" value="${data.id}"/>
            </div>
            <div class="row">
                <div class="col-md-6 base_info">
                    <div class="row">
                        <div class="col-md-6">
                            <label>名称: </label>
                            <input class="form-control" value="${data.name}" id="name"/>
                        </div>
                        <div class="col-md-6">
                            <label>价格: </label>
                            <input class="form-control" value="${data.price}" id="price"
                                   onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label>描述: </label>
                        </div>
                        <div class="col-md-12">
                            <textarea class="form-control" id="description" rows="2">${data.description}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="dropdown">
                                <button id="state_text" class="btn btn-secondary dropdown-toggle" type="button"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${data.stateStr}
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a class="dropdown-item" onclick="Page.changeState('1')">上架</a>
                                    <a class="dropdown-item" onclick="Page.changeState('2')">下架</a>
                                </div>
                            </div>
                            <input value="${data.state}" type="hidden" id="state"/>
                        </div>
                        <div class="col-md-4">
                            <div class="dropdown">
                                <button id="type_text" class="btn btn-secondary dropdown-toggle" type="button"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${data.typeStr}
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <c:forEach items="${data.typeList}" var="type">
                                        <a class="dropdown-item"
                                           onclick="Page.changeType('${type.name}','${type.id}')">${type.name}</a>
                                    </c:forEach>
                                </div>
                            </div>
                            <input value="${data.type}" type="hidden" id="type"/>
                        </div>
                        <!--
                            1. 使用shop_fruits --- image.fruits.knxy.top
                            2. 点击选择后记录地址，显示本地图片
                            3. 管理员点击保存，上传图片到七牛云，保存到数据库。
                        -->
                        <div class="col-md-4">
                            <button type="submit" onclick="Page.changeImage()" class="btn btn-primary btn-md">
                                点击修改封面图片
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <img alt="商品图片" style="width: 250px;height: 250px" id="imageUrl" src="${imageHost}${data.imageUrl}">
                </div>
            </div>


            <!-- 暂时不做修改 -->
            <div class="detail">
                <div class="title">
                    <h5>商品 Banner 图片列表</h5><span>(限定尺寸：750*750像素)</span>
                </div>
                <div id="detail_header_imageList" class="row">
                </div>

                <div class="title">
                    <h5>商品详情图片列表</h5><span>(限定宽度：750像素)</span>
                </div>
                <div id="detail_content_imageList" class="row">
                </div>
            </div>

            <div>
                <input type="file" id="imageUrl_input" class="hide">
            </div>
            <div class="operation">
                <button type="submit" onclick="Page.save.click()" class="btn btn-success col-md-3">保存</button>
            </div>
        </div>
    </c:if>
</div>