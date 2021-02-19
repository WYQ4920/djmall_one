<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/dist/jquery.validate.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/static/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>


    <form id="fm">
        <input type="button" onclick="notPay()" value="待支付">
        <input type="button" onclick="isPay()" value="待收货">
        <input type="button" onclick="isOk()" value="已完成">
        <input type="button" onclick="isDel()" value="已取消"><br>

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



    </form>


</body>
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
                for (var i = 0; i < result.data.productDTOList.length; i++) {
                    var data = result.data.productDTOList[i];
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

    /*
      地区
     */
    function toArea(){
        layer.open({
            type : 2,
            title : '修改',
            shade : 0.5,
            area : [ '380px', '360px' ],
            content :'<%=request.getContextPath() %>/areas/toShow/',//iframe的url
        });

    }




</script>
<style>
    .error{
        color:red;
        font-size:10px;
    }
</style>
</html>