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

    /*let pageNo = 1;*/
    $(function(){
        show();
    })

    function show(){
        $.get(
            "<%=request.getContextPath() %>/auth/role/show",
            $("#fm").serialize(),
            function(result){
                let html = "";
                /*let pageHtml = "";*/
                for (let i = 0; i < result.data.length; i++) {
                    let data = result.data[i];
                    html += "<tr>";
                    html += "<td>"+ data.id +"</td>";
                    html += "<td>"+ data.roleName +"</td>";
                    html += "<td>";
                    html += "<input type='button' value='关联资源' onclick='resRole("+ data.id +")' style='color: cornflowerblue;border: white;background-color: white'>";
                    html += "<span style='color: cornflowerblue'>|</span>";
                    html += "<input type='button' value='编辑' onclick='upd("+ data.id +")' style='color: cornflowerblue;border: white;background-color: white'>";
                    html += "<span style='color: cornflowerblue'>|</span>";
                    html += "<input type='button' value='删除' onclick='remove("+ data.id +")' style='color: cornflowerblue;border: white;background-color: white'>";
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
               /* pageHtml += "<input type='button' onclick='page(true,null)' value='上一页'>";
                pageHtml += "<input type='button' onclick='page(false,"+result.data.pages+")' value='下一页'>";
                $("#pageDiv").html(pageHtml);*/
            }
        )
    }

    /* 分页 */
    /*function page(isShow, pages){
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
    }*/

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
    function remove(id){
        $.post(
            "<%=request.getContextPath() %>/auth/role/remove",
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
        show();
   }

</script>
<body>
<br>
<form id="fm">
    角色名：<input type="text" name="roleName"/>
    <input type="button" value="查询" onclick="check()"/>&nbsp;
    <input type="button" value="新增" onclick="add()"/>
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
<%--<div id="pageDiv"></div>--%>
</body>
</html>