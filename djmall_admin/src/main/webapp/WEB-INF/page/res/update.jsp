<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/dist/jquery.validate.js"></script>
</head>

<body>

<form id="fm">
	<input type="hidden" value="${one.id}" name="id"/>

	<label for="resourceName">资源名：</label>
	<input type="text" id="resourceName" name="resourceName" value="${one.resourceName}"><br>

	<label for="resourceCode">编码名：</label>
	<input type="text" id="resourceCode" name="resourceCode" value="${one.resourceCode}"><br>
	资源类型：
	<select name="resourceType">
		<option value="1" <c:if test="${one.resourceType == 1}">selected</c:if>>菜单</option>
		<option value="2" <c:if test="${one.resourceType == 2}">selected</c:if>>按钮</option>
	</select><br/>

	<input type="submit" onclick="toUpdate()">
</form>
</body>
<script type="text/javascript">
	$(function(){
		$("#fm").validate({
			rules:{
				resourceName:{
					required: true,
				},
				url:{
					required: true,
				},
				resourceCode:{
					required: true,
				},
			},
			messages:{
				resourceName: {
					required: "请输入资源名",
				},
				url:{
					required: "请输入路径",
				},
				resourceCode: {
					required: "请输入编码",
				}

			},
			submitHandler:function(fm){
				var index = layer.load(2,{shade:0.4});
				$.post(
						"<%=request.getContextPath() %>/res/update?_method=put",
						$("#fm").serialize(),
						function(result){
							layer.msg(result.msg, {
								/* icon: 1, */
								time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
							}, function(){
								//do something
								if(result.code == "200"){
									layer.close(index);
									parent.location.href="<%=request.getContextPath() %>/res/toShowResZtree";
									return;
								}
								layer.close(index);
							})
						});
			}
		});
	})
</script>
<style>
	.error{
		color:red;
		font-size:10px;
	}
</style>
</html>