<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>djmall商城</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js.cookie.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/token.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/dist/jquery.validate.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/static/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header header-demo" summer>
        <div class="layui-main">
            <div class="layui-nav">
                <li class="layui-nav-item"><a  href="">首页</a></li>
                <li class="layui-nav-item"><a id="login" href="javascript:toLogin()">登录</a></li>
                <li class="layui-nav-item"><a   href="javascript:toAdd()">注册</a></li>
                <li class="layui-nav-item"><a i href="">购物车</a></li>
            </div>
        </div>
    </div>
</div>




</body>

<style>

</style>
</html>
<script type="text/javascript">
    $(function (){
        //是否登录
        if(check_login()){
            $("#login").html(Cookies.get("NICK_NAME"));
            $("#login").attr("href", "<%=request.getContextPath()%>/index/toIndex?TOKEN=" + getToken());
        }
    })

    //去登陆
    function toLogin(){
        layer.open({
            type:2,
            content:"<%=request.getContextPath()%>/user/toLogin",
            title:"登录",
            shade:0.6,
            area:['300px','300px']
        });
    }

    //去注册
    function toAdd(){
        layer.open({
            type:2,
            content:"<%=request.getContextPath()%>/user/toAdd",
            title:"登录",
            shade:0.6,
            area:['300px','300px']
        });
    }

    function getSalt(){
        /*alert(userNPE);*/
        $.post(
            "<%=request.getContextPath() %>/user/getSalt",
            {userNPE:userNPE},
            function (result){
                if (result.code == 200){
                    $("#salt").val(result.data);
                    return;
                }
                layer.msg(result.msg);
            }
        )
    }

    //判断当前窗口路径与加载路径是否一致。
    if(window.top.document.URL != document.URL){
        //将窗口路径与加载路径同步
        window.top.location = document.URL;
    }

</script>