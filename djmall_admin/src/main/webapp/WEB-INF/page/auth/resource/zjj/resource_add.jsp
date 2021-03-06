<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/dist/jquery.validate.js"></script>
    <title>新增资源</title>
</head>
<style>
    .error{
        color:red;
        font-size:10px;
    }
</style>
<body>
<form id="fm">
    <input type="hidden" name="parentId" value="${parentId}">
    上级名称：<input type="text" value="${resourceName}" disabled><br>
    <label for="resourceName">资 源 名：</label>
    <input type="text" name="resourceName" id="resourceName"><br>
    <label for="resourceCode">资源编码：</label>
    <input type="text" name="resourceCode" id="resourceCode"><br>
    <label for="url">路  径：</label>
    <input type="text" name="url" id="url"><br>
    <label for="resourceType">资源类型：</label>
    <select name="resourceType" id="resourceType">
        <option value="1">菜单</option>
        <option value="2">按钮</option>
    </select><br>
    <input type="submit" onclick="add()" value="新增资源">
</form>
</body>
<script type="text/javascript">
    $(function(){
        $("#fm").validate({
            rules:{
                resourceName:{
                    required: true,
                    remote: {
                        type: "post",
                        url: "<%=request.getContextPath()%>/res/checkResourceName",
                        data:{
                            resourceName: function() {
                                return $("#resourceName").val();
                            }
                        }
                    }
                },
                url:{
                    required: true,
                },
                resourceCode:{
                    required: true,
                },
            },
            messages:{
                resourceName: {
                    required: "请输入资源名",
                    remote:"资源名重复"
                },
                url: {
                    required: "请输入路径",
                },
                resourceCode: {
                    required: "请输入编码",
                }
            },
            submitHandler:function(){
                var index = layer.load(2,{shade:0.4});
                $.post(
                    "<%=request.getContextPath() %>/zjj/resource/add",
                    $("#fm").serialize(),
                    function(result){
                        layer.msg(result.msg, {
                            /* icon: 1, */
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            //do something
                            if(result.code == "200"){
                                layer.close(index);
                                parent.location.href="<%=request.getContextPath() %>/zjj/resource/toShow";
                                return;
                            }
                            layer.close(index);
                        })
                    });
            }
        });
    })

</script>

</html>