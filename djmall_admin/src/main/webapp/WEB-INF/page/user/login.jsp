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
	
	$(function(){
	    $("#fm").validate({
	        rules:{
				userName:{
	            	 required: true,
	            	 rangelength:[2,8],
	            },
				userPwd:{
	            	 required: true,
	            	 /*rangelength:[3,9]*/
	            }
	            
	        },
	        messages:{
				userName: {
			        required: "请输入用户名",
			        rangelength: "用户名长度在2~8"
	       		 },
				userPwd: {
			        required: "密码不能为空",
			        /*rangelength: "密码长度应在3~9",*/
	       		},
	       		
	   		 },
	   		submitHandler:function(fm){
	   			let index = layer.load(2,{shade:0.4});
				// 最终密码：md5(md5(明文)+盐)
				$("#userPwd").val(md5(md5($("#userPwd").val())+$("#salt").val()));
	   			$.post(
	   				"<%=request.getContextPath() %>/user/login",
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

	function add(){
		layer.open({
			type : 2,
			title : '注册页面',
			shade : 0.5,
			area : [ '380px', '360px' ],
			content : '<%=request.getContextPath() %>/user/toAdd' //iframe的url
		});
	}

	function getSalt(userName){
		//alert(userName);
		$.post(
				"<%=request.getContextPath() %>/user/getSalt",
				{userName:userName},
				function (result){
					if (result.code == 200){
						$("#salt").val(result.data);
						return;
					}
					layer.msg(result.msg);
				}
		)
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
		<input type="hidden" name="salt" id="salt">
		<label for="userName">用户名：</label>
		<input type="text" name="userName" id="userName" onblur="getSalt(this.value)"/><br>
		<label for="userPwd">密 码：</label>
		<input type="password" name="userPwd" id="userPwd"><br>
		<input type="submit" value="登录">
		<input type="button" value="注册" onclick="add()">
	</form>	

</body>
</html>