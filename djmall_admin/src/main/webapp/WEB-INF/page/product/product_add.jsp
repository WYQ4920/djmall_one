<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>新增商品</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">

//    $(function (){
//        search();
//    })

    function search(){
        $.get(
            "<%=request.getContextPath() %>/product/listClassify",
            $("#fm").serialize(),
            function(result){
                var html = "";
                for (var i = 0; i < result.data.length; i++) {
                    var data = result.data[i];
                    html += "<tr>";
                    html += "<td>"+ data.attrName +"</td>";
                    html += "<td>";
                    // var  attrValue = data.attrValue.split(",");
                    // for (var j = 0; j < attrValue.length; j++) {
                    //     html += "<input type='checkbox' value='+ attrValue[j] +'>"+ attrValue[j];
                    // }
                    for (var a = 0; a < result.data[i].attrValueList.length; a++) {
                        var dta = result.data[i].attrValueList[a];
                        html += "<input type='checkbox' value='"+ dta.attrValue +"'>"+ dta.attrValue;
                    }
                    html += "</td>";
                    html += "</tr>";
                }
                $("#skuAttr").html(html);
            }
        )
    }

    //新增  +自定义SKU属性
    function addCustomAttr(){
        var content = "属性名:<input type='text' id='AttrName'/><br>";
        content += "属性值<input type='text' id='AttrValue'/>";
        content += "(多个值之间,分割)";
        layer.open({
            type: 1,
            content: content,
            title: '新增SKU',
            shadeClose: true,
            shade :0.2,
            area: ['50%', '80%'],
            btn: ['确认', '取消'],
            yes: function(index, layero){
                var html = "<tr>";
                html += "<td>" + $("#AttrName").val();
                html += "<input type='hidden' value='" + $("#AttrName").val() + "'/>";
                html += "</td>";
                html += "<td>";
                var values = $("#AttrValue").val().split(",");
                for(var i = 0; i< values.length; i++) {
                    html += "<input type='checkbox' value='" + values[i] + "'/>" + values[i];
                }
                html += "</td>";
                html += "</tr>";
                $("#skuAttr").append(html);
                layer.close(index);
            }
        })
    }

    //生成SKU
    function createSKU(){
        //获取所有属性
        var trs = $("#skuAttr tr");
        //声明新数组
        var attrValue = new Array;
        for (var i = 0; i < trs.length; i++){
            //js对象
            var tr = trs[i];
            //js对象转jquery对象
            var checkboxs = $(tr).find(":checked");
            if(checkboxs.length == 0){
                continue;
            }
            var attrValues = new Array();
            for(var j = 0; j < checkboxs.length; j++){
                attrValues.push(checkboxs[j].value);
            }
            attrValue.push(attrValues);
        }
        //笛卡尔积组合Sku集合
        var skuList = dkej(attrValue);
        var html = " ";
        for(var i = 0; i < skuList.length; i++){
            html += "<tr>";
            html += "<td>" + (i + 1) + "</td>";
            html += "<td>" + skuList[i] + "<input type='hidden' name= 'skuList[" + i +"].skuName' value='" + skuList[i] + "'/></td>";
            html += "<td><input type='text' name='skuList[" + i + "].skuCount' value='0' width ='10%'/></td>";
            html += "<td><input type='text' name='skuList[" + i + "].skuPrice' value='0' width ='10%'/></td>";
            html += "<td><input type='text' name='skuList[" + i + "].skuRate' value='0' width ='10%'/></td>";
            html += "<td><input type='button' onclick='removeSKU(this)' value='移除'></td>";
            html += "</tr>";
        }
        $("#skuList").html(html);
    }

    //移除SKU
    function removeSKU(obj){
        $(obj).parent().parent().remove();
    }

    function dkej(d) {// d = 二维数组
        var total = 1;
        for (var i = 0; i < d.length; i++) {
            total *= d[i].length;
        }
        var e = [];
        var itemLoopNum = 1;
        var loopPerItem = 1;
        var now = 1;
        for (var i = 0; i < d.length; i++) {
            now *= d[i].length;
            var index = 0;
            var currentSize = d[i].length;
            itemLoopNum = total / now;
            loopPerItem = total / (itemLoopNum * currentSize);
            var myIndex = 0;
            for (var j = 0; j < d[i].length; j++) {
                for (var z = 0; z < loopPerItem; z++) {
                    if (myIndex == d[i].length) {
                        myIndex = 0;
                    }
                    for (var k = 0; k < itemLoopNum; k++) {
                        e[index] = (e[index] == null ? "" : e[index] + ":") + d[i][myIndex];
                        index++;
                    }
                    myIndex++
                }
            }
        }
        return e;
    }

    /* 新增商品 */
    function addProduct(){
        var index = layer.load(2,{shade:0.4});
        var formData = new FormData($("#fm")[0]);
        $.ajax({
            url:"<%=request.getContextPath()%>/product/addProduct",
            dataType:'json',
            type:'post',
            data: formData,
            processData : false, // 使数据不做处理
            contentType : false, // 不要设置Content-Type请求头信息
            success:function (result){            	  			layer.msg(result.msg,{
                time:800
            },function(){
                if(result.code == 200){
                    location.reload();
                    layer.close(index);
                    return;
                }
                layer.close(index);
            });
            }
        })

    }

</script>
<body>
    <form id="fm">
        <input type="hidden" name="productGiveLike" value="0">
        <input type="hidden" name="productOrderCount" value="0">
        名称
            <input type="text" name="productName"><br>
        邮费
            <select name="productPostage">
                <c:forEach items="${freightList}" var="f">
                    <option>${f.dictCode}-${f.freight}</option>
                </c:forEach>
            </select><br>
        描述
            <textarea name="productDes"></textarea><br>
        图片
            <input type="file" name="img"><br>
        分类
            <select name="productType" onchange="search()">
                <option value="">请选择</option>
                <c:forEach items="${productList}" var="p">
                    <option value="${p.code}">${p.dictName}</option>
                </c:forEach>
            </select><br>
        SKU
            <input type="button" value="+" onclick="addCustomAttr()">
            <input type="button" value="生成SKU" onclick="createSKU()"><br>
        <%-- 分类联动展示 --%>
        <table cellspacing="0" cellpadding="10" border="1px solid">
            <tr align="center">
                <td>属性名</td>
                <td>属性值</td>
            </tr>
            <tbody id="skuAttr" align="center"></tbody>
        </table>
        <%-- 展示生成的sku --%>
        <table cellspacing="0" cellpadding="10" border="1px solid">
            <tr>
                <td>编号</td>
                <td>SKU属性</td>
                <td>库存</td>
                <td>价格(元)</td>
                <td>折扣(%)</td>
                <td>操作</td>
            </tr>
            <tbody id="skuList" align="center"></tbody>
        </table>
        <button type="button" onclick="addProduct()">新增</button>
    </form>
</body>
</html>
