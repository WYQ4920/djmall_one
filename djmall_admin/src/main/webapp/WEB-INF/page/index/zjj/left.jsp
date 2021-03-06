<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.all.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="ztree" id="resourceDemo"></div>

</body>
<script type="text/javascript">

	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId"
			},
			key : {
				name : "resourceName",
				url : "xUrl"
			}
		},
		callback:{    //第一步
			onClick:function (event, treeId, treeNode) {
				if (!treeNode.isParent) {
					parent.zjjRight.location.href = "<%=request.getContextPath() %>" + treeNode.url;
				}
			}
		}
	};

	$(function () {
		$.get("<%=request.getContextPath()%>/zjj/resource/list",
			{},
			function (result) {
				if (200 == result.code){
					$.fn.zTree.init($("#resourceDemo"), setting, result.data);
				}
			}
		)
	})

</script>
</html>