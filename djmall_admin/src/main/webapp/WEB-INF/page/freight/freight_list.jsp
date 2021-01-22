<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>展示运费表</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">

    $(function(){
        search();
        $("#result").hide();
    })

    function search(){
        $.get(
            "<%=request.getContextPath() %>/freight/show",
            {},
            function(result){
                let html = "";
                for (let i = 0; i < result.data.length; i++) {
                    let data = result.data[i];
                    html += "<tr>";
                    html += "<td>"+ data.dictCode +"</td>";
                    html += "<td>"+ data.freight +"</td>";
                    html += "<td>";
                    html += "<shrio:hasPermission name='FREIGHT_UPDATE_BTN'>"
                    html += "<input type='button' value='修改' onclick='toUpdate("+ data.id +")'>";
                    html +="</shrio:hasPermission>"
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }

    function display(value) {
        if (value == "▲") {
            $("#result").hide();
            $("#switch").val("▼");
            return;
        } else {
            $("#result").show();
            $("#switch").val("▲");
        }
    }

    function add(){
        $.post(
            "<%=request.getContextPath() %>/freight/add",
            $("#fm").serialize(),
            function(result){
                if (200 == result.code){
                    if (0 == $("#num").val()) {
                        $("#pinkage").html("已包邮")
                    }
                    window.location.reload();
                    return;
                }
                layer.msg(result.msg);
                return;
            }
        )
    }

    function toUpdate(id){
        layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/freight/toUpdate?id=' + id,
        });
    }

</script>
<body>
<shrio:hasPermission name="FREIGHT_ADD_BTN">
    <input type="button" value="▲" id="switch" onclick="display(this.value)"/>
    <div id="result">
        <form id="fm">
            物流公司:
                <select name="dictCode">
                    <c:forEach var="d" items="${dictData}">
                        <option value="${d.code}">${d.dictName}</option>
                    </c:forEach>
                </select>
            运费:
                <input name="freight" id="num">
                <span style="font-size: 12px;color: red" id="pinkage"></span>
                <button type="button" onclick="add()">新增</button>
        </form>
    </div>
</shrio:hasPermission>
<table cellspacing="0" cellpadding="10" border="1px solid">
    <tr align="center">
        <td>物流公司</td>
        <td>运费</td>
        <td>操作</td>
    </tr>
    <tbody id="tb" align="center"></tbody>
</table>
</body>
</html>
