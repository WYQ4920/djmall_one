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
           /*userPwd_confirm:{
          	 required: true,
          	 rangelength:[3,9],
          	 equalTo: "#userPwd"
          },*/
           /*userSex:{
           	 required: true,          	 
           },
           userClass:{
           	 required: true,           	 
           }    		     
           */
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
       		/*userPwd_confirm: {
		        required: "确认密码不能为空",
		        rangelength: "确认密码的长度应在3~9",
		        equalTo:"两次输入不一致"
       		},*/
       		/*userSex: {
		        required: "性别不能为空",
       		}, 
       		userClass:{
           	 required: "班级不能为空",           	 
           }*/
       		 
   		 },
   		submitHandler:function(fm){
			const index = layer.load(2, {shade: 0.4});
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
		<label for="userName">用户名</label>
		 <input type="text" name="userName" id="userName"><br>
	 	<label for="userPwd">密 码：</label>
		 <input type="text" name="userPwd" id="userPwd"><br>
		 <%--<label for="userPwd_confirm">确认密码：</label>
		 <input type="text" name="userPwd_confirm" id="userPwd_confirm"><br>--%>
		<%--<label for="userSex">性 别：</label>
			<c:forEach items="${sexList}" var="s">
				<input type="radio" name="userSex" id="userSex" value="${s.id}" >${s.baseName}
			</c:forEach>
			<br>
		<label for="userClass">班 级：</label>
			 <select name="userClass" id="userClass">
			&lt;%&ndash;<option value="2001">2001班</option>
			<option value="2002">2002班</option>
			<option value="2003">2003班</option>&ndash;%&gt;
				 <c:forEach items="${classList}" var="c">
					 <option value="${c.id}">${c.baseName}</option>
				 </c:forEach>
		</select><br>--%>
		 <input type="submit" value="提交注册">
	</form>

</body>
</html>