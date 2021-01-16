<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改角色</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">
    function update(){
        $.post(
            "<%=request.getContextPath()%>/auth/role/update",
            $("#fm").serialize(),
            function(result){
                if(result.code == 200){
                    layer.msg(result.msg);
                    parent.location.href="<%=request.getContextPath() %>/auth/role/toShow";
                    return;
                }
                layer.msg(result.msg);
                return;
            }
        )
    }

</script>
<body>
<form id="fm">
    <input type="hidden" name="id" value="${role.id}">
    <table align="center" cellspacing="0" cellpadding="5">
        <tr align="center">
            <td><label for="roleName">角色名:</label></td>
            <td><input id="roleName" type="text" name="roleName" value="${role.roleName}"/></td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <br>
                <input type="button" value="提交" onclick="update()">
            </td>
        </tr>
    </table>
</form>
</body>
</html>