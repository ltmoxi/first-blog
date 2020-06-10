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
        <c:import url="../header.jsp"></c:import>
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
                <p style="text-align: center;font-size: 80px">500</p>
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
            <div>
            </div>
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