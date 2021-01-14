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
        <input type="hidden" id="id" value="0">

        <input type="button" value="新增资源" onclick="toAdd">
        <input type="button" value="编辑" onclick="toUpdate">
        <input type="button" value="删除">
    </form>
    <a id="treeDemo" class="ztree"></a>
</body>
<script type="text/javascript">

    //zTree 的配置数据
    var setting = {
        edit: {
            enable: true,
            editNameSelectAll: true,//编辑名字时候是否为全选
            removeTitle:'删除',//鼠标移到×的提示
            renameTitle:'重命名'//鼠标移到编辑的提示
        },
        data: {//数据
            simpleData: {
                enable: true, //true 、 false 分别表示 使用 、 不使用 简单数据模式
                idKey: "id",//节点数据中保存唯一标识的属性名称
                pIdKey: "parentId"//节点数据中保存其父节点唯一标识的属性名称
            },
            key: {
                name: "resourceName"  //zTree 节点数据保存节点名称的属性名称  默认值："name"
            }
        },
        callback:{
            beforeDrag: zTreeBeforeDrag, //防止拖拽
            beforeClick: zTreeBeforeClick //节点被选中
        }
    };

    //展示基础数据
    $(function(){
        $.post(
            "${ctx}/res/showResZtree",
            {"isDel":0},
            function (res){
                $.fn.zTree.init($("#treeDemo"), setting, res.data);//生成ztree
            }
        )
    });

    //防止拖拽
    function zTreeBeforeDrag(treeId, treeNodes) {
        return false;
    }

    //获得节点id
    function zTreeBeforeClick(treeId, treeNodes) {
        $("#id").val(treeNodes.id);
        alert(treeNodes.id)
        return true;
    }

    function toAdd() {
        var parentId = $("#id").val();
        layer.open({
            type: 2,
            area: ['400px', '300px'], //宽高
            content: '<%=request.getContextPath()%>/res/toAdd?parentId='+parentId,
        });
    }

    function toUpdate() {
        var id = $("#id").val();
        layer.open({
            type: 2,
            area: ['400px', '300px'], //宽高
            content: '<%=request.getContextPath()%>/res/toUpdate?id='+id,
        });
    }

</script>
</html>