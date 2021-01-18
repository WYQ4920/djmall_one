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
	<button type="button" class="btn btn-info btn-lg" onclick="del()">删除</button>
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
					<form id="fm">
						<input type="hidden" name="id" id="id">
						资 源 名：<input type="text" name="resourceName" id="resourceName"><br>
						资源编码：<input type="text" name="resourceCode" id="resourceCode" disabled><br>
						路  径：<input type="text" name="url" id="url"><br>
						资源类型：<select name="resourceType" id="resourceType">
									<option value="1">菜单</option>
									<option value="2">按钮</option>
								</select><br>
					</form>
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

	/*去修改资源*/
	function toUpdate(){
		// 获取整个树对象
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		// 获取选中的节点
		var selectedNodes = treeObj.getSelectedNodes();
		if (selectedNodes == undefined || selectedNodes.length ==0){
			layer.msg("至少选中一个节点");
			return;
		}
		// 获取选中的第一个节点
		var selectedNode = selectedNodes[0];
		// 回填数据
		$("#resourceName").val(selectedNode.resourceName);
		$("#resourceCode").val(selectedNode.resourceCode);
		$("#url").val(selectedNode.url);
		$("#resourceType").val(selectedNode.resourceType);
		$("#id").val(selectedNode.id);
		//JQ操作 打开模态框
		$("#myModal").modal();
	}

	function update(){
		//修改
		$.post(
				"<%=request.getContextPath() %>/zjj/resource/update?_method=put",
				$("#fm").serialize(),
				function (result){
					if (result.code == 200){
						layer.msg("修改成功");
						location.href="<%=request.getContextPath() %>/zjj/resource/toShow";
					}else {
						layer.msg(result.msg);
					}
				})
		//JQ操作 关闭模态框
		$("#myModal").modal('hide');
	}

	/*删除*/
	function del(){
		// 获取整个树对象
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		// 获取选中的节点
		var selectedNodes = treeObj.getSelectedNodes();
		if (selectedNodes == undefined || selectedNodes.length ==0){
			layer.msg("至少选中一个节点");
			return;
		}
		// 获取选中的第一个节点
		var selectedNode = selectedNodes[0];

		var ids = "";
		if (selectedNode.isParent){
			ids = getChildNode(selectedNode);
		}
		ids += selectedNode.id;
		alert(ids);
		$.post(
				"<%=request.getContextPath() %>/zjj/resource/del?_method=delete",
				{"resourceIds": ids},
				function(result) {
					if (result.code == 200) {
						layer.msg("删除成功");
						location.href = "<%=request.getContextPath() %>/zjj/resource/toShow";
					} else {
						layer.msg(result.msg);
					}
			})
	}

	/*递归获取子节点*/
	function getChildNode(parentNode){
		var ids = "";
		var childList = parentNode.children;
		for (var i = 0; i < childList.length; i++) {
			var child = childList[i];
			ids += child.id + ",";
			if (child.isParent){
				ids += getChildNode(child);
			}
		}
		return ids;
	}

</script>
</html>