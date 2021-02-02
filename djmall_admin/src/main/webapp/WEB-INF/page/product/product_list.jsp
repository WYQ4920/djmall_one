<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>展示商品表</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/layui/css/layui.css" media="all">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">

    $(function (){
       search();
   })

    function search(){
        $.get(
            "<%=request.getContextPath() %>/product/list",
            $("#fm").serialize(),
            function(result){
                var html = "";
                for (var i = 0; i < result.data.length; i++) {
                    var data = result.data[i];
                    html += "<tr>";
                    html += "<td><input type='checkbox' name='id' value='"+ data.id +"'></td>";
                    html += "<td>"+ data.productName +"</td>";
                    html += "<td>"+ data.productType +"</td>";
                    html += "<td>"+ data.productStatus +"</td>";
                    html += "<td>"+ data.productPostage +"</td>";
                    html += "<td>"+ data.productImg +"</td>";
                    html += "<td>"+ data.productDes +"</td>";
                    html += "<td>"+ data.productGiveLike +"</td>";
                    html += "<td>"+ data.productOrderCount +"</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }

    /* 去新增 */
    function toAdd(){
       /* layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/product/toAdd',
        });*/
        location.href="<%=request.getContextPath() %>/product/toAdd";
    }

    /* 去修改 */
    function toUpdate(){
        var id;
        $("input[name='id']:checked").each(function(i){
            id = $(this).val();
        });
        layer.open({
           type: 2,
           shade: 0.2,
           area:["360px","360px"],
           content: '<%=request.getContextPath() %>/product/toUpdate?id=' + id,
        });
    }

</script>
<body>
<br>
<form id="fm">
    &nbsp&nbsp&nbsp&nbsp名称
        <input type="text" name="productName"/><br>
    &nbsp&nbsp&nbsp&nbsp分类
        <c:forEach items="${productList}" var="p">
            &nbsp&nbsp&nbsp&nbsp<input type="checkbox" name="productType" value="${p.productType}">${p.productType}
        </c:forEach>
    <button type="button" onclick="search()" class="btn btn-default btn-sm" style="margin:5px 20px">搜索</button><br><br>
    <button type="button" onclick="toAdd()" class="btn btn-default" style="margin:5px 20px">新增</button>
    <button type="button" onclick="toUpdate()" class="btn btn-default" style="margin:5px">修改</button>
    <button type="button" onclick="upOrDown()" class="btn btn-default" style="margin:5px">上架/下架</button>
    <button type="button" onclick="queryComment()" class="btn btn-default" style="margin:5px">查看评论</button>
    <button type="button" onclick="loadTemplate()" class="btn btn-default" style="margin:5px">下载导入模板</button>
    <button type="button" onclick="toLead()" class="btn btn-default" style="margin:5px">导入</button>
</form>
<table cellspacing="0" cellpadding="10" border="1px solid" class="layui-table" style="margin: 20px;width: auto;child-align: auto" >
    <tr align="center">
        <td><input type="checkbox"></td>
        <td>名称</td>
        <td>类型</td>
        <td>状态</td>
        <td>邮费</td>
        <td>商品图片</td>
        <td>描述</td>
        <td>点赞量</td>
        <td>订单量</td>
    </tr>
    <tbody id="tb" align="center"></tbody>
</table>
</body>
</html>
