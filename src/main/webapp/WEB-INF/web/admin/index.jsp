<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8"/>
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../../../static/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <script type="text/javascript" src="../../../static/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
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
            <c:import url="menu.jsp"/>
        </div>
        <!--<div class="col-md-9 column" style="padding-top: 12px;">
            <div class="row clearfix">
                <div class="col-md-12 column">
                </div>
            </div>
        </div>-->
    </div>
</div>
</body>

</html>


