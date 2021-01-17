<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>展示资源</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.all.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <button type="button" onclick="toAdd()" class="btn btn-primary">新增资源</button>&nbsp;
    <button type="button" onclick="toUpdate()" class="btn btn-primary">修改资源</button>&nbsp;
    <button type="button" onclick="del()" class="btn btn-primary">删除资源</button>&nbsp;
<form id="fm">
    <div id="resourceList" class="ztree"></div><br>
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body">
                    <table>
                        <tr>
                            <input type="hidden" name="id" id="id">
                            <td>
                                资源名：<input type="text" name="resourceName" id="resourceName">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                资源编码：<input type="text" name="resourceCode" id="resourceCode">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                路径：<input type="text" name="url" id="url">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                资源类型：
                                <select name="resourceType" id="resourceType">
                                    <option value="1">菜单</option>
                                    <option value="2">按钮</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                    <button type="button" class="btn btn-primary" onclick="update()">修改资源</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">

    var setting = {
        data: {//数据
            simpleData: {
                enable: true,
                idKey: "id",//节点数据中保存唯一标识的属性名称
                pIdKey: "parentId"//节点数据中保存其父节点唯一标识的属性名称
            },
            key: {
                name: "resourceName", // 对应资源名属性
                url: "xUrl"
            }
        }
    }

    $(function show(){
        $.get(
            "<%=request.getContextPath() %>/auth/resource/wyq/list",
            $("#fm").serialize(),
            function(result){
                if (200 == result.code){
                    $.fn.zTree.init($("#resourceList"), setting, result.data);
                }
            }
        )
    })

    /* 新增 */
    function toAdd(){
        var parentId = 0;
        // 获取树对象
        var treeObj = $.fn.zTree.getZTreeObj("resourceList");
        var selectedNodes = treeObj.getSelectedNodes();
        if (selectedNodes != undefined && selectedNodes.length > 0){
            // 选择节点作为父id
            var selectedNode = selectedNodes[0];
            parentId = selectedNode.id;
        }
        layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/auth/resource/wyq/toAdd?parentId=' + parentId,
            end: function () {
                location.reload();
            }
        });
    }

    /* 去修改 */
    function toUpdate(){
        // 获取树对象
        var treeObj = $.fn.zTree.getZTreeObj("resourceList");
        // 获取选择节点信息
        var selectedNodes = treeObj.getSelectedNodes();
        if (undefined == selectedNodes || 0 == selectedNodes.length){
            layer.msg("请选择要修改的资源信息");
            return; // 终止后续流程
        }
        // 选择修改的资源节点
        var selectedNode = selectedNodes;
        $("#id").val(selectedNode[0].id);
        $("#resourceName").val(selectedNode[0].resourceName);
        $("#url").val(selectedNode[0].url);
        $("#resourceCode").val(selectedNode[0].resourceCode);
        $("#resourceType").val(selectedNode[0].resourceType);
        $("#myModal").modal(); // 打开弹框
    }

    /* 修改 */
    function update() {
        alert($("#resourceCode").val());
        $.post(
            "<%=request.getContextPath() %>/auth/resource/wyq/update?_method=put",
            $("#fm").serialize(),
            function(result){
                if (200 == result.code){
                    $("#myModal").modal("hide"); // 关闭弹框
                }
            }
        )
    }

    /* 删除 */
    function del(){
        // 获取树对象
        var treeObj = $.fn.zTree.getZTreeObj("resourceList");
        // 获取选择节点信息
        var selectedNodes = treeObj.getSelectedNodes();
        if (undefined == selectedNodes || 0 == selectedNodes.length){
            layer.msg("请选择要删除的资源信息");
            return; // 终止后续流程
        }
        // 选择删除的资源节点
        var selectedNode = selectedNodes[0];
        var ids ="";
        debugger;
        if (selectedNode.isParent) {
            // 获取所选节点的子节点
            ids = getChildNode(selectedNode);
        }
        ids += selectedNode.id;
        alert(ids);
        $.post(
            "<%=request.getContextPath() %>/auth/resource/wyq/del",
            {"resourceIds": ids},
            function(result){
                if (200 == result.code){
                    layer.msg(result.msg);
                    window.location.reload();
                    show();
                    return;
                }
            }
        )
    }

    /* 获取删除的所有节点 */
    function getChildNode(parentNode){
        var ids = "";
        var childList = parentNode.children;
        for (var i = 0; i < childList.length; i++) {
            var child = childList[i];
            ids += child.id + ",";
            if (child.isParent) {
                getChildNode(child);
            }
        }
        alert(ids);
        return ids;
    }

</script>
</html>