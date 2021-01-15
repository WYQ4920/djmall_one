<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/demo.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.exedit.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a id="treeDemo" class="ztree"></a>
	<center>
		<a href="<%=request.getContextPath() %>/user/toShow" target="right">用户管理</a><br>
		<a href="<%=request.getContextPath() %>/res/toShowResZtree" target="right">资源管理</a><br>
		<a href="<%=request.getContextPath() %>/role/toShow" target="right">角色管理</a><br>
		<a href="<%=request.getContextPath() %>/auth/resource/toShow" target="right">WYQ -> 资源管理</a><br>
	</center>
</body>
<script type="text/javascript">
	var setting = {
		edit : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId"
			},
			key : {
				name : "resourceName"
			}
		}
	};
	$(function () {
		show();
	})
	function show() {
		$.post("<%=request.getContextPath()%>/res/resourceShow",
				{},
				function (result) {
					$.fn.zTree.init($("#treeDemo"), setting, result.data);
				})
	}
</script>

</html>