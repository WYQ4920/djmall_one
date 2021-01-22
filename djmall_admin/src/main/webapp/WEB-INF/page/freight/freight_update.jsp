<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改运费</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">
    function update(){
        $.post(
            "<%=request.getContextPath()%>/freight/update",
            $("#fm").serialize(),
            function(result){
                if(200 == result.code){
                    layer.msg(result.msg);
                    parent.location.href="<%=request.getContextPath() %>/freight/toShow";
                    return;
                }
                layer.msg(result.msg);
                return;
            }
        )
    }

</script>
<body>
<form id="fm">
    <input type="hidden" name="id" value="${list.id}">
    <table align="center" cellspacing="0" cellpadding="5">
        <tr align="center">
            <td>
                运费:
                <input type="text" name="freight" id="freight" value="${list.freight}"/>
            </td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <br>
                <input type="button" value="提交" onclick="update()">
            </td>
        </tr>
    </table>
</form>
</body>
</html>