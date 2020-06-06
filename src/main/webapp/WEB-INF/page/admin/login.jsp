<%--
  Created by IntelliJ IDEA.
  User: Moses
  Date: 2020/06/03
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录页</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>

</head>

<body class="text-center">
<form id="login" class="form-signin">
    <h1 class="h3 mb-3 font-weight-normal">请登录</h1>
    <label for="inputUsername" class="sr-only">用户名</label>
    <input name="username" id="inputUsername" class="form-control" placeholder="用户名" required="" autofocus="">
    <label for="inputPassword" class="sr-only">密码</label>
    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="密码" required="">
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> 在这台电脑上记住我
        </label>
    </div>
    <button id="submit" class="btn btn-lg btn-primary btn-block" type="button">登陆</button>
</form>


</body>
<script>
    $("#submit").click(function () {

        //获取表单参数
        let data = $("#login").serialize();

        $.ajax({
            url: "${pageContext.request.contextPath}/admin/user_info/login.json",
            type: "POST",
            dataType: "json",
            data: data,
            success: function (result) {
                console.log(result);
                if (result.code === 2000) {
                    window.location.href = "${pageContext.request.contextPath}/admin/user_info/index.action";
                } else {
                    alert("登陆失败:" + result.message)
                }
            },
            error: function (result) {

            }
        });

    });
</script>
</html>
