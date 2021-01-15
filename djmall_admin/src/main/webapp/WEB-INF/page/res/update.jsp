<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
</head>

<body>

<form id="fm">
	<input type="hidden" value="${one.id}" name="id"/>
	<input type="text" name="resourceName" value="${one.resourceName}">
	<input type="text" name="resourceCode" value="${one.resourceCode}">
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
					remote: {
						type: "post",
						url: "<%=request.getContextPath()%>/res/checkResourceName",
						data:{
							resourceName: function() {
								return $("#resourceName").val();
							}
						}
					}
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
					remote:"资源名重复"
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
						"<%=request.getContextPath() %>/res/update",
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
</html>