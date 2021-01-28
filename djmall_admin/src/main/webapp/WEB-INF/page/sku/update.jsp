<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改页面</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/dist/jquery.validate.min.js"></script>
</head>
<body>
<form id="fm">
    <input type="type" name="productType" value="${voResp.productType}">
    <table>
        <tr>
            <td>编号</td>
            <td>属性名</td>
            <td>属性值</td>
        </tr>

        <c:forEach items="${list}" var="l" >
            <tr>
                <td>

                    <input type="checkbox" value="${l.id}"    name="attrIds">

                </td>

                <td>
                    ${l.attrName}
                </td>

                <td>
                    ${l.attrValue}
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="button" value="修改" onclick="update()">
</form>
</body>
<script type="text/javascript">
    function update(){
        $.post(
            "<%=request.getContextPath()%>/sku/update",
            $("#fm").serialize(),
            function(result){
                if(result.code == 200){
                    layer.msg(result.msg);
                    parent.location.href="<%=request.getContextPath() %>/sku/toShow";
                    return;
                }
                layer.msg(result.msg);
                return;
            }
        )
    }


</script>
</html>