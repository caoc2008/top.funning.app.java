<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-19
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="/admin/css/poster.css?version=${version}" rel="stylesheet"/>
<script src="/js/qiniu.min.js"></script>
<script src="/admin/js/poster.js?version=${version}"></script>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <input type="file" id="image_file">
    <div id="msg" class="msg lead">${msg}</div>
    <div class="modify">
        <button type="submit" onclick="Page.save.click()" class="btn btn-success col-md-2">保存文件为首页海报</button>
        <button type="submit" onclick="Page.delete.click()" class="btn btn-danger col-md-2">删除首页海报</button>
    </div>
    <hr/>
    <div class="content">
        <h3>当前首页海报</h3>
        <img id="postImage" src="${postImageUrl}" />
        <script>
            $(function () {
                let postImageUrl = "${postImageUrl}";
                if(postImageUrl){
                    $("#postImage").show();
                }else{
                    $("#postImage").hide();
                    $("h3").html("当前首页没有海报")
                }
            })

        </script>
    </div>
</main>
