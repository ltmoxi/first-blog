<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>文章编辑</title>
</head>

<body>
<div class="wrap">
    <!-- 头部 -->
    <div id="header">
        <c:import url="../header.jsp"></c:import>
    </div>
    <!-- 左侧菜单和主体内容 -->
    <div class="grid-1-7" style="flex: 1;margin:0;">
        <!-- 左侧菜单 -->
        <c:import url="../menu.jsp"></c:import>
        <!-- 面包屑导航和主体内容 -->
        <div class="content">
            <!--面包屑导航-->
            <div class="content-header">
                <div class="breadcrumb">
                    <span>文章管理</span>
                    <span class="divider">/</span>
                    <span class="active">文章编辑</span>
                </div>
            </div>
            <!--全部主体内容-->
            <div class="list-content">
                <!--块元素-->
                <div class="block">
                    <!--页面有多个表格时，可以用于标识表格-->
                    <h2>文章编辑</h2>
                    <!--正文内容-->
                    <div class="main">
                        <!--表单-->
                        <form id="form" action="" method="">
                            <input type="hidden" name="id" value="${id}"/>
                            <!--输入框-->
                            <div class="unit">
                                <div class="left">
                                    <p class="subtitle">标题</p>
                                </div>
                                <div class="right">
                                    <input type="text" class="text" name="title" data-type="必填" placeholder="请输入文章标题"
                                           value="${article.title}"/>
                                </div>
                                <!--清浮动-->
                                <span class="clearfix"></span>
                            </div>
                            <!--分割线-->
                            <p class="divider"></p>

                            <!--下拉选择框-->
                            <div class="unit">
                                <div class="left">
                                    <p class="subtitle">所属分类</p>
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
                                <!--清浮动-->
                                <span class="clearfix"></span>
                            </div>
                            <!--分割线-->
                            <p class="divider"></p>

                            <!--文章封面-->
                            <div class="unit">
                                <div class="left">
                                    <p class="subtitle">文章封面</p>
                                </div>
                                <div class="right">
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
                                <!--清浮动-->
                                <span class="clearfix"></span>
                            </div>
                            <!--分割线-->
                            <p class="divider"></p>

                            <!--文本域-->
                            <div class="unit">
                                <div class="left">
                                    <p class="subtitle">内容</p>
                                </div>
                                <div class="right">
                                    <div id="edit" class="edit-container"></div>

                                    <input type="hidden" id="content" name="content" value=""/>
                                    <input type="hidden" id="summary" name="summary" value=""/>
                                </div>
                                <!--清浮动-->
                                <span class="clearfix"></span>
                            </div>
                            <!--分割线-->
                            <p class="divider"></p>

                            <!--提交按钮-->
                            <div class="unit">
                                <div style="margin-left: 200px;">
                                    <!--表单提交时，必须是input元素，并指定type类型为button，否则ajax提交时，会返回error回调函数-->
                                    <input type="button" id="return" class="button no" value="返回"/>
                                    <input type="button" id="submit" class="button yes" value="保存"/>
                                </div>
                                <!--清浮动-->
                                <span class="clearfix"></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    //下拉选择框
    javaex.select({
        id: "type_id",
        isSearch: false
    });

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
                $("#container img").attr("src", "../" + imgUrl);
                $("#cover").val("../" + imgUrl);
            } else {
                javaex.optTip({
                    content: "error",
                    type: "error"
                });
            }
        }
    });

    //富文本编辑器
    javaex.edit({
        id: "edit",
        content: '${article.content}',
        callback: function (result) {
            $("#content").val(result.html);
            $("#summary").val(result.text);

        }
    });


    //返回
    $("#return").click(function () {
        history.back();
    });

    //提交
    $("#submit").click(function () {
        console.log($("#form").serialize())
        $.ajax({
            url: "save.json",
            type: "POST",
            dataType: "json",
            data: $("#form").serialize(),
            success: function (result) {
                if (result.code === 2000) {
                    javaex.optTip({
                        content: "成功!"
                    });
                    // 建议延迟加载
                    setTimeout(function () {
                        // 跳转页面
                        window.location.href = "${pageContext.request.contextPath}/article_info/list_normal.action";
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

</script>

</html>