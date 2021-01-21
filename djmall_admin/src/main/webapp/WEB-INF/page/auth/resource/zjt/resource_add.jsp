<%--
  Created by IntelliJ IDEA.
  User: zjt
  Date: 2021/1/17
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资源新增</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.all.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
<form id="fm">
<input type="hidden" name="parentId" value="${parentId}">
<input type="text" value="${parentResourceName}" disabled="disabled"/><br>
    资源名称:<input type="text" name="ResourceName"/><br>
    URL:<input type="text" name="url"/><br>
    资源编码:<input type="text" name="ResourceCode"/><br>
    资源类型:<select name="resourceType">
              <option value="1">菜单</option>
              <option value="2">button</option>
            </select>
    <button type="button" onclick="add()" >提交</button>
</form>
</body>
<script type="text/javascript">
    function add(){
        var index = layer.load(2,{shade:0.4});
        $.post(
            "<%=request.getContextPath() %>/zjt/add",
            $("#fm").serialize(),
            function(result) {
                layer.msg(result.msg, {
                    /* icon: 1, */
                    time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                }, function () {
                    //do something
                    if (result.code == "200") {
                        layer.close(index);
                        parent.location.href = "<%=request.getContextPath() %>/zjt/toZtreeShow";
                        return;
                    }
                    layer.close(index);
                })
            })
    }

</script>
<style>
    .error{
        color:red;
        font-size:10px;
    }
</style>
</html>
