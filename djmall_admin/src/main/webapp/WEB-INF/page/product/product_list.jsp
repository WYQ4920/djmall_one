<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示商品表</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">

    /* 新增 */
    function add(){
        layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/product/toAdd',
        });
        <%--location.href="<%=request.getContextPath() %>/product/toAdd";--%>
    }

</script>
<body>
    <button type="button" onclick="add()">新增</button>
</body>
</html>
