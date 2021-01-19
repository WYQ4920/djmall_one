<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
<script
	src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">
<%-- 	$(function(){
		if('${loginUser.userRank}' != 2){
			$.post(
				"<%=request.getContextPath()%>/exp/getCount",
				{},
				function(result){
					if(result.data>0){
						layer.confirm("您有"+result.data+"条信息待处理,立即去处理？", {icon: 3, title:'提示'}, function(index){
								location.href="<%=request.getContextPath()%>/exp/toShow";							  
							  	layer.close(index);
							  	return;
							});    
					}					
					
				}					
			);
			
		}
		
	})
	
 --%>	

</script>
<body>sss

</body>
</html>