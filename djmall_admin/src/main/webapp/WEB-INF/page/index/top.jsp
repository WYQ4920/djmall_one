<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">欢迎${user.userName}登陆</h1>
	<div id="time" align="right"></div><br>
	<a href="<%=request.getContextPath() %>/user/exit">退出</a>
</body>
<script type="text/javascript">
	function time(){
		let vWeek, vWeek_s, vDay;
		vWeek = ["星期天","星期一","星期二","星期三","星期四","星期五","星期六"];
		const date = new Date();
		const year = date.getFullYear();
		const month = date.getMonth() + 1;
		const day = date.getDate();
		const hours = date.getHours();
		const minutes = date.getMinutes();
		const seconds = date.getSeconds();
		vWeek_s = date.getDay();
		document.getElementById("time").innerHTML = year + "年" + month + "月" + day + "日" + "\t" + hours + ":" + minutes +":" + seconds + "\t" + vWeek[vWeek_s] ;
	};
		setInterval("time()",1000);
</script>

</html>
