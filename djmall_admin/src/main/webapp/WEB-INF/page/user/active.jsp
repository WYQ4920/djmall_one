<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/dist/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/MD5/md5-min.js"></script>
</head>
<script type="text/javascript">
	

	function add(){
		layer.open({
			type : 2,
			title : '注册页面',
			shade : 0.5,
			area : [ '380px', '360px' ],
			content : '<%=request.getContextPath() %>/user/toAdd' //iframe的url
		});
	}




</script>
<style>

</style>
<body>
	<h2>激活成功，<a href="<%=request.getContextPath()%>/user/toLogin">点我去登录</a>!</h2>

</body>
</html>