<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>展示页面</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/dist/jquery.validate.min.js"></script>

</head>
<script type="text/javascript">
	var pageNum=1;
	$(function(){
		show()
	})
	function show(){
		$.post(
			"<%=request.getContextPath() %>/user/list",
			$("#fm").serialize(),
			function(result){
				if(result.code != 200){
					layer.msg(result.msg);
					return;
				}
				let html = "";
				var pageHtml = "";
				for (var i = 0; i < result.data.length; i++) {
					html+="<tr>";
					html+="<td>"+result.data.id+"</td>";
					html+="<td>"+result.data.userName+"</td>";
					/*html+="<td>"+result.data.records[i].sex+"</td>";
					html+="<td>"+result.data.records[i].age+"</td>";
					html+="<td>"+result.data.records[i].nickName+"</td>";*/
					html+="<td>";
					html+="<input type='button' value='修改' onclick='upd("+result.data.id+")'>";
					/*html+="<input type='button' value='删除' onclick='del("+result.data.id+")'>";*/
					html+="<input type='button' value='授予角色' onclick='upd("+result.data.id+")'>";
					html+="</td>";
					html+="</tr>";
				}
				$("#tb").html(html);
				/*pageHtml += "<input type='button' onclick='page(true,null)' value='上一页'>";
				pageHtml += "<input type='button' onclick='page(false,"+result.data.pages+")' value='下一页'>";
				$("#pageDiv").html(pageHtml);*/
			}
		)
		
	}

	/*function page(page,pages){
		if (page){
			if (pageNum==1){
				layer.msg("首页");
				return;
			}
			pageNum--;
		}else {
			if (pageNum>=pages){
				layer.msg("尾页");
				return;
			}
			pageNum++;
		}
		show(pageNum);
	}*/
	  					
	function upd(id){
		layer.open({
			type : 2,
			title : '修改',
			shade : 0.5,
			area : [ '380px', '360px' ],
			content : '<%=request.getContextPath() %>/user/toUpdate/'+id //iframe的url
		});
		
	}	
	
	/*function del(id){
		$.post(
			"<%=request.getContextPath() %>/users/del",
			{"id":id,"isDel":0},
			function(result){
				if(result.code!=200){
					layer.msg(result.msg);
					return;
				}
				show(pageNum);
			})
	}*/

	function query(){
		/*show(pageNum);*/
		show();
	}

	function add(){
		layer.open({
			type : 2,
			title : '注册页面',
			shade : 0.5,
			area : [ '380px', '360px' ],
			content : '<%=request.getContextPath() %>/user/toAdd' //iframe的url
		});
	}

	/*function check(){
		$.post(
				"<%=request.getContextPath() %>/user/check",
				{"id":1},
				function (result){
					if(result.code == 200){
						layer.msg("success!")
						alert(result.data);
					}
				}
		)
	}*/
	

</script>
<style>

</style>

<body>
	<form id="fm">
		用户名：<input type="text" name="userName"><br>
		<%--年龄：<input type="text" name="startAge">--
		<input type="text" name="endAge">
		性别：<input type="radio" name="sex" value="男">男
		<input type="radio" name="sex" value="女">女
		<br>--%>
		<input type="button" value="查询" onclick="query()">
		<input type="button" value="新增" onclick="add()">
		<br>
		<table>
			<tr>
				<td>用户ID</td>
				<td>用户名</td>
				<%--<td>性别</td>
				<td>年龄</td>
                <td>昵称</td>--%>
				<td>操作</td>
			</tr>
			<tbody id="tb"></tbody>
		</table>
		<div id="pageDiv"></div>
	</form>	

</body>
</html>