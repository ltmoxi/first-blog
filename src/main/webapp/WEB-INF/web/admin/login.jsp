<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="../../../static/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <script type="text/javascript" src="../../../static/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin .checkbox {
            font-weight: normal;
        }

        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input[type="text"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

    </style>
</head>
<body>
<div class="container">

    <form id="login-form" class="form-signin">
        <h2 style="text-align: center;">登录博客</h2>
        <div style="height: 50px;">
        </div>
        <label for="inputUsername" class="sr-only">用户名</label>
        <input name="username" type="text" id="inputUsername" class="form-control" placeholder="username" required>
        <label for="inputPassword" class="sr-only">密码</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 在这台电脑上记住我
            </label>
        </div>
        <button id="login-btn" class="btn btn-lg btn-primary btn-block" type="button">登录</button>
        <button  type="button" class="btn btn-link"><a href="../reg.html">新用户？创建账户</a></button>
    </form>
</div>
</body>
<script>

    $("#login-btn").click(function () {

        //获取表单参数
        let data = $("#login-form").serialize();

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