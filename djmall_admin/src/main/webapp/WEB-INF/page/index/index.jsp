<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<frameset rows="20%,80%">
	<frame src="<%=request.getContextPath() %>/index/toTop?token=${token}"></frame>
	<frameset cols="30%,70%">
		<frame src="<%=request.getContextPath() %>/index/toLeft?token=${token}"></frame>
		<frame src="<%=request.getContextPath() %>/index/toRight?token=${token}" name="right"></frame>
	</frameset>
</frameset>
</html>