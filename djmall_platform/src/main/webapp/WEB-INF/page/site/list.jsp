<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>收货地址</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/layui/css/layui.css" media="all">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">

    $(function(){
        search()
    });

    function search(){
        $.get(
            "<%=request.getContextPath() %>/site/list",
            $("#fm").serialize(),
            function(result){
                var html = "";
                for (var i = 0; i < result.data.length; i++) {
                    var data = result.data[i];
                    html += "<tr>";
                    html += "<td>"+ data.userName +"</td>";
                    html += "<td>"+ data.userPhone +"</td>";
                    html += "<td>"+ data.sheng +"</td>";
                    html += "<td>"+ data.shi +"</td>";
                    html += "<td>"+ data.qu +"</td>";
                    html += "<td>"+ data.details +"</td>";
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

    <button type="button" onclick="toAdd()" class="btn btn-default" style="margin:5px 20px">新增</button>

</form>
<table cellspacing="0" cellpadding="10" border="1px solid" class="layui-table" style="margin: 20px;width: auto;child-align: auto" >
    <tr align="center">

        <td>收货人</td>
        <td>手机号</td>
        <td>省</td>
        <td>市</td>
        <td>区</td>
        <td>详细地址</td>
    </tr>
    <tbody id="tb" align="center"></tbody>
</table>
<div id="pageDiv"></div>
</body>
</html>
