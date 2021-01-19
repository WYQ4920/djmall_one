<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
                    html += "<td>";
                    html += "<input type='radio' name='roleId' value='"+data.id+"'>"+ data.id +"</td>";
                    html += "<td>"+ data.roleName +"</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }

    function giveRole(){
        $.post(
            "<%=request.getContextPath() %>/user/giveRole",
            $("#fm").serialize(),
            function (result){
                if(result.code == 200){
                    layer.msg("授予角色成功");
                    show();
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
    <input type="button" value="确定" onclick="giveRole()"/>&nbsp;
    <br>
    <table cellspacing="0" cellpadding="10" border="1px solid">
        <tr align="center">
            <td>编号</td>
            <td>角色名</td>
        </tr>
        <tbody id="tb" align="center"></tbody>
    </table>
</form>
</body>
</html>