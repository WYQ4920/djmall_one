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
<script type="text/javascript">

    $(function(){
        show();
    })

    function show(){
        $.get(
            "<%=request.getContextPath() %>/role/show",
            {},
            function(result){
                let html = "";
                for (let i = 0; i < result.data.length; i++) {
                    let data = result.data[i];
                    html += "<tr>";
                    html += "<td>"+ data.id +"</td>";
                    html += "<td>"+ data.roleName +"</td>";
                    html += "<td>";
                    html += "<input type='button' onclick='resRole("+ data.id +")' value='关联资源'>";
                    html += "|";
                    html += "<input type='button' onclick='upd("+ data.id +")' value='编辑'>";
                    html += "|";
                    html += "<input type='button' onclick='remove("+ data.id +")' value='删除'>";
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }

    /* 修改 */
    function upd(id){
        layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/role/toUpdate?id=' + id,
        });
    }

    /* 删除 */
    function remove(id){
        $.post(
            "<%=request.getContextPath() %>/role/remove",
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
<table cellspacing="0" cellpadding="10" border="1px solid">
    <tr align="center">
        <td>id</td>
        <td>角色名</td>
        <td>操作</td>
    </tr>
    <tbody id="tb" align="center"></tbody>
</table>
</body>
</html>