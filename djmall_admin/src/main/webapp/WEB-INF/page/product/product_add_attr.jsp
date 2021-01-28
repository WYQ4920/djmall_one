<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增商品属性</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">

    /* 新增商品属性 */
    function del(id){
        $.post(
            "<%=request.getContextPath() %>/product/addAttr",
            $("#fm").serialize(),
            function(result){
                if (200 != result.code){
                    layer.msg(result.msg);
                    return;
                }
                parent.location.href="<%=request.getContextPath() %>/product/toList";
            }
        )
    }

</script>
<body>
    <form name="fm">
        属性名:
            <input type="text" name="attrName">
        属性值:
            <input type="text" name="attrValue">
        <button type="button" onclick="add()">新增</button>
    </form>
</body>
</html>
