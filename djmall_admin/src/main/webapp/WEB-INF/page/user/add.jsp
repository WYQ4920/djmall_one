<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/MD5/md5-min.js"></script>
</head>
<script type="text/javascript">

$(function(){
    $("#fm").validate({
        rules:{
            userName:{
            	 required: true,
            	 rangelength:[2,8],
            	 remote: {
       	        	 	type: "post",
                        url: "<%=request.getContextPath()%>/user/checkUserName",
                        data:{
                       	 userName: function() {
                             return $("#userName").val();
                           }
                       }
            	 }
            },           
           userPwd:{
           	 required: true,
           	 rangelength:[3,9]
           },
           userPwd_confirm:{
          	 required: true,
          	 rangelength:[3,9],
          	 equalTo: "#userPwd"
          },
			nickName:{
				required: true,
				rangelength:[2,8],
				equalTo: "#userName",
			},
           userSex:{
           	 required: true,          	 
           },
			userPhone:{
				required:true,
				isphoneNum:true,
				remote: {
					type: "post",
					url: "<%=request.getContextPath()%>/user/checkUserPhone",
					data:{
						userPhone: function() {
							return $("#userPhone").val();
						}
					}
				}
			},
			userEmail:{
				required:true,
				email:true,
				remote: {
					type: "post",
					url: "<%=request.getContextPath()%>/user/checkUserEmail",
					data:{
						userEmail: function() {
							return $("#userEmail").val();
						}
					}
				}
			},
        },
        messages:{            
            userName: {
		        required: "请输入用户名",
		        rangelength: "用户名长度在2~8",
		        remote:"用户名重复"
       		 },
       		 userPwd: {
		        required: "密码不能为空",
		        rangelength: "密码长度应在3~9",
       		},
       		userPwd_confirm: {
		        required: "确认密码不能为空",
		        rangelength: "确认密码的长度应在3~9",
		        equalTo:"两次输入不一致"
       		},
			nickName:{
				required: "昵称不能为空",
				equalTo:"不能和用户名重复"
			},
       		userSex: {
		        required: "性别不能为空",
       		},
			userPhone:{
				required:"不能为空",
				remote: "用户手机号重复"
			},
			userEmail: {
				required: "不能为空",
				email: "请输入正确的邮箱规则",
				remote: "用户邮箱重复"
			}
   		 },
   		submitHandler:function(fm){
			const index = layer.load(2, {shade: 0.4});
			// md5(md5(明文)+盐)
			$("#userPwd").val(md5(md5($("#userPwd").val())+$("#salt").val()));
			$.post(
   				"<%=request.getContextPath()%>/user/add",
   				$("#fm").serialize(),
   				function(result){
   					layer.msg(result.msg, {
   						  /* icon: 1, */
   						  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
   						}, function(){
   						  //do something
   							if(result.code == "200"){
   								parent.location.href="<%=request.getContextPath()%>/user/toLogin";
   								layer.close(index); 
								return;
							}
							layer.close(index);
						})
					});
				}
			});
		//自定义手机号验证
		jQuery.validator.addMethod("isphoneNum", function(value, element) {
			var length = value.length;
			var mobile = /^1[3|5|8]{1}[0-9]{9}$/;
			return this.optional(element) || (length == 11 && mobile.test(value));
		}, "请正确填写您的手机号码");

	})
</script>
<style>
.error {
	color: red;
	font-size: 10px;
}
</style>
<body>
	<form id="fm">
		<input type="hidden" name="salt" id="salt" value="${salt}">
		<label for="userName">用户名:</label>
		<input type="text" name="userName" id="userName"><br>
		<label for="nickName">昵称:</label>
		<input type="text" name="nickName" id="nickName"><br>
		<label for="userPwd">密 码：</label>
		<input type="text" name="userPwd" id="userPwd"><br>
		<label for="userPwd_confirm">确认密码：</label>
		<input type="text" name="userPwd_confirm" id="userPwd_confirm"><br>
		<label for="userPhone">手机：</label>
		<input type="text" name="userPhone" id="userPhone"><br>
		<label for="userEmail">邮箱：</label>
		<input type="email" name="userEmail" id="userEmail"><br>
		<label for="roleId">级 别：</label>
		<c:forEach items="${roleList}" var="role">
			<input type="radio" name="roleId" id="roleId" value="${role.id}">${role.roleName}
		</c:forEach>
		<br>
		<label for="userSex">性 别：</label>
			<input type="radio" name="userSex" id="userSex" value="1" checked>男
			<input type="radio" name="userSex" id="userSex" value="2">女
		<br>
		<input type="submit" value="提交注册">
	</form>

</body>
</html>