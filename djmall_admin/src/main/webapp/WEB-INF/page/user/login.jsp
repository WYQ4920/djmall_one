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

</head>
<script type="text/javascript">
	
	$(function(){
	    $("#fm").validate({
	        rules:{
				userName:{
	            	 required: true,
	            	 rangelength:[2,8],
	            },
				password:{
	            	 required: true,
	            	 rangelength:[2,8]           	 
	            }
	            
	        },
	        messages:{
				userName: {
			        required: "请输入用户名",
			        rangelength: "用户名长度在2~8"
	       		 },
				password: {
			        required: "密码不能为空",
			        rangelength: "密码长度应在2~8",
	       		},
	       		
	   		 },
	   		submitHandler:function(fm){
	   			let index = layer.load(2,{shade:0.4});
	   			$.get(
	   				"<%=request.getContextPath() %>/users/login",
	   				$("#fm").serialize(),
	   				function(result){
	   					layer.msg(result.msg, {
	   						  /* icon: 1, */
	   						  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
	   						}, function(){
	   						  //do something
	   							if(result.code == "200"){
	   								layer.close(index); 
	   								location.href="<%=request.getContextPath() %>/index/toIndex";
	   								return;
	   							}
	   							layer.close(index); 
	   						}); 
	   					}
	   				);
	   			}   			  			
	   		})
	 	 });
	  					
	function register(){	
		layer.open({
				type : 2,
				title : '注册页面',
				shade : 0.5,
				area : [ '380px', '360px' ],
				content : '<%=request.getContextPath() %>/user/toRegister' //iframe的url
			});
	}
	

	//Frameset中使得页面加载同步
	//判断当前窗口路径与加载路径是否一致。
	if (window.top.document.URL != document.URL) {
		//将窗口路径与加载路径同步
		window.top.location = document.URL;
	}
</script>
<style>

		
</style>
<body>
	<form id="fm">
		<label for="userName">用户名：</label>
		  <input type="text" name="userName" id="userName"/><br>
		<label for="userPwd">密   码：</label>
		   <input type="text" name="userPwd" id="userPwd" ><br>
		<input type="submit" value="登录" >
		<input type="button" value="注册" onclick="register()">
	</form>	

</body>
</html>