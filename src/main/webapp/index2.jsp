<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/credit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/jquery-2.1.4.min.js"></script>
</head>
<body>
	<div style="display: block; width: 100px; height:25px; border: 1px dotted;"></div>
	<br />
	<input type="button" onclick="delteCredit()" value="delteCredit"></input>
	
	<script type="text/javascript">
		var app_url = "<%=request.getContextPath()%>";
		function delteCredit(){
			credit.deleteCredit({"id":"20150831161101231b422f099845de8f51c829454a9ddb"}, function(data){
				console.info(data);
			},function(e){
				console.info(e.responseText);
			});
		}
	</script>
</body>
</html>