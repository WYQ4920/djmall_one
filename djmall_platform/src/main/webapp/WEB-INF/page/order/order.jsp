<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <input type="button" onclick="notPay()" value="待支付">
    <input type="button" onclick="isPay()" value="待收货">
    <input type="button" onclick="isOk()" value="已完成">
    <input type="button" onclick="isDelete()" value="已取消"><br>



    <form id="fm">
       <input type="hidden" id="orderStatus" value="PAY_WAIT">

        <table id="ta" cellspacing="0" cellpadding="10" border="1px solid" class="layui-table" style="margin: 20px;width: auto;child-align: auto" >
<%--            <tbody id='tb' align='center'></tbody>--%>
        </table>

        <div id="pageDiv"></div>

    </form>


</body>
<script type="text/javascript">

    var pageNo = 1;
    $(function (){
        search(pageNo);
    })

    //待支付
    function notPay(){
        $("#orderStatus").val("PAY_WAIT");
        var html = "";
        html += "<tr>";
        html += "<td>订单号</td>";
        html += "<td>订单总金额</td>";
        html += "<td>总运费</td>";
        html += "<td>总购买数量</td>";
        html += "<td>支付方式</td>";
        html += "<td>创建时间</td>";
        html += "<td>订单状态</td>";
        html += "</tr>";
        // html += "<tbody id='tb'></tbody>";
        $("#ta").html(html);
        search(pageNo);
    }

    //已取消
    function isDelete(){
        $("#orderStatus").val("CANCEL");
        var html = "";
        html += "<tr>";
        html += "<td>订单号</td>";
        html += "<td>订单总金额</td>";
        html += "<td>总运费</td>";
        html += "<td>总购买数量</td>";
        html += "<td>支付方式</td>";
        html += "<td>创建时间</td>";
        html += "<td>订单状态</td>";
        html += "</tr>";
        // html += "<tbody id='tb' align='center'></tbody>";
        $("#ta").html(html);
        search(pageNo);
    }

    //已支付（已发货，代发货）
    function isPay(){
        $("#orderStatus").val("SEND_WAIT,SEND_ING");
        var html = "";
        html += "<tr>";
        html += "<td>订单号</td>";
        html += "<td>商品信息</td>";
        html += "<td>购买数量</td>";
        html += "<td>折扣</td>";
        html += "<td>付款金额</td>";
        html += "<td>支付方式</td>";
        html += "<td>邮费</td>";
        html += "<td>下单时间</td>";
        html += "<td>付款时间</td>";
        html += "<td>订单状态</td>";
        html += "</tr>";
        // html += "<tbody id='tb' align='center'></tbody>";
        $("#ta").html(html);
        search1(pageNo);
    }

    //已完成
    function isOk(){
        $("#orderStatus").val("COMPLETE");
        var html = "";
        html += "<tr>";
        html += "<td>订单号</td>";
        html += "<td>商品信息</td>";
        html += "<td>购买数量</td>";
        html += "<td>折扣</td>";
        html += "<td>付款金额</td>";
        html += "<td>支付方式</td>";
        html += "<td>邮费</td>";
        html += "<td>下单时间</td>";
        html += "<td>付款时间</td>";
        html += "<td>订单状态</td>";
        html += "</tr>";
        // html += "<tbody id='tb' align='center'></tbody>";
        $("#ta").html(html);
        search1(pageNo);
    }

    //父级订单展示（默认待支付）
    function search(pageNo){
        $.post(
            "<%=request.getContextPath() %>/order/show?pageNo=" + pageNo,
            {"orderStatus":$("#orderStatus").val()},
            function(result){
                var html= "";
                var pageHtml = "";
                for (var i = 0; i < result.data.records.length; i++) {
                    var data = result.data.records[i];
                    html += "<tr>";
                    html += "<td>"+ data.orderNo +"</td>";
                    html += "<td>"+ data.totalPayMoney +"</td>";
                    html += "<td>"+ data.totalFreight +"</td>";
                    html += "<td>"+ data.totalBuyCount +"</td>";
                    html += "<td>"+ data.payTypeShow +"</td>";
                    html += "<td>"+ data.createTime +"</td>";
                    html += "<td>"+ data.orderStatusShow ;
                    if(data.orderStatus == 'PAY_WAIT' ){
                        html += "<br><a href=>去支付</a><br><a href=>取消订单</a></td>"
                            }
                    if(data.orderStatus == 'CANCEL' ){
                        html += "<a href=>再次购买</a></td>"
                    }
                    html += "</tr>";

                }
                $("#ta").append(html);
                pageHtml += "<input type='button' onclick='page(true,null)' value='上一页'>";
                pageHtml += "<input type='button' onclick='page(false," + result.data.pages + ")' value='下一页'>";
                $("#pageDiv").html(pageHtml);
            }
        )
    }



    //子级订单展示
    function search1(pageNo){
        $.post(
            "<%=request.getContextPath() %>/order_info/show?pageNo=" + pageNo,
            {"orderStatus":$("#orderStatus").val()},
            function(result){
                var html = "";
                var pageHtml = "";
                for (var i = 0; i < result.data.records.length; i++) {
                    var data = result.data.records[i];
                    html += "<tr>";
                    html += "<td>"+ data.orderNo +"</td>";
                    html += "<td>"+ data.skuInfo +"</td>";
                    html += "<td>"+ data.totalBuyCount +"</td>";
                    html += "<td>"+ data.skuRate +"</td>";
                    html += "<td>"+ data.totalPayMoney +"</td>";
                    html += "<td>"+ data.payTypeShow +"</td>";
                    html += "<td>"+ data.productPostage +"</td>";
                    html += "<td>"+ data.createTime +"</td>";
                    html += "<td>"+ data.payTime +"</td>";
                    html += "<td>"+ data.orderStatusShow ;
                    if(data.orderStatus == 'SEND_ING'){
                        html += "<br><a href=>确认收货</a></td>"
                    }
                    if(data.orderStatus == 'SEND_WAIT' ){
                        html += "<a href=>提醒发货</a></td>"
                    }
                    if(data.orderStatus == 'COMPLETE' ){
                        html += "<a href=>再次购买</a><br><a href=>评价晒单</a></td>"
                    }
                    html += "</tr>";

                }
                $("#ta").append(html);
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