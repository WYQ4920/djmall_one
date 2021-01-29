<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>展示页面</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/layui/css/layui.css" media="all">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/dist/jquery.validate.min.js"></script>

</head>
<script type="text/javascript">

    var pageNum = 1;
    $(function () {
        show();
    })

    /*用户展示*/
    function show() {
        $.post(
            "<%=request.getContextPath() %>/user/list",
            $("#fm").serialize(),
            function (result) {
                if (result.code != 200) {
                    layer.msg(result.msg);
                    return;
                }
                let html = "";
                var pageHtml = "";
                for (var i = 0; i < result.data.length; i++) {
                    html += "<tr>";
                    html += "<td>";
                    html += "<input type='checkbox' name='ids' value='" + result.data[i].id + "'/>";
                    /*html += "<input type='hidden' id='"+result.data[i].id+"' value='"+result.data[i].userStatus+"' />";*/
                    html += "</td>";
                    html += "<td>" + result.data[i].id + "</td>";
                    html += "<td>" + result.data[i].userName + "</td>";
                    html += "<td>" + result.data[i].nickName + "</td>";
                    html += "<td>" + result.data[i].sexShow + "</td>";
                    html += "<td>" + result.data[i].userPhone + "</td>";
                    html += "<td>" + result.data[i].userEmail + "</td>";
                    /*html+="<input type='button' value='修改' onclick='upd("+result.data[i].id+")'>";
                    html+="<input type='button' value='授予角色' onclick='giveRole("+result.data[i].id+")'>";*/
                    /*html+="<td>";
                    html+="<input type='button' value='删除' onclick='del("+result.data[i].id+")'/>";
                    html+="</td>";*/
                    html += "<td>" + result.data[i].roleName + "</td>";
                    html += "<td>" + result.data[i].statusShow + "</td>";
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
                /*pageHtml += "<input type='button' onclick='page(true,null)' value='上一页'>";
                pageHtml += "<input type='button' onclick='page(false,"+result.data.pages+")' value='下一页'>";
                $("#pageDiv").html(pageHtml);*/
            }
        )

    }

    /*function page(page,pages){
        if (page){
            if (pageNum==1){
                layer.msg("首页");
                return;
            }
            pageNum--;
        }else {
            if (pageNum>=pages){
                layer.msg("尾页");
                return;
            }
            pageNum++;
        }
        show(pageNum);
    }*/

    /*用户修改*/
    function upd() {
        var chk_value = [];
        $('input:checkbox[name="ids"]:checked').each(function () { //遍历，将所有选中的值放到数组中
            chk_value.push($(this).val());
        });
        if (chk_value.length < 1) {
            layer.msg("至少勾选一项");
            return;
        }
        if (chk_value.length > 1) {
            layer.msg("只能勾选一项");
            return;
        }
        layer.open({
            type: 2,
            title: '修改',
            shade: 0.5,
            area: ['380px', '360px'],
            content: '<%=request.getContextPath() %>/user/toUpdate/' + chk_value[0] //iframe的url
        });

    }

    /*用户删除*/
    function del() {
        var chk_value = [];
        $('input:checkbox[name="ids"]:checked').each(function () { //遍历，将所有选中的值放到数组中
            chk_value.push($(this).val());
        });
        if (chk_value.length < 1) {
            layer.msg("至少勾选一项");
            return;
        }
        $.post(
            "<%=request.getContextPath() %>/user/del",
            {"ids": chk_value},
            function (result) {
                if (result.code != 200) {
                    layer.msg(result.msg);
                    return;
                }
                show();
            })
    }

    /*查询*/
    function query() {
        /*show(pageNum);*/
        show();
    }

    /*授予角色*/
    function giveRole() {
        var chk_value = [];
        $('input:checkbox[name="ids"]:checked').each(function () { //遍历，将所有选中的值放到数组中
            chk_value.push($(this).val());
        });
        if (chk_value.length < 1) {
            layer.msg("至少勾选一项");
            return;
        }
        if (chk_value.length > 1) {
            layer.msg("只能勾选一项");
            return;
        }
        location.href = "<%=request.getContextPath() %>/user/toGiveRole?userId=" + chk_value[0];
    }

    /*重置密码*/
    function resetPwd() {
        var chk_value = [];
        $('input:checkbox[name="ids"]:checked').each(function () { //遍历，将所有选中的值放到数组中
            chk_value.push($(this).val());
        });
        if (chk_value.length < 1) {
            layer.msg("至少勾选一项");
            return;
        }
        if (chk_value.length > 1) {
            layer.msg("只能勾选一项");
            return;
        }
        $.post(
            "<%=request.getContextPath() %>/user/resetPwd",
            {"id": chk_value[0]},
            function (result) {
                if (result.code != 200) {
                    layer.msg(result.msg);
                    return;
                }
                show();
            })
    }

</script>
<style>

</style>

<body>
<form id="fm">
    用户名：<input type="text" name="userName" autocomplete="off" placeholder="请输入用户名">
    <br>
    <%--<i class="layui-icon layui-icon-search" style="font-size: 20px; color: #1E9FFF;"></i>--%>
    性 别：
    <c:forEach items="${userSexMap}" var="s">
        <input type="radio" name="userSex" value="${s.key}">${s.value}
    </c:forEach><br>

    级别：
    <c:forEach items="${roleList}" var="role">
        <input type="radio" name="roleId" id="roleId" value="${role.id}">${role.roleName}
    </c:forEach>

    <br>
    用户状态：
    <select name="userStatus">
        <option value="">--请选择--</option>
        <c:forEach items="${userStatusMap}" var="st">
            <option value="${st.key}">${st.value}</option>
        </c:forEach>
    </select>

    <shrio:hasPermission name="USER_MANAGER">
        <input type="button" value="查询" onclick="query()" class="layui-btn layui-btn-normal layui-btn-xs">
    </shrio:hasPermission>
    <br>
    <div class="layui-btn-group" style="margin-left: 20px;margin-top: 10px">
        <shrio:hasPermission name="USER_UPDATE_BTN">
            <input type="button" value="修改" class="layui-btn layui-btn-normal layui-btn-sm" onclick="upd()">
        </shrio:hasPermission>

        <shrio:hasPermission name="USER_DEL_BTN">
            <input type="button" value="删除" class="layui-btn layui-btn-normal layui-btn-sm" onclick="del()">
        </shrio:hasPermission>

        <shrio:hasPermission name="USER_ADD_ROLE_BTN">
            <input type="button" value="授权" class="layui-btn layui-btn-normal layui-btn-sm" onclick="giveRole()">
        </shrio:hasPermission>

        <shrio:hasPermission name="USER_RESET_PWD_BTN">
        <input type="button" value="重置密码" class="layui-btn layui-btn-normal layui-btn-sm" onclick="resetPwd()">
        <shrio:hasPermission name="USER_RESET_PWD_BTN">
    </div>
    <table class="layui-table" style="margin: 20px;width: auto;child-align: auto" cellspacing="0" cellpadding="10">
        <tr align="center">
            <td></td>
            <td>用户ID</td>
            <td>用户名</td>
            <td>昵称</td>
            <td>性别</td>
            <td>手机号</td>
            <td>邮箱</td>
            <td>角色名</td>
            <td>用户状态</td>
        </tr>
        <tbody id="tb"></tbody>
    </table>
    <div id="pageDiv"></div>
</form>

</body>
</html>