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
	

	function sendSms(btn){
		$.post(
				"<%=request.getContextPath() %>/user/sendSms",
				{
					"verifyCode":$("#verifyCode").val(),
					"userPhone":$("#userPhone").val()
				},
				function (result){
					if (result.code == 200){
						layer.msg("手机验证码发送成功");
						$(btn).attr("disabled","disabled");
						/*开启定时器*/
						var i = 60;
						var interval = setInterval(function (){
							$(btn).html("请"+i--+"秒后重新发送");
							//	超时以后
							if(i <= 0){
								clearInterval(interval);
								$(btn).removeAttr("disabled");
								$(btn).html("请重新发送");
							}
						},1000);
						return;
					}
					layer.msg(result.msg);
				}
		)

	}

/*	function forgetPwd(){
		$("#pwd").val(md5(md5($("#pwd").val()) + $("#salt").val()));
		$("#pwd2").val(md5(md5($("#pwd2").val()) + $("#salt").val()));
		$.post(
				"<%=request.getContextPath() %>/user/forgetPwd",
				{
					"userPhone":$("#userPhone").val(),
					"smsCode":$("#smsCode").val(),
					"pwd":$("#pwd").val(),
					"pwd2":$("#pwd2").val(),
					"salt":$("#salt").val(),
				},
				function (result){
					if(result.code == 200){
						layer.msg("更改密码成功");
						return;
					}
					layer.msg(result.msg);
					location.href = "<%=request.getContextPath()%>/user/toLogin";
				}
		)
	}*/
	$(function () {
		$("#fm").validate({
			rules: {
				pwd: {
					required: true,
					rangelength: [3, 9]
				},
				pwd2: {
					required: true,
					rangelength: [3, 9],
					equalTo: "#pwd"
				},
			},
			messages: {

				pwd: {
					required: "密码不能为空",
					rangelength: "密码长度应在3~9",
				},
				pwd2: {
					required: "确认密码不能为空",
					rangelength: "确认密码的长度应在3~9",
					equalTo: "两次输入不一致"
				}

			},
			submitHandler: function (fm) {
				const index = layer.load(2, {shade: 0.4});
				// md5(md5(明文)+盐)
				$("#pwd").val(md5(md5($("#pwd").val()) + $("#salt").val()));
				$("#pwd2").val(md5(md5($("#pwd2").val()) + $("#salt").val()));
				$.post(
						"<%=request.getContextPath()%>/user/forgetPwd",
						{
							"userPhone":$("#userPhone").val(),
							"smsCode":$("#smsCode").val(),
							"pwd":$("#pwd").val(),
							"pwd2":$("#pwd2").val(),
							"salt":$("#salt").val(),
						},
						function (result) {
							layer.msg(result.msg, {
								/* icon: 1, */
								time: 1000 //1.0秒关闭（如果不配置，默认是3秒）
							}, function () {
								//do something
								if (result.code == "200") {
									location.href = "<%=request.getContextPath()%>/user/toLogin";
									layer.close(index);
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
	.error {
		color: red;
		font-size: 10px;
	}
</style>
<body>
	<form id="fm">
		<input type="hidden" name="salt" id="salt" value="${salt}">
		手机号
		<input type="text" name="userPhone" id="userPhone" placeholder="请输入手机号"/><br>

		图形验证码
		<input type="text" name="verifyCode" id="verifyCode">
		<img src="<%=request.getContextPath()%>/user/getVerifyCode" onclick="this.src='<%=request.getContextPath()%>/user/getVerifyCode?d='+Math.random();">
		<br>

		短信验证码
		<input type="text" name="smsCode" id="smsCode">
		<button type="button" onclick="sendSms(this)">发送短信验证码</button><br>


		<label for="pwd">新密码：</label>
		<input type="password" name="pwd" id = "pwd"><br>


		<label for="pwd2">确认新密码：</label>
		<input type="password" name="pwd2" id = "pwd2"><br>

		<input type="submit" value="提交修改">
	</form>

</body>
</html>