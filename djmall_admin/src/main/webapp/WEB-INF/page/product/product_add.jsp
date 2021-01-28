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
                    html += "<td>"+ data.attrName +"</td>";
                    html += "<td>";
                    for (var a = 0; a < result.data[i].attrValueList.length; a++) {
                        var dta = result.data[i].attrValueList[a];
                        html += "<input type='checkbox' value='+ dta.attrId +'>"+ dta.attrValue;
                    }
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }

    /* 新增商品属性 */
    function add(){
        layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/product/toAddAttr',
        });
    }

</script>
<body>
    <form id="fm">
        名称:
            <input type="text" name="">
        邮费:
            <select>
                <option>顺丰-包邮</option>
                <option>韵达-15</option>
            </select>
        描述:
            <textarea></textarea>
        图片:
            <input type="file" name="file">
        分类:
            <select name="productType" onchange="search()">
                <option value="">请选择</option>
                <c:forEach items="${list}" var="p">
                    <option value="${p.code}">${p.dictName}</option>
                </c:forEach>
            </select>
        SKU
            <input type="button" value="+">
            <input type="button" value="生成SKU">
        <button type="button" onclick="add()">新增</button>
    </form>

    <table cellspacing="0" cellpadding="10" border="1px solid">
        <tr align="center">
            <td>属性名</td>
            <td>属性值</td>
        </tr>
        <tbody id="tb" align="center"></tbody>
    </table>
</body>
</html>
