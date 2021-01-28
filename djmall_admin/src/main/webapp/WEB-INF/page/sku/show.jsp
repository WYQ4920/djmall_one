<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
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

        <table width="500" hight="300" cellspacing="0" cellpadding="10" border="1px">
            <tr  align="center">
                <td>编号</td>
                <td>商品类型</td>
                <td>属性名</td>
                <td>操作</td>
            </tr>
            <tbody id="tb"></tbody>
        </table>

    </form>


</body>
<script type="text/javascript">

    $(function(){
        show()
    });

    function show(){
        $.post(
            "<%=request.getContextPath()%>/sku/show",
            {"productType":"PRODUCT_TYPE"},
            function(result){
                if(result.code != 200){
                    layer.msg(result.msg);
                    return;
                }
                let html = "";
                var pageHtml = "";
                for (var i = 0; i < result.data.length; i++) {
                    html+="<tr align='center'>";
                    html+="<td>"+i+"</td>";
                    html+="<td>"+result.data[i].dictName+"</td>";
                    html+="<td>"+result.data[i].attrName+"</td>";
                    html+="<td>";
                    html+="<input type='button' value='关联属性' name='checkedCode' onclick='upd1(\""+result.data[i].productType+"\")'>";
                    html+="</td>";
                    html+="</tr>";
                }
                $("#tb").html(html);
                /*pageHtml += "<input type='button' onclick='page(true,null)' value='上一页'>";
                pageHtml += "<input type='button' onclick='page(false,"+result.data.pages+")' value='下一页'>";
                $("#pageDiv").html(pageHtml);*/
            }
        )

    }


    /*
    修改
     */
    function upd1(code){
        layer.open({
            type : 2,
            title : '修改',
            shade : 0.5,
            area : [ '380px', '360px' ],
            content :'<%=request.getContextPath() %>/sku/toUpdate/'+code,//iframe的url
        });

    }


</script>
<style>
</style>
</html>