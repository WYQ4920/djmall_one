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

    let pageNo = 1;
    $(function(){
        show(pageNo);
    })

    function show(){
        $.get(
            "<%=request.getContextPath() %>/role/show",
            $("#fm").serialize(),
            function(result){
                let html = "";
                let pageHtml = "";
                for (let i = 0; i < result.data.length; i++) {
                    let data = result.data[i];
                    html += "<tr>";
                    html += "<td>"+ data.id +"</td>";
                    html += "<td>"+ data.roleName +"</td>";
                    html += "<td>";
                    html += "<a href='' onclick='resRole("+ data.id +")'>关联资源|</a>";
                    html += "<a href='' onclick='upd("+ data.id +")'>编辑|</a>";
                    html += "<a href='' onclick='remove("+ data.id +")'>删除</a>";
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
                pageHtml += "<input type='button' onclick='page(true,null)' value='上一页'>";
                pageHtml += "<input type='button' onclick='page(false,"+result.data.pages+")' value='下一页'>";
                $("#pageDiv").html(pageHtml);
            }
        )
    }

    /* 分页 */
    function page(isShow, pages){
        if(isShow){
            if(pageNo <= 1){
                layer.msg("首页");
                return;
            }
            pageNo--;
            layer.msg("第"+pageNo+"页");
        }else{
            if(pageNo >= pages){
                layer.msg("尾页");
                return;
            }
            pageNo++;
            layer.msg("第"+pageNo+"页");
        }
        show(pageNo);
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
                show(pageNo);
            }
        )
    }

    function check(){
        show(pageNo);
    }

</script>
<body>
<form id="fm">
    角色名：<input type="text" name="roleName"/>
    <input type="button" value="查询" onclick="check()"/>
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
<div id="pageDiv"></div>
</body>
</html>