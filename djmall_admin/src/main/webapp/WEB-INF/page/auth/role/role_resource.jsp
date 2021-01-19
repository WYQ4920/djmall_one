<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示管理资源</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/zTree_v3/css/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/zTree_v3/js/jquery.ztree.all.js"></script>
</head>
<script type="text/javascript">

    var setting = {
        check: {
            enable: true
        },
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
            "<%=request.getContextPath() %>/auth/role/showResRel",
            {"id": ${id}},
            function(result){
                if (200 == result.code){
                    $.fn.zTree.init($("#resourceList"), setting, result.data).expandAll(true);
                }
            }
        )
    })

    function save(){
        // 获取树对象
        var treeObj = $.fn.zTree.getZTreeObj("resourceList");
        var selectedNodes = treeObj.getCheckedNodes(true);
        var resourceIds = "";
        for (var i = 0; i < selectedNodes.length; i++){
            resourceIds += selectedNodes[i].id + ",";
        }
        resourceIds = resourceIds.substring(0, resourceIds.length - 1);
        alert(resourceIds);
        $.post(
            "<%=request.getContextPath() %>/auth/role/saveRoleResource",
            {"id": ${id}, "resourceIds": resourceIds},
            function (result){
                if (200 == result.code) {
                    layer.msg("保存成功");
                    window.location.reload();
                    parent.location.href="<%=request.getContextPath() %>/auth/role/toShow";
                    return;
                } else {
                    layer.msg(result.msg);
                    return;
                }

            }
        )
    }

</script>
<body>
    <button type="button" onclick="save()">保存</button>
    <div id="resourceList" class="ztree"></div><br>
</body>
</html>
