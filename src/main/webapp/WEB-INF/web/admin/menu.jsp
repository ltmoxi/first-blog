<%--
  Created by IntelliJ IDEA.
  User: Moses
  Date: 2020/06/09
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div>
    <h3>
        菜单
    </h3>
    <hr style="border-bottom: 1px;"/>
</div>
<!--手风琴切换开始-->
<div class="panel-group  " id="panel-112788">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-112788"
               href="#panel-element-486551">分类管理</a>
        </div>
        <div id="panel-element-486551" class="panel-collapse collapse">
            <div class="panel-body">
                <a href="${pageContext.request.contextPath}/admin/type_info/list_type.action">文章分类</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-112788"
               href="#panel-element-898383">文章管理</a>
        </div>
        <div id="panel-element-898383" class="panel-collapse collapse">
            <div class="panel-body">
                <a href="${pageContext.request.contextPath}/admin/article_info/list_normal.action">文章列表</a>
            </div>
            <div class="panel-body">
                <a href="${pageContext.request.contextPath}/admin/article_info/list_recycle.action">回收站</a>
            </div>
        </div>
    </div>
</div>
<!--手风琴切换结束-->