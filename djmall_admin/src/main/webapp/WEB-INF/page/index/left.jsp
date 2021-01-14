<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath() %>/user/toShow" target="right">用户展示</a>
	<br>
	<a href="<%=request.getContextPath() %>/ztree/toShow" target="right">ztree展示</a><br>
	<a href="<%=request.getContextPath() %>/ztree/toShowArea" target="right">三级联动</a><br>
	<a href="<%=request.getContextPath() %>/echars/toShow" target="right">近一周登录统计</a><br>
	<a href="<%=request.getContextPath() %>/ztree/toTest" target="right">ztree测试</a><br>
</body>
</html>