<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<frameset rows="25%,75%">
	<frame src="<%=request.getContextPath() %>/zjj/index/toTop"></frame>
	<frameset cols="30%,70%">
		<frame src="<%=request.getContextPath() %>/zjj/index/toLeft"></frame>
		<frame src="<%=request.getContextPath() %>/zjj/index/toRight" name="zjjRight"></frame>
	</frameset>
</frameset>
</html>