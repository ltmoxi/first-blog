<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>blog</title>
    <link rel="stylesheet" type="text/css" href="../../static/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="../../static/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <script type="text/javascript" src="../../static/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <style>
        body {
            background: whitesmoke;
        }

        .add-shadow {
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.5);
        }
        .add-light-shadow {
            box-shadow: 0 1px 6px rgba(0, 0, 0, 0.5);
        }

        .content-style {
            padding-top: 25px;
            overflow: hidden
        }
    </style>

</head>

<body>
<!--导航栏开始-->
<div class="container">
    <div id="nav" class="row clearfix">
        <c:import url="header.jsp"></c:import>
    </div>
</div>
<!--导航栏结束-->

<div class="row clearfix">
    <div class="col-md-12 column" style="height: 120px;">
        <h2 style="text-align: center;">xxx的个人博客</h2>
    </div>
</div>

<div class="container add-light-shadow">
    <!--文章列表开始-->
    <div class="row clearfix" style="padding-top: 30px">

        <div class="row clearfix">
            <div class="col-md-1 column"></div>
            <div id="content" class="col-md-10 column">

            </div>
            <div class="col-md-1 column"></div>
        </div>
    </div>
    <!--文章列表结束-->

    <!--底部开始-->
    <div id="footer" class="row clearfix" style="background: #323437; height: 60px; color: white;">
        <div class="col-md-2 column " style="height: 60px;text-align: center;line-height: 60px;">
            <a href="${pageContext.request.contextPath}/admin/user_info/login.action" style=" color: white;text-decoration: none;">后台管理</a>
        </div>

        <div class="col-md-8 column">
            <script>
                $("#content").empty();
                let url = "${pageContext.request.contextPath}/portal/type.json?pageNum=${pageNum}";
                $.ajax({
                    "url": url,
                    "type": "GET",
                    "dataType": "json",
                    "success": function (result) {
                        console.log(result);
                        if (result.code === 2000) {
                            let articleList = result.data.list;
                            //添加每个文章
                            for (let i = 0; i < articleList.length; i++) {
                                let template = ' <div class="well well-lg add-shadow row clearfix">\n' +
                                    '                    <div class="col-md-5 column content-style">\n' +
                                    '                        <img src="@{cover}" alt="" width="326">\n' +
                                    '                    </div>\n' +
                                    '                    <div class="col-md-7 column">\n' +
                                    '                        <h2><a href="${pageContext.request.contextPath}/portal/article.action?id=@{id}" style=" color: #000000;">@{title}</a></h2>\n' +
                                    '                        <p>@{summary}<p>\n' +
                                    '                        <p><a class="btn" href="${pageContext.request.contextPath}/portal/article.action?id=@{id}">阅读更多 »</a></p>\n' +
                                    '                    </div>\n' +
                                    '                </div>';
                                let html = template.replace('@{cover}', articleList[i].cover)
                                    .replace('@{cover}', articleList[i].cover)
                                    .replace('@{title}', articleList[i].title)
                                    .replace('@{summary}', articleList[i].summary)
                                    .replace('@{id}', articleList[i].id)
                                    .replace('@{id}', articleList[i].id)
                                $("#content").append(html);
                            }
                            //添加分页
                            let pageInfo = result.data;
                            let template = '        <ul class="pager">\n' +
                                '                    <li><a href="${pageContext.request.contextPath}/portal/index.action?pageNum=1">首页</a></li>\n' +
                                '                    <li><a href="${pageContext.request.contextPath}/portal/index.action?pageNum=@{prePageNum}">上一页</a></li>\n'+
                                '                    <li><a href="${pageContext.request.contextPath}/portal/index.action?pageNum=@{pageNum}">@{pageNum}</a></li>\n' +
                                '                    <li><a href="${pageContext.request.contextPath}/portal/index.action?pageNum=@{afterPageNum}">下一页</a></li>\n' +
                                '                    <li><a href="${pageContext.request.contextPath}/portal/index.action?pageNum=99">尾页</a></li>\n' +
                                '                </ul>';
                            let html = template.replace('@{prePageNum}', pageInfo.pageNum-1)
                                .replace('@{pageNum}', pageInfo.pageNum)
                                .replace('@{pageNum}', pageInfo.pageNum)
                                .replace('@{afterPageNum}', pageInfo.pageNum+1)
                            $("#content").append(html);
                        } else {
                            console.log("获取数据失败" + result.message)
                        }
                    }
                });

            </script>
        </div>
        <div class="col-md-2 column" style="height: 60px;text-align: center;line-height: 60px;">
            <a href="${pageContext.request.contextPath}/portal/group.action" style=" color: white;text-decoration: none;">个人博客后台系统小组</a>

        </div>
    </div>
    <!--底部结束-->
</div>
</div>
</body>

</html>