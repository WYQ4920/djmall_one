
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
<head>
    <title>Title</title>
</head>
<body>
<form id="fm">

    收件人<input type="text" name="userName"><br>
    手机号<input type="text" name="userPhone"><br>
    省市县
    <select onchange="chengeSheng()" id="sheng" name="sheng">
        <option>请选择</option>
    </select>

    <select  onchange="chengeShi()" id="shi" name="shi">
        <option>请选择</option>
    </select>

    <select id="qu" name="qu">
        <option>请选择</option>
    </select><br>

    街道详细地址<input type="text" name="details"><br>

    <input type="button" value="确定" onclick="toAdd()">
</form>



</body>
<script type="text/javascript">

    $(function(){
        show();
    });
    function show(){

        //获得上级id
        var parentId = 0;

        //清空其他下拉框
        $("#shi option:gt(0)").remove();
        $("#qu option:gt(0)").remove();

        $.post(
            "<%=request.getContextPath() %>/areas/show",
            {"parentId":parentId},
            function(result){
                var htmls="";

                for (var i = 0; i < result.data.length; i++) {
                    var html="";
                    html+="<option value="+result.data[i].id+">"+result.data[i].areaName+"</option>";
                    htmls+=html
                }
                $("#sheng").append(htmls);
            })
    }

    function chengeSheng(){

        //获得上级id
        var parentId = $("#sheng").val();

        //清空其他下拉框
        $("#shi option:gt(0)").remove();
        $("#qu option:gt(0)").remove();

        $.post(
            "<%=request.getContextPath() %>/areas/show",
            {"parentId":parentId},
            function(result){
                var htmls="";

                for (var i = 0; i < result.data.length; i++) {
                    var html="";
                    html+="<option value="+result.data[i].id+">"+result.data[i].areaName+"</option>";
                    htmls+=html
                }
                $("#shi").append(htmls);
            })
    }

    function chengeShi(){

        //获得上级id
        var parentId = $("#shi").val();

        //清空其他下拉框
        $("#qu option:gt(0)").remove();

        $.post(
            "<%=request.getContextPath() %>/areas/show",
            {"parentId":parentId},
            function(result){
                var htmls="";
                for (var i = 0; i < result.data.length; i++) {
                    var html="";
                    html+="<option value='"+result.data[i].id+"'>"+result.data[i].areaName+"</option>";
                    htmls+=html
                }
                $("#qu").append(htmls);
            })
    }
    function toAdd(){
        $.post(
            "<%=request.getContextPath() %>/site/add",
            $("#fm").serialize(),
            function(result){
                layer.msg(result.msg, {
                    /* icon: 1, */
                    time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                }, function(){
                    //do something
                    if(result.code == "200"){
                        parent.location.href = "<%=request.getContextPath()%>/order/toDetermine";
                        layer.close(index);
                        return;
                    }
                    layer.close(index);
                })
            });
    }


</script>
</html>
