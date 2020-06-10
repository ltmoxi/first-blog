<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>blog</title>

		<script src="../../static/js/jquery-3.5.1.min.js"></script>
		<link rel="stylesheet" href="../../static/font-awesome-4.7.0/css/font-awesome.css">
		<link rel="stylesheet" href="../../static/bootstrap-3.3.7-dist/css/bootstrap.css">
		<script src="../../static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
		<style>
			body {
				background: whitesmoke;
			}

			.add-shadow {
				box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.5);
			}

			.add-light-shadow {
				box-shadow: 0 1px 6px rgba(0, 0, 0, 0.5);
			}

			p {
				text-indent: 30px;
			}
		</style>
	</head>

	<body>
		<!--导航栏开始-->
		<div class="container">
			<c:import url="header.jsp"/>
		</div>
		<!--导航栏结束-->

		<div class="row clearfix">
			<div class="col-md-12 column" style="padding-top: 50px;padding-bottom: 50px">
				<h1 style="text-align: center">${article.title}</h1>
				<p style="text-align: center">${article.updateTime}</p>
			</div>
		</div>

		<div class="container add-light-shadow" >
			<!--文章列表开始-->
			<div class="row clearfix">

				<div class="col-md-12 column " >
					<div class="col-md-12 column " style="padding-top: 20px">
						<nav class="nav nav-tabs">
							<ul class="breadcrumb">
								<li><a href="${pageContext.request.contextPath}/">Home</a></li>
								<li><a href="${pageContext.request.contextPath}/portal/type.action?typeId=${article.typeId}">${article.name}</a></li>
								<li class="active">${article.title}</li>
							</ul>
						</nav>
					</div>
					<div class="row clearfix " style="height: 500px">

						<div class="col-md-1 column">
						</div>
						<div class="col-md-10 column ">

							<h2>${article.title}</h2>
							${article.content}
						</div>
						<div class="col-md-1 column">
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

	</body>

</html>