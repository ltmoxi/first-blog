<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>文章列表</title>
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
                    <span class="active">文章列表</span>
                </div>
            </div>
            <!--全部主体内容-->
            <div class="list-content">
                <!--块元素-->
                <div class="block">
                    <!--页面有多个表格时，可以用于标识表格-->
                    <h2>文章列表</h2>
                    <!--正文内容-->
                    <div class="main-20">
                        <div style="text-align:right;margin-bottom:10px;">
                            <!-- 文章分类 -->
                            <select id="type_id" class="no-shadow">
                                <option value="">请选择</option>
                                <c:forEach items="${typeList}" var="typeInfo" varStatus="status" >
                                    <option value="${typeInfo.id}" <c:if test="${typeInfo.typeId==typeId}">selected</c:if>>${typeInfo.name}</option>
                                </c:forEach>
                            </select>
                            <!-- 日期范围 -->
                            <input type="text" id="date2" class="date" style="width: 300px;" value="" readonly/>
                            <!-- 标题检索 -->
                            <input type="text" class="text" id="title" value="${keyWord}" placeholder="检索文章标题" />
                            <!-- 点击查询按钮 -->
                            <button class="button blue" style="margin-top: -3px;" onclick="search()"><span class="icon-search"></span></button>
                        </div>

                        <!--表格上方的操作元素，添加、删除、搜索等-->
                        <div class="operation-wrap">
                            <div class="buttons-wrap">
                                <a href="edit.action">
                                    <button id="add" class="button blue"><span class="icon-plus"></span> 添加</button>
                                </a>
                            </div>
                        </div>
                        <table id="table" class="table">
                            <thead>
                            <tr>
                                <th style="width:20px;"><input type="checkbox" class="fill listen-1"/> </th>
                                <th style="width:60px;">序号</th>
                                <th>分类</th>
                                <th>文章标题</th>
                                <th>撰写日期</th>
                                <th>阅读量</th>
                                <th>编辑</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${fn:length(list)==0}">
                                    <tr>
                                        <td colspan="7" style="text-align:center;">暂无记录</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${list}" var="entity" varStatus="status" >
                                        <tr>
                                            <td><input type="checkbox" class="fill listen-1-2" name="id" value="${entity.id}" /> </td>
                                            <td>${status.index+1}</td>
                                            <td>${entity.typeName}</td>
                                            <td>${entity.title}</td>
                                            <td>${entity.updateTime}</td>
                                            <td>${entity.viewCount}</td>
                                            <td>
                                                <a href="edit.action?id=${entity.id}">
                                                    <button class="button wathet"><span class="icon-edit"></span> 编辑</button>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>


</html>