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

</head>
<body>
    <form>
        <input type="button" value="新增资源" onclick="toAdd()"><br>
        <input type="button" value="编辑" onclick="toUpdate()"><br>
        <input type="button" value="删除">
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
                    <button type="button" class="btn btn-primary" onclick="upd()">修改</button>
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
                url: "url"
            }
        }
    };

    //展示基础数据
    function show(){
        $.post(
            "<%=request.getContextPath() %>/res/resourceShow",
            {},
            function (res){
                $.fn.zTree.init($("#treeDemo"), setting, res.data).expandAll(true);;//生成ztree
            }
        )
    };

    //新增
    function add(){
        // 获取当前被选中的节点数据集合
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getSelectedNodes();
        if(undefined == nodes || nodes.length == 0){
            location.href = "<%=request.getContextPath() %>/res/toAdd/"+0;
            return ;
            var selectNodes =  treeObj.getSelectedNodes;
            alert(selectNodes)
        }
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
            "del",
            {"ids":ids},
            function (res){
                if(res.code == 200){
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

    //去增加页面
    function toUpd(){
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
    function upd(){
        $.post(
            "<%=request.getContextPath() %>/res/toUpdate",
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