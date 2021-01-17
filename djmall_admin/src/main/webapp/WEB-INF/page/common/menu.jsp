<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>点击ztree</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.all.js"></script>
</head>
<script type="text/javascript">
    var setting = {
        data: {//数据
            simpleData: {
                enable: true,
                idKey: "resourceId",//节点数据中保存唯一标识的属性名称
                pIdKey: "parentId"//节点数据中保存其父节点唯一标识的属性名称
            },
            key: {
                name: "resourceName",
                url: "xUrl"
            },
            callback:{    //第一步
                onClick:function (event, treeId, treeNode) {
                    if (!treeNode.isParent) {
                        parent.right.location.href = "<%=request.getContextPath() %>" + treeNode.url;
                    }
                }
            }
        }
    }
/*
    var data = [
        {"resourceId": 6, "resourceName": "权限管理", "parentId":0, "url": "-"},
        {"resourceId": 2, "resourceName": "用户管理", "parentId":6, "url": "/user/toShow"},
        {"resourceId": 8, "resourceName": "角色管理", "parentId":6, "url": "/auth/role/toShow"},
        {"resourceId": 7, "resourceName": "资源管理", "parentId":6, "url": "/res/toShowResZtree"}
    ]*/

    $(function (){
        $.get(
            "<%=request.getContextPath() %>/getMenu",
            function(result){
                if (200 == result.code){
                    $.fn.zTree.init($("#tree"), setting, result.data);
                }
            }
        )
    })

</script>
<body>
<div id="tree" class="ztree"></div><br>
</body>
</html>
