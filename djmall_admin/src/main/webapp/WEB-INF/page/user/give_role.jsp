<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>展示角色</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<style type="text/css">
    a {text-decoration: none}
</style>
<script type="text/javascript">

    function giveRole(){
        $.post(
            "<%=request.getContextPath() %>/user/giveRole",
            $("#fm").serialize(),
            function (result){
                if(result.code == 200){
                    layer.msg("授予角色成功");
                    location.href="<%=request.getContextPath()%>/user/toShow";
                    return;
                }
                layer.msg(result.msg);
            })
    }


</script>
<body>
<br>
<form id="fm">
    <input type="hidden" name="userId" value="${userId}">
    <shrio:hasPermission name="USER_ADD_ROLE_BTN">
        <input type="button" value="确定" onclick="giveRole()"/>&nbsp;
    </shrio:hasPermission>
    <br>
    <table cellspacing="0" cellpadding="10" border="1px solid">
        <tr align="center">
            <td>编号</td>
            <td>角色名</td>
        </tr>
        <c:forEach items="${roleList}" var="role" >
            <tr>
                <td><input type="radio" name="roleId" value="${role.id}" <c:if test="${roleId==role.id}">checked</c:if> >${role.id}</td>
                <td>${role.roleName}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>