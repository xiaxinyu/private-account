<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>== 消费类型曲线分布图 ==</title>
    <script type="text/javascript" src="<%=basePath %>/source/jquery/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/source/fusioncharts/FusionCharts.js"></script>
  </head>
  
  <body>
  		<div style="width: 100%;">
  			<div style="width: 100%;">
  				<span>
  					年份 ：
  					<select id="in_year">
  						<option value="2014">2014</option>
  						<option value="2013">2013</option>
  					</select>  
  				</span>
  				<span>
  					月份：
  					<select id="in_month">
  						<option value="01">一月</option>
  						<option value="02">二月</option>
  						<option value="03">三月</option>
  						<option value="04">四月</option>
  						<option value="05">五月</option>
  						<option value="06">六月</option>
  						<option value="07">七月</option>
  						<option value="08">八月</option>
  						<option value="09">九月</option>
  						<option value="10">十月</option>
  						<option value="11">十一月</option>
  						<option value="12">十二月</option>
  					</select>
  				</span>
  			</div>
  		</div>
  
        <div id="chartdiv" align="center" style="width: 100%;height: 100%">Chart will load here</div>        
  </body>
	<script type="text/javascript"> 
		
	
		$(document).ready(function(){
			var width = $("#chartdiv").width();
			var height = $("#chartdiv").height();
			
			var chart = new FusionCharts("<%=basePath %>/source/fusioncharts/Line.swf", "ChartId", width, height, "0", "0");
			
			var year = $("#in_year").find("option:selected").val();
			var month = $("#in_month").find("option:selected").val();
			var param = {"year":year,"month":month};
			$.ajax({
				type: "post",
				async: true,
				data: param,
				url: "<%=basePath%>/AccountMain/AccountBillReportAjax_countMonthConsumptionTypeByLine.action",
				success: function(val){		
							if(val== "error"){
								alert("访问消费类型曲线分布图报表出现错误!");
							}else{
								chart.setXMLData( val );		   
								chart.render("chartdiv");									
							}					
						},
				error: function(val){
					alert(val);
					alert("访问消费类型曲线分布图报表出现错误!");
				},			
				dataType: "text"
			}); 	
		});
	</script>
</html>
