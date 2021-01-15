<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新增角色</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/dist/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">

    $(function(){
        $("#fm").validate({
            rules:{
                roleName:{
                    required:true,
                    rangelength:[2,5],
                    remote: {
                        type: "post",
                        url: "<%=request.getContextPath()%>/auth/role/checkRoleName",
                        data:{
                            roleName: $("#roleName").val()
                        }
                    }
                },
            },
            messages:{
                roleName:{
                    required:"角色名必填",
                    rangelength:"角色名必须在2~5个字符之间",
                    remote:"角色名重复"

                },
            },
            submitHandler:function(fm){
                var index = layer.load(4, {shade:0.2});
                $.post(
                    "<%=request.getContextPath()%>/auth/role/add",
                    $("#fm").serialize(),
                    function(result){
                        layer.msg(
                            "奋力加载中", {
                                time: 2000 //2秒关闭（如果不配置，默认是3秒）
                            }, function(){
                                if(result.code == 200){
                                    layer.close(index);
                                    layer.msg(result.msg);
                                    parent.location.href="<%=request.getContextPath() %>/auth/role/toShow";
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
    .error{
        color:red;
        font-size:9px;
    }
</style>
<body>
<form id="fm">
    <table align="center" cellspacing="0" cellpadding="5">
        <tr align="center">
            <td><label for="roleName">角色名:</label></td>
            <td><input id="roleName" type="text" name="roleName"/></td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <br>
                <input class="submit" type="submit" value="提交">
            </td>
        </tr>
    </table>
</form>
</body>
</html>