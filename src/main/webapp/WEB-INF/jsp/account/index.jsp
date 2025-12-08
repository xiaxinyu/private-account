<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../system/header.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jquery-easyui-1.11.4/jquery.portal.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/jquery-easyui-1.11.4/portal.css">
  <body>   
	<div id="portal" style="position:relative">
		<div style="width:30%;">
			<div title="Clock" style="height:240px;"></div>
		    <div title="Tutorials" style="height:240px;"></div>
		</div>
		<div style="width:40%;">
			<div title="DataGrid"  style="height:240px;"></div>
			<div title="Summer"  style="height:240px;"></div>
		</div>
		<div style="width:30%;">
			<div title="Searching" style="height:240px;"></div>
			<div title="Graph" style="height:240px;"></div>
		</div>
	</div> 
  </body>  
  
<script type="text/javascript">
	$(document).ready(function(){
		$('#portal').portal({
			border:false,
			fit:true
		});
	});
</script>
<%@ include file="../system/footer.jsp" %>
