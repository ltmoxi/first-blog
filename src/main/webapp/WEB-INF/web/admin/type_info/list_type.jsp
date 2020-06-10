<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>文章分类</title>
    <link rel="stylesheet" type="text/css" href="../../../../static/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <script type="text/javascript" src="../../../../static/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../../../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
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
                    <button id="add" type="button" class="btn btn-primary">添加</button>
                    <hr style="border-bottom: 1px;"/>
                    <!--表格-->
                    <div class="col-md-12 column">
                        <form action="" id="form-table">
                            <table id="table" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>显示排序</th>
                                    <th>名称</th>
                                    <th>保存</th>
                                    <th>删除</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${typeInfo}" var="entity" varStatus="status">
                                    <tr data-id="${entity.id}">
                                        <td>${entity.id}</td>
                                        <td><input type="text" name="sort" value="${entity.sort}"/></td>
                                        <td><input type="text" name="name" value="${entity.name}"/></td>
                                        <td>
                                            <button type="button" data-type="save"
                                                    class="btn btn-success btn-group-justified">
                                                保存
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button" data-type="remove"
                                                    class="btn btn-danger btn-group-justified">删除
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    //添加按钮
    $("#add").click(function () {
        let html = '<tr data-id="">\n' +
            '          <th></th>\n' +
            '          <th><input type="text" name="sort" value="" /></th>\n' +
            '          <th><input type="text" name="name" value="" /></th>\n' +
            '          <th>\n' +
            '              <button type="button" data-type="save" class="btn btn-success btn-group-justified">\n' +
            '                  保存\n' +
            '              </button>\n' +
            '          </th>\n' +
            '          <th>\n' +
            '              <button type="button" data-type="remove" class="btn btn-danger btn-group-justified">删除</button>\n' +
            '          </th>\n' +
            '        </tr>';
        $("#table tbody").append(html);
    });

    $(document).ready(function () {
        $("#form-table").on("click", function (e) {
            const $that = $(e.target);
            if (e.target.tagName.toLowerCase() === "button") {
                switch ($that.data("type")) {
                    case "save" : //保存
                        save(e.target);
                        break;
                    case "remove" : //删除
                        remove(e.target);
                        break;
                }
                ;
            }
            ;
        });

        //保存
        function save(el) {
            //获取父元素tr的所有input
            let data = {};
            const $tr = $(el).parents('tr');
            const $input = $tr.find("input");
            data["id"] = $tr.data("id");

            $input.each(function (index, element) {
                const that = $(this);
                data[that.attr("name")] = that.val();
            });

            data = 'id=' + data.id + '&sort=' + data.sort + '&name=' + data.name;
            console.log(data);

            $.ajax({
                url: "${pageContext.request.contextPath}/admin/type_info/save.json",
                type: "POST",
                dataType: "json",
                data: data,
                success: function (result) {
                    console.log(result);
                    if (result.code === 2000) {

                        window.location.href = "${pageContext.request.contextPath}/admin/type_info/list_type.action";
                    } else {
                        alert("登陆失败:" + result.message)
                    }
                },
                error: function (result) {

                }
            })
        };

        //删除
        function remove(el) {
            //获取父元素tr的所有input
            let data = {};
            const $tr = $(el).parents('tr');
            const $input = $tr.find("input");
            data["id"] = $tr.data("id");

            $input.each(function (index, element) {
                const that = $(this);
                data[that.attr("name")] = that.val();
            });

            data = 'id=' + data.id;

            $.ajax({
                url: "${pageContext.request.contextPath}/admin/type_info/delete.json",
                type: "POST",
                dataType: "json",
                data: data,
                success: function (result) {
                    console.log(result);
                    if (result.code === 2000) {

                        window.location.href = "${pageContext.request.contextPath}/admin/type_info/list_type.action";
                    } else {
                        alert("登陆失败:" + result.message)
                    }
                },
                error: function (result) {

                }
            })

        }


    });

</script>
</body>

</html>