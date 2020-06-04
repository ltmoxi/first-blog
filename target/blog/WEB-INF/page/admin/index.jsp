<%--
  Created by IntelliJ IDEA.
  User: Moses
  Date: 2020/06/02
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>无标题文档</title>
</head>

<body>
<div class="wrap">
    <!--头部-->
    <div id="header">
<%--        用来导入其他jsp文件,把这jsp里的内容放到这个div--%>
        <c:import url="header.jsp"/>
    </div>

    <!-- 左侧菜单和主体内容 -->
    <div class="grid-1-7" style="flex: 1;margin:0;">
        <c:import url="menu.jsp"/>
    </div>
</div>
</body>
</html>