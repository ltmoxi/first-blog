<%--
  Created by IntelliJ IDEA.
  User: Moses
  Date: 2020/06/09
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="col-md-12 column">
    <nav class="navbar navbar-inverse" role="navigation">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span>
                <span class="icon-bar"></span><span class="icon-bar"></span></button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">我的博客</a>
        </div>

        <div class="collapse navbar-collapse add-shadow" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="${pageContext.request.contextPath}/">首页</a>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">文章分类<strong
                            class="caret"></strong></a>
                    <ul id="articleClassification" class="dropdown-menu">

                    </ul>
                </li>

                <li><a href="${pageContext.request.contextPath}/portal/about.action">关于我</a></li>
            </ul>

            <!--  搜索框开始-->
            <form class="navbar-form navbar-right" role="search">
                <div>
                    <input id="search-value" type="text" class="form-control" placeholder="搜索.."/>
                    <button id="search-btn" type="button" class="form-control"><i class="fa fa-search "></i></button>
                </div>
            </form>
            <!--  搜索框结束-->
        </div>
    </nav>
</div>

<script>
    //搜索按钮
    $("#search-btn").click(function () {
        let keyword = $("#search-value").val();
        if (keyword !== "") {
            window.location.href = "${pageContext.request.contextPath}/portal/search.action?keyWord=" + keyword;
        }
    })

    // 页面一加载，就向后台请求文章分类的数据
    $(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/portal/get_type.json",
            type: "POST",
            dataType: "json",
            data: {},
            success: function (result) {
                let typeList = result.data;
                let html = '';
                for (var i = 0; i < typeList.length; i++) {
                    html += '<li><a href="${pageContext.request.contextPath}/portal/type.action?typeId=' + typeList[i].id + '">' + typeList[i].name + '</a></li>';
                }
                $("#articleClassification").append(html);
            }
        });
    });
</script>
