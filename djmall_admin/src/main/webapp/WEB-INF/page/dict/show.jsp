<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/dist/jquery.validate.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/static/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
    <form id="fm">
        分类上级：
        <select name="parentCode" id="parentCode" >
            <option value="SYSTEM">SYSTEM</option>
            <c:forEach items="${dictList}" var="l" >
                <option value='${l.code}'>${l.dictName}</option>
            </c:forEach>
        </select><br>

        <label for="dictName"> 分类名称:</label>
        <input type="text" name="dictName" id="dictName"><br>

        <label for="code">分类code:</label>
        <input type="text" name="code" id="code">

        <input type="submit" value="新增">

        <table width="500" hight="300" cellspacing="0" cellpadding="10" border="1px">
            <tr  align="center">
                <td>字典数据编码</td>
                <td>字典父级编码</td>
                <td>字典数据名称</td>
                <td>操作</td>
            </tr>
            <tbody id="tb"></tbody>
        </table>

    </form>


</body>
<script type="text/javascript">

    $(function(){
        show()
    });


    function show(){
        $.post(
            "<%=request.getContextPath()%>/dict/show",
            {"parentCode":$("#parentCode").val()},
            function(result){
                if(result.code != 200){
                    layer.msg(result.msg);
                    return;
                }
                let html = "";
                var pageHtml = "";
                for (var i = 0; i < result.data.length; i++) {
                    html+="<tr align='center'>";
                    html+="<td>"+result.data[i].code+"</td>";
                    html+="<td>"+result.data[i].parentCode+"</td>";
                    html+="<td>"+result.data[i].dictName+"</td>";
                    html+="<td>";
                    html+="<input type='button' value='修改' name='checkedCode' onclick='upd1(\""+result.data[i].code+"\")'>";
                    html+="</td>";
                    html+="</tr>";
                }
                $("#tb").html(html);
                /*pageHtml += "<input type='button' onclick='page(true,null)' value='上一页'>";
                pageHtml += "<input type='button' onclick='page(false,"+result.data.pages+")' value='下一页'>";
                $("#pageDiv").html(pageHtml);*/
            }
        )

    }

    /* 新增 */
    $(function(){
        $("#fm").validate({
            rules:{
                dictName:{
                    required:true,
                    remote: {
                        type: "post",
                        url: "<%=request.getContextPath()%>/dict/checkDictName",
                        data:{
                            dictName: function() {
                                return $("#dictName").val();
                            }
                        }
                    }
                },
                code:{
                    required:true,
                    remote: {
                        type: "post",
                        url: "<%=request.getContextPath()%>/dict/checkCode",
                        data:{
                            code: function() {
                                return $("#code").val();
                            }
                        }
                    }
                },
            },
            messages:{
                dictName:{
                    required:"字典名必填",
                    remote:"字典名重复"

                },
                code: {
                    required: "请输入编码",
                    remote:"字典code重复"
                }
            },
            submitHandler:function(fm){
                var index = layer.load(4, {shade:0.2});
                $.post(
                    "<%=request.getContextPath()%>/dict/add",
                    $("#fm").serialize(),
                    function(result){
                        layer.msg(result.msg, {
                            /* icon: 1, */
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            if(result.code == 200){
                                layer.close(index);
                                show();
                                return;
                            }
                            layer.close(index);
                            layer.msg(result.msg);
                            return;
                        });
                    });
            }
        });
    })

    /*
    修改
     */
    function upd1(code){
        layer.open({
            type : 2,
            title : '修改',
            shade : 0.5,
            area : [ '380px', '360px' ],
            content :'<%=request.getContextPath() %>/dict/toUpdate/'+code,//iframe的url
        });

    }




</script>
<style>
    .error{
        color:red;
        font-size:10px;
    }
</style>
</html>