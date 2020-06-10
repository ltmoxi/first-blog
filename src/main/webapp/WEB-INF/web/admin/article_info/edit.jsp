<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>文章列表</title>
    <link rel="stylesheet" type="text/css" href="../../../../static/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../../../../static/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <script type="text/javascript" src="../../../../static/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../../../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="../../../../static/js/wangEditor.min.js"></script>

    <!--jquery，当前版本不可更改jquery版本-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/lib/jquery-1.7.2.min.js"></script>
    <!--核心组件-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/js/javaex.js"></script>
    <style>
        body {
            background: whitesmoke;
        }
    </style>
</head>

<body>

<div class="container">
    <!--导航栏开始-->
    <div class="row clearfix">
        <div class="col-md-9 column " style="padding-top: 10px;">
            <h2>后&nbsp;&nbsp;&nbsp;&nbsp;台&nbsp;&nbsp;&nbsp;&nbsp;管&nbsp;&nbsp;&nbsp;&nbsp;理</h2>
        </div>
        <div class="col-md-3 column" style="padding-top: 30px;">
            <ul class="breadcrumb">
                <li>
                    欢迎您，<%=session.getAttribute("username")%>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/user_info/login_out.action">退出</a>
                </li>
                <li class="active">
                    <a href="${pageContext.request.contextPath}/">首页</a>
                </li>
            </ul>
        </div>
    </div>
    <!--导航栏结束-->
    <hr style="border-bottom: 1px;"/>

    <div class="row clearfix">
        <div class="col-md-3 column">
            <c:import url="../menu.jsp"/>
        </div>
        <div class="col-md-9 column" style="padding-top: 16px;">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <input type="hidden" id="id" name="id" value="${article.cover}"/>
                    <div class="form-group">
                        <label for="title">标题</label><input type="text" class="form-control" id="title" value="${article.title}" />
                    </div>
                    <div class="form-group">
                        <!--下拉选择框-->
                        <div class="unit">
                            <div class="left">
                                <label for="title">所属分类</label>
                            </div>
                            <div class="right">
                                <select id="type_id" name="typeId">
                                    <c:forEach items="${typeList}" var="typeInfo" varStatus="status">
                                        <option value="${typeInfo.id}"
                                                <c:if test="${article.typeId == typeInfo.id}">selected</c:if>>${typeInfo.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>封面</label>
                        <br>
                        <!--上传按钮-->
                        <a href="javascript:;">
                            <!-- 图片承载容器 -->
                            <label id="container" for="upload"
                                   style="display: inline-block; width:132px;height:74px;">
                                <c:choose>
                                    <c:when test="${empty article.cover}">
                                        <img src="${pageContext.request.contextPath}/static/javaex/pc/images/default.png"
                                             width="100%" height="100%"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${article.cover}" width="100%" height="100%"/>
                                    </c:otherwise>
                                </c:choose>
                            </label>
                            <input type="file" class="hide" id="upload"
                                   accept="image/gif, image/jpeg, image/jpg, image/png"/>
                        </a>
                        <input type="hidden" id="cover" name="cover" value="${article.cover}"/>
                    </div>
                    <div class="form-group">
                        <label>正文</label><br>
                        <div id="editor">
                            ${article.content}
                        </div>
                    </div>
                    <button id="save" type="button" class="btn btn-default">保存</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    //图片上传
    javaex.upload({
        type: "image",
        url: "upload.json",	    // 请求路径
        id: "upload",	        // <input type="file" />的id
        param: "file",			// 参数名称，SSM中与MultipartFile的参数名保持一致
        dataType: "url",		// 返回的数据类型：base64 或 url
        callback: function (rtn) {
            console.log(rtn);
            // 后台返回的数据
            if (rtn.code === 2000) {
                var imgUrl = rtn.data;
                $("#container img").attr("src", "../../" + imgUrl);
                $("#cover").val("../../" + imgUrl);
            } else {
                javaex.optTip({
                    content: "error",
                    type: "error"
                });
            }
        }
    });



    var E = window.wangEditor
    var editor = new E('#editor')
    editor.create()

    document.getElementById('save').addEventListener('click', function () {
        let id = $("#id").val();
        let content = editor.txt.html();
        let title = $("#title").val();
        let summary = editor.txt.text();
        let cover = $("#cover").val();
        let typeId = $("#type_id").val();
        let data = "id=" + id + "&content=" + content + "&cover=" + cover+"&typeId="+typeId+"&summary="+summary+"&title="+title;
        console.log(data);

        $("#save").click(function () {
            console.log($("#form").serialize())
            $.ajax({
                url: "save.json",
                type: "POST",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.code === 2000) {
                        alert("成功!")
                        // 建议延迟加载
                        setTimeout(function () {
                            // 跳转页面
                            window.location.href = "${pageContext.request.contextPath}/admin/article_info/list_normal.action";
                        }, 2000);
                    } else {
                        javaex.optTip({
                            content: "失败!",
                            type: "error"
                        });
                    }
                }
            })
        })

    }, false);


</script>
</body>

</html>