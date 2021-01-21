<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改页面</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/dist/jquery.validate.min.js"></script>
</head>
<script type="text/javascript">

    $(function () {
        $("#fm").validate({
            rules: {
                dictName: {
                    required: true,
                },
            },
            messages: {
                dictName:{
                    required:"字典名必填",
                }
            },
                submitHandler:function(fm){
                    var index = layer.load(4, {shade:0.2});
                    $.post(
                        "<%=request.getContextPath()%>/dict/update",
                        $("#fm").serialize(),
                        function(result){
                            layer.msg(result.msg, {
                                /* icon: 1, */
                                time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                            }, function(){
                                if(result.code == 200){
                                    layer.close(index);
                                    parent.location.href = "<%=request.getContextPath()%>/dict/toShow";
                                    return;
                                }
                                layer.close(index);
                                layer.msg(result.msg);
                                return;
                            });
                        });
                }
            });
    })


</script>
<style>
    .error {
        color: red;
        font-size: 10px;
    }
</style>
<body>
<form id="fm">

    <input type="hidden" name="code" id="code"  value="${dictDTO.code}">
    <input type="hidden" name="parentCode" value="${dictDTO.parentCode}">

    <input type="text"  disabled value="${dictDTO.code}"><br>
    <input type="text" disabled value="${dictDTO.parentCode}"><br>

    <label for="dictName">字典数据名</label>
    <input type="text" name="dictName" id="dictName" value="${dictDTO.dictName}"><br>
    <br>

    <input type="submit" value="提交修改">
</form>
</body>
</html>