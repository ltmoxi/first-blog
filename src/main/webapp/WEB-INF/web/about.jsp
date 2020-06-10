<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>blog</title>
		<link rel="stylesheet" type="text/css" href="../../static/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="../../static/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script type="text/javascript" src="../../static/js/jquery-3.5.1.min.js"></script>
		<script type="text/javascript" src="../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<style>
			body {
				background: whitesmoke;
			}
			
			.add-shadow {
				box-shadow: 0 2px 15px rgba(0, 0, 0, 0.5);
			}
			
		</style>
	</head>

	<body>
		<!--导航栏开始-->
		<div class="container">
			<c:import url="header.jsp"></c:import>
		</div>
		<!--导航栏结束-->

		<div class="row clearfix">
			<div class="col-md-12 column" style="height: 120px;">
				<h2 style="text-align: center;">关于我</h2>
			</div>
		</div>

		<div id="content" class="container ">
			<!--文章列表开始-->
			<div class="row clearfix">
	   			<img src="../../static/img/background.jpg" style="width: 1170px;"/>
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
