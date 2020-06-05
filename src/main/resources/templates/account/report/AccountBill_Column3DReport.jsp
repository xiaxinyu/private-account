<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>== 消费类型月份分布图 ==</title>
    <script type="text/javascript" src="<%=basePath %>/source/jquery/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/source/fusioncharts/FusionCharts.js"></script>
  </head>
  
  <body>
        <div id="chartdiv" align="center">Chart will load here</div>        
  </body>
	<script type="text/javascript"> 
		$(document).ready(function(){
			var chart = new FusionCharts("<%=basePath %>/source/fusioncharts/Column3D.swf", "ChartId", "560", "400", "0", "0");
			var param = {"name":"summer"};
			$.ajax({
				type: "post",
				async: true,
				data: param,
				url: "<%=basePath%>/AccountMain/AccountBillReportAjax_countConsumptionTypeByColumn.action",
				success: function(val){		
							if(val== "error"){
								alert("访问消费类型月份分布图报表出现错误!");
							}else{
								chart.setXMLData( val );		   
								chart.render("chartdiv");									
							}					
						},
				error: function(val){
					alert(val);
					alert("访问消费类型月份分布图报表出现错误!");
				},			
				dataType: "text"
			}); 	
		}); 
	</script>
</html>