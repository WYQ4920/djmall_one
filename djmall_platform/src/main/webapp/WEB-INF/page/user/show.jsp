<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>页面</title>
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

    $(function () {
        $("#fm").validate({
            rules: {
                nickName: {
                    required: true,
                },
                userEmail: {
                    required: true,
                    email: true,
                },
            },
            messages: {
                nickName: {
                    required: "请输入用户名",
				},
                userEmail: {
                    required: "不能为空",
                    email: "请输入正确的邮箱规则",

                }
            },
            submitHandler: function (fm) {
                const index = layer.load(2, {shade: 0.4});
                $.post(
                    "<%=request.getContextPath()%>/user/update",
                    $("#fm").serialize(),
                    function (result) {
                        layer.msg(result.msg, {
                            /* icon: 1, */
                            time: 1000 //1.0秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            //do something
                            if (result.code == "200") {
                                parent.location.href = "<%=request.getContextPath()%>/index/toIndex";
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
		<input type="hidden" name="id" value="${user.id}">

		<label for="nickName">昵称:</label>
		<input type="text" name="nickName" id="nickName" value="${user.nickName}"><br>

		性别：
		<c:forEach items="${sexList}" var="s">
			<input type="radio" name="userSex" value="${s.code}"  <c:if test="${s.code ==user.userSex}">checked</c:if> >${s.dictName}
		</c:forEach>
		<br>

		<label for="userEmail">邮箱：</label>
		<input type="email" name="userEmail" id="userEmail" value="${user.userEmail}"><br>
		<br>
		<input type="submit" value="提交">
	</form>

</body>
</html>