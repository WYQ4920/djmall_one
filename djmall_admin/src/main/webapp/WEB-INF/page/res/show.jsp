<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/demo.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.exedit.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <form>
        <input type="hidden" name="isDel" value="0">
        <input type="button" value="新增资源" onclick="toAdd()"><br>
        <input type="button" value="编辑" onclick="toUpdate()"><br>
        <input type="button" value="删除"  onclick="del()">
    </form>
    <a id="treeDemo" class="ztree"></a>

    <!-- Modal框-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body">
                    <form id="fm">
                        资源名称:<input type="text" name="resourceName" id="resourceName"><br>
                        <input type="hidden" name="id" id="id">
                        url:<input type="text" name="url" id="url"><br>
                        资源码:<input type="text" name="resourceCode" id="resourceCode" disabled><br>
                        资源类型:<select name="resourceType" id="resourceType">
                        <option value="1">菜单</option>
                        <option value="2">按钮</option>
                    </select><br/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="update()">修改</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">

    $(function (){
        show()
    });

    //zTree 的配置数据
    var setting = {
        data: {//数据
            simpleData: {
                enable: true, //true 、 false 分别表示 使用 、 不使用 简单数据模式
                idKey: "id",//节点数据中保存唯一标识的属性名称
                pIdKey: "parentId"//节点数据中保存其父节点唯一标识的属性名称
            },
            key: {
                name: "resourceName",  //zTree 节点数据保存节点名称的属性名称  默认值："name"
                url: ""
            }
        }
    };

    //展示基础数据
    function show(){
        $.post(
            "<%=request.getContextPath() %>/res/showResZtree",
            {"isDel":0},
            function (res){
                $.fn.zTree.init($("#treeDemo"), setting, res.data).expandAll(true);;//生成ztree
            }
        )
    };

    //新增
    function toAdd(){
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var selectedNodes = treeObj.getSelectedNodes();
        if(undefined == selectedNodes || selectedNodes.length == 0){
            layer.open({
                type: 2,
                shade: 0.2,
                area:["360px","360px"],
                content: "<%=request.getContextPath() %>/res/toAdd/"+0,
                end: function () {
                    location.reload();
                }
            });
            return ;
        }
        var selectedNode = selectedNodes[0];
        layer.open({
            type: 2,
            shade: 0.2,
            area:["360px","360px"],
            content: '<%=request.getContextPath() %>/res/toAdd/' +selectedNode.id,
            end: function () {
                location.reload();
            }
        });
    }

    //删除
    function del(){
        // 获取当前被选中的节点数据集合
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getSelectedNodes();
        if(undefined == nodes || nodes.length == 0){
            layer.msg("请选择后再删除");
            return ;
        }
        var ids = '';
        if(nodes[0].children){
            ids = getChildNode(nodes[0]);
        }
        ids += nodes[0].id;
        $.post(
            "<%=request.getContextPath() %>/res/del",
            {"resourceIds":ids},
            function (res){
                if(res.code == 200){
                    layer.msg("删除成功")
                    location.href = "<%=request.getContextPath() %>/res/toShowResZtree";
                }
            }
        )
    }

    //递归
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

    //去修改页面
    function toUpdate(){
        // 获取当前被选中的节点数据集合
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getSelectedNodes();
        if(undefined == nodes || nodes.length == 0){
            layer.msg("请选择后再修改");
            return ;
        }
        $("#resourceName").val(nodes[0].resourceName);
        $("#url").val(nodes[0].url);
        $("#id").val(nodes[0].id);
        $("#resourceCode").val(nodes[0].resourceCode);
        $("#resourceType").val(nodes[0].resourceType);
        //jQ模态框
        $("#myModal").modal();
    }

    //修改
    function update(){
        $.post(
            "<%=request.getContextPath() %>/res/update?_method=put",
            $("#fm").serialize(),
            function (res){
                if(res.code == 200){
                    $("#myModal").modal("hide");
                    show();
                    return ;
                }
                layer.msg(res.msg);
            }
        )
    }

</script>
</html>