<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>关于我页面</title>
    <style>
        .t {
            font-size: 100px;
            text-align: center;
            color: orangered
        }
    </style>
</head>
<body style="background-color: #ccc;">

<p class="t">${id}</p>
<p class="t">${data.username}</p>
<p class="t">${data.password}</p>
<p></p>
</body>
</html>
