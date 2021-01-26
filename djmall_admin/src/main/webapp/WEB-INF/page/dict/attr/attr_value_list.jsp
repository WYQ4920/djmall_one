<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>展示商品属性值表</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">

    $(function(){
        search();
    })

    function search(){
        $.get(
            "<%=request.getContextPath() %>/attr/listValue",
            {"id":'${id}'},
            function(result){
                let html = "";
                for (let i = 0; i < result.data.length; i++) {
                    let data = result.data[i];
                    html += "<tr>";
                    html += "<td>"+ data.attrValueList.id +"</td>";
                    html += "<td>"+ data.attrValueList.attrValue +"</td>";
                    html += "<td>";
                    html += "<shrio:hasPermission name='RELEVANCE_ATTRIBUTE_VALUE_REMOVE_BTN'>"
                    html += "<input type='button' value='移除' onclick='remove("+ data.attrValueList.id +")' style='color: cornflowerblue;border: white;background-color: white'>";
                    html +="</shrio:hasPermission>"
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }

    /* 新增 */
    function add(){
        $.post(
            "<%=request.getContextPath() %>/attr/addValue",
            $("#fm").serialize(),
            function(result){
                if (200 == result.code){
                    layer.msg(result.msg);
                    window.location.reload();
                    return;
                }
                layer.msg(result.msg);
                return;
            }
        )
    }

    /* 移除 */
    function remove(id){
        $.post(
            "<%=request.getContextPath() %>/auth/removeValue",
            {"id":id},
            function(result){
                if (200 != result.code){
                    layer.msg(result.msg);
                    return;
                }
                search();
            }
        )
    }

</script>
<body>
<shrio:hasPermission name="RELEVANCE_ATTRIBUTE_VALUE_ADD_BTN">
    <form id="fm">
        属性名:
            <input name="attrName" value="" disabled="disabled"><br>
        已关联属性值  属性值:
            <input name="attrValue">
        <button type="button" onclick="add()">新增</button>
    </form>
</shrio:hasPermission>
<table cellspacing="0" cellpadding="10" border="1px solid">
    <tr align="center">
        <td>编号</td>
        <td>属性值</td>
        <td>操作</td>
    </tr>
    <tbody id="tb" align="center"></tbody>
</table>
</body>
</html>
