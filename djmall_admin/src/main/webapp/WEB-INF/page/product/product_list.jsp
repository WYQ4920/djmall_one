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

    var pageNo = 1;
    $(function (){
       search(pageNo);
   })

    function search(pageNo){
        $.get(
            "<%=request.getContextPath() %>/product/list?pageNo=" + pageNo,
            $("#fm").serialize(),
            function(result){
                var html = "";
                var pageHtml = "";
                for (var i = 0; i < result.data.records.length; i++) {
                    var data = result.data.records[i];
                    html += "<tr>";
                    html += "<td><input type='checkbox' name='id' value='"+ data.id +"'></td>";
                    html += "<td>"+ data.productName +"</td>";
                    html += "<td>"+ data.productType +"</td>";
                    html += "<td>"+ data.productStatus +"</td>";
                    html += "<td>"+ data.productPostage +"</td>";
                    html += "<td><img src='http://qnue446o0.hn-bkt.clouddn.com/"+ data.productImg +"'></td>";
                    html += "<td>"+ data.productDes +"</td>";
                    html += "<td>"+ data.productGiveLike +"</td>";
                    html += "<td>"+ data.productOrderCount +"</td>";
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

    //模糊查
    function check(){
        search(1);
    }

</script>
<body>
<br>
<form id="fm">
    &nbsp&nbsp&nbsp&nbsp名称
        <input type="text" name="productName"/><br>
    &nbsp&nbsp&nbsp&nbsp分类
        <c:forEach items="${productList}" var="p">
            &nbsp&nbsp&nbsp&nbsp<input type="checkbox" name="productType" value="${p.code}">${p.dictName}
        </c:forEach>
    <button type="button" onclick="check()" class="btn btn-default btn-sm" style="margin:5px 20px">搜索</button><br><br>
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
<div id="pageDiv"></div>
</body>
</html>
