<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.all.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button type="button" class="btn btn-info btn-lg" onclick="toAdd()">新增</button>
	<button type="button" class="btn btn-info btn-lg" onclick="toUpdate()">修改</button>
	<button type="button" class="btn btn-info btn-lg">删除</button>
	<div class="ztree" id="treeDemo"></div>
	<!-- Button trigger modal -->
	<%--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
		Launch demo modal
	</button>--%>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">修改</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="id" id="id">
					资 源 名：<input type="text" name="resourceName" id="resourceName"><br>
					资源编码：<input type="text" name="resourceCode" id="resourceCode"><br>
					路  径：<input type="text" name="url" id="url"><br>
					资源类型：<select name="resourceType" id="resourceType">
								<option value="1">菜单</option>
								<option value="2">按钮</option>
							</select><br>
				</div>
				<div class="modal-footer">
					<%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
					<button type="button" class="btn btn-primary" onclick="update()">Save changes</button>
				</div>
			</div>
		</div>
	</div>

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

	};

	$(function () {
		$.get("<%=request.getContextPath()%>/zjj/resource/list",
			{},
			function (result) {
				if (200 == result.code){
					$.fn.zTree.init($("#treeDemo"), setting, result.data);
				}
			}
		)
	})

	/*新增资源*/
	function toAdd(){
		// 没有选则父级id为0
		var parentId = 0;
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		//获取选中的节点
		var selectedNodes = treeObj.getSelectedNodes();
		if (selectedNodes !== undefined && selectedNodes.length >0){
			// 选中多个则取第一个
			parentId = selectedNodes[0].id;
		}
		alert(parentId);
		//新增
		layer.open({
			type: 2,
			shade: 0.2,
			area:["360px","360px"],
			content: '<%=request.getContextPath() %>/zjj/resource/toAdd/' + parentId,
			end: function () {
				location.reload();
			}
		});
	}

	/*修改资源*/
	function toUpdate(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		//获取选中的节点
		var selectedNodes = treeObj.getSelectedNodes();
		if (selectedNodes == undefined || selectedNodes.length ==0){
			layer.msg("至少选中一个节点");
			return;
		}
		var selectedNode = selectedNodes[0];
		$("#resourceName").val(selectedNode.resourceName);
		$("#resourceCode").val(selectedNode.resourceCode);
		$("#url").val(selectedNode.url);
		$("#resourceType").val(selectedNode.resourceType);
		$("#id").val(selectedNode.id);
		//JQ操作
		$("#myModal").modal();
	}

	function update(){

		$("#myModal").modal('hide');
	}

</script>
</html>