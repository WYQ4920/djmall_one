<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改页面</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/dist/jquery.validate.min.js"></script>
</head>
<script type="text/javascript">

    $(function () {
        $("#fm").validate({
            rules: {
                userName: {
                    required: true,
                    rangelength: [2, 8],
                },
                userPwd: {
                    required: true,
                    rangelength: [3, 9]
                },
                userSex: {
                    required: true,
                },
				userPhone:{
					required:true,
					isphoneNum:true,
				},
				userEmail:{
					required:true,
					email:true,
				},
            },
            messages: {
                userName: {
                    required: "请输入用户名",
                    rangelength: "用户名长度在2~8",
                },
                userPwd: {
                    required: "密码不能为空",
                    rangelength: "密码长度应在3~9",
                },
                userSex: {
                 required: "性别不能为空",
                },
				userPhone:{
					required:"不能为空",
				},
				userEmail: {
					required: "不能为空",
					email: "请输入正确的邮箱规则",
				}


            },
            submitHandler: function (fm) {
                const index = layer.load(2, {shade: 0.4});
                $.post(
                    "<%=request.getContextPath()%>/user/update?_method=put",
                    $("#fm").serialize(),
                    function (result) {
                        layer.msg(result.msg, {
                            /* icon: 1, */
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            //do something
                            if (result.code == "200") {
                                parent.location.href = "<%=request.getContextPath()%>/user/toShow";
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
			var mobile = /^1[3|5|7|8]{1}[0-9]{9}$/;
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
    <input type="hidden" name="id" value="${user.id}">
    <label for="userName">用户名</label>
    <input type="text" name="userName" id="userName" value="${user.userName}"><br>
    <label for="userPhone">手机：</label>
    <input type="text" name="userPhone" id="userPhone" value="${user.userPhone}"><br>
    <label for="userEmail">邮箱：</label>
    <input type="email" name="userEmail" id="userEmail" value="${user.userEmail}"><br>
   <label for="userSex">性 别：</label>
    <input type="radio" name="userSex"  id="userSex" value="1" <c:if test="${user.userSex==1}">checked</c:if> >男
    <input type="radio" name="userSex"  id="userSex" value="2" <c:if test="${user.userSex==2}">checked</c:if> >女
    <br>
    <input type="submit" value="提交修改">
</form>
</body>
</html>