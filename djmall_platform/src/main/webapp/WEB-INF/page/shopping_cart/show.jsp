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
    购物车
       <%-- <table width="500" hight="300" cellspacing="0" cellpadding="10" border="1px">
            <tr  align="center">
                <td>订单号</td>
                <td>1</td>
                <td>2</td>
                <td>3</td>
            </tr>
            <tbody id="tb"></tbody>
        </table>--%>

    </form>

</body>
<script type="text/javascript">

    <%--$(function(){--%>
    <%--    show()--%>
    <%--});--%>

    <%--function show(){--%>
    <%--    $.post(--%>
    <%--        "<%=request.getContextPath()%>/area/show",--%>
    <%--        {"parentCode":$("#parentCode").val()},--%>
    <%--        function(result){--%>
    <%--            if(result.code != 200){--%>
    <%--                layer.msg(result.msg);--%>
    <%--                return;--%>
    <%--            }--%>
    <%--            let html = "";--%>
    <%--            var pageHtml = "";--%>
    <%--            for (var i = 0; i < result.data.length; i++) {--%>
    <%--                html+="<tr align='center'>";--%>
    <%--                html+="<td>"+result.data[i].code+"</td>";--%>
    <%--                html+="<td>"+result.data[i].parentCode+"</td>";--%>
    <%--                html+="<td>"+result.data[i].dictName+"</td>";--%>
    <%--                html+="<td>";--%>
    <%--                html+="<input type='button' value='修改' name='checkedCode' onclick='upd1(\""+result.data[i].code+"\")'>";--%>
    <%--                html+="</td>";--%>
    <%--                html+="</tr>";--%>
    <%--            }--%>
    <%--            $("#tb").html(html);--%>

    <%--        }--%>
    <%--    )--%>

    <%--}--%>

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