<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>djmall商城</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/layui/css/layui.css" media="all">
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
</div><br><br><br><br><br>

<form id="fm">
    &nbsp&nbsp&nbsp&nbsp名称查询：
    <input type="text" name="productName"/><br>
    &nbsp&nbsp&nbsp&nbsp分类：
    <c:forEach items="${productList}" var="p">
        &nbsp&nbsp&nbsp&nbsp<input type="checkbox" name="productType" value="${p.code}">${p.dictName}
    </c:forEach>
    <button type="button" onclick="check()" class="btn btn-default btn-sm" style="margin:5px 20px">搜索</button><br><br>


<table cellspacing="0" cellpadding="10" border="1px solid" class="layui-table" style="margin: 20px;width: auto;child-align: auto" >
    <tr align="center">
        <td>商品名</td>
        <td>类型</td>
        <td>邮费</td>
        <td>商品图片</td>
        <td>描述</td>
        <td>点赞量</td>
        <td>订单量</td>
        <td>了解更多</td>
    </tr>
    <tbody id="tb" align="center"></tbody>
</table>
<div id="pageDiv"></div>
</form>



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


    //判断当前窗口路径与加载路径是否一致。
    if(window.top.document.URL != document.URL){
        //将窗口路径与加载路径同步
        window.top.location = document.URL;
    }

    //商品展示
    var pageNo = 1;
    $(function (){
        search(pageNo);
    })

    function search(pageNo){
        $.get(
            "<%=request.getContextPath() %>/djmall_platform/list?pageNo=" + pageNo,
            $("#fm").serialize(),
            function(result){
                var html = "";
                var pageHtml = "";
                for (var i = 0; i < result.data.productDTOList.length; i++) {
                    var data = result.data.productDTOList[i];
                    html += "<tr>";
                    html += "<td>"+ data.productName +"</td>";
                    html += "<td>"+ data.productType +"</td>";
                    html += "<td>"+ data.productPostage +"</td>";
                    html += "<td><img src='http://qnue446o0.hn-bkt.clouddn.com/"+ data.productImg +"'height='100px' width='100px'></td>";
                    html += "<td>"+ data.productDes +"</td>";
                    html += "<td>"+ data.productGiveLike +"</td>";
                    html += "<td>"+ data.productOrderCount +"</td>";
                    html += "<td><input type='button' name='id' value='查看详情' onclick='toBuy("+ data.id +")'></td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
                pageHtml += "<input type='button' onclick='page(true,null)' value='上一页'>";
                pageHtml += "<input type='button' onclick='page(false," + result.data.pages + ")' value='下一页'>";
                $("#pageDiv").html(pageHtml);
            }
        )
    }

    /* 分页 */
    function page(page, pages){
        if (page){
            if (pageNo == 1){
                layer.msg("首页");
                return;
            }
            pageNo--;
        }else {
            if (pageNo >= pages){
                layer.msg("尾页");
                return;
            }
            pageNo++;
        }
        show(pageNo);
    }

    //模糊查
    function check(){
        search(1);
    }

</script>