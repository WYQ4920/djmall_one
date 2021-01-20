<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

    $(function(){
        show();
    })

    function show(){
        $.get(
            "<%=request.getContextPath() %>/auth/role/show",
            $("#fm").serialize(),
            function(result){
                let html = "";
                for (let i = 0; i < result.data.length; i++) {
                    let data = result.data[i];
                    html += "<tr>";
                    html += "<td>"+ data.id +"</td>";
                    html += "<td>"+ data.roleName +"</td>";
                    html += "<td>";
                    html +="<shrio:hasPermission name='ROLE_ADD_RESOURCE_BTN'>"
                    html += "<input type='button' value='关联资源' onclick='toResRelZtree("+ data.id +")' style='color: cornflowerblue;border: white;background-color: white'>";
                    html +="</shrio:hasPermission>"
                    html += "<span style='color: cornflowerblue'>|</span>";
                    html += "<shrio:hasPermission name='ROLE_UPDATE_BTN'>"
                    html += "<input type='button' value='编辑' onclick='upd("+ data.id +")' style='color: cornflowerblue;border: white;background-color: white'>";
                    html +="</shrio:hasPermission>"
                    html += "<span style='color: cornflowerblue'>|</span>";
                    html +="<shrio:hasPermission name='ROLE_DEL_BTN'>"
                    html += "<input type='button' value='删除' onclick='del("+ data.id +")' style='color: cornflowerblue;border: white;background-color: white'>";
                    html +="</shrio:hasPermission>"
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }
    /* 根据角色名模糊查 */
    function check(){
        show();
    }

    /* 关联资源 */
    function toResRelZtree(id){
        layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/auth/role/toResRelZtree?id=' + id,
        });
    }

    /* 新增 */
    function add(){
        layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/auth/role/toAdd',
        });
    }

    /* 修改 */
    function upd(id){
        layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/auth/role/toUpdate?id=' + id,
        });
    }

    /* 删除 */
    function del(id){
        $.post(
            "<%=request.getContextPath() %>/auth/role/del",
            {"id":id},
            function(result){
                if (200 != result.code){
                    layer.msg(result.msg);
                    return;
                }
                show();
            }
        )
    }

</script>
<body>
<br>
<form id="fm">
    角色名：<input type="text" name="roleName"/>
    <input type="button" value="查询" onclick="check()"/>&nbsp;
    <shrio:hasPermission name="ROLE_ADD_BTN">
    <input type="button" value="新增" onclick="add()"/>
    </shrio:hasPermission>
</form>
<br>
<table cellspacing="0" cellpadding="10" border="1px solid">
    <tr align="center">
        <td>编号</td>
        <td>角色名</td>
        <td>操作</td>
    </tr>
    <tbody id="tb" align="center"></tbody>
</table>
</body>
</html>