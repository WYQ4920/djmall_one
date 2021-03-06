<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.all.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="resourceShow" class="ztree"></div>
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
					parent.right.location.href = "<%=request.getContextPath() %>" + treeNode.url;
				}
			}
		}
	};

	$(function () {
		$.get("<%=request.getContextPath()%>/res/resourceShow",
			{},
			function (result) {
				if (200 == result.code){
					$.fn.zTree.init($("#resourceShow"), setting, result.data);
				}
			}
		)
	})

</script>
</html>