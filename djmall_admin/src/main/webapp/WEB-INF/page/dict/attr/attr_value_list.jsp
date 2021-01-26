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
        $.post(
            "<%=request.getContextPath() %>/product/attr/listValue",
            {"attrId":'${list.id}'},
            function(result){
                let html = "";
                for (let i = 0; i < result.data.length; i++) {
                    let data = result.data[i];
                    html += "<tr>";
                    html += "<td>"+ data.id +"</td>";
                    html += "<td>"+ data.attrValue +"</td>";
                    html += "<td>";
                    html += "<shrio:hasPermission name='RELEVANCE_ATTRIBUTE_VALUE_REMOVE_BTN'>"
                    html += "<input type='button' value='移除' onclick='remove("+ data.id +")' style='color: cornflowerblue;border: white;background-color: white'>";
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
            "<%=request.getContextPath() %>/product/attr/addAttrValue",
            $("#fm").serialize(),
            function(result){
                if (200 != result.code){
                    layer.msg(result.msg);
                    return;
                }
                window.location.reload();
                search();
                return;
            }
        )
    }

    /* 移除 */
    function remove(id){
        $.post(
            "<%=request.getContextPath() %>/product/attr/removeAttrValue",
            {"id":id},
            function(result){
                if (200 != result.code){
                    layer.msg(result.msg);
                    return;
                }
                search();
                return;
            }
        )
    }

</script>
<body>
<shrio:hasPermission name="RELEVANCE_ATTRIBUTE_VALUE_ADD_BTN">
    <form id="fm">
        <input name="attrId" value="${list.id}" type="hidden">
        属性名:
            <input type="text" name="attrName" value="${list.attrName}" disabled="disabled"><br>
        已关联属性值:<br>
            属性值:
            <input type="text" name="attrValue">
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
