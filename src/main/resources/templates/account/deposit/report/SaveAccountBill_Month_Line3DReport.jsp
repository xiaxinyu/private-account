<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>== 月消费曲线分布图 ==</title>
    <script type="text/javascript" src="<%=basePath %>/source/fusioncharts/FusionCharts.js"></script>
    <script type="text/javascript" src="<%=basePath %>/source/jquery/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/source/ui/miniui.js"></script>
	<script type="text/javascript" src="<%=basePath %>/source/javascript/SystemBase.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/source/ui/themes/default/miniui.css"  />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/source/css/SystemBase.css"  />
  </head>
  
 <body>
  		
		<div class="mini-toolbar" style="border-bottom:0;padding:0px; height: 25px;">
				<span class="item_intervel">
					<label>年份 ：</label>
					<input id="cmbYear" class="mini-combobox" style="width:80px;" 
	                 		textField="text" valueField="id" data="Years" value="0" onvaluechanged = "display" />
				</span>
				
				<span class="item_intervel">
					<label>类型：</label>
		           	<input id="cmbConsumptionType" class="mini-combobox" style="width:120px;" 
		           		textField="text" valueField="id" data="ConsumptionTypes" value="0"  onvaluechanged = "display"/>   
	            </span>
				
				<span class="item_intervel">
					<label>Card类型 ：</label>
	 				<input id="cmbCardTypes" class="mini-combobox" style="width:160px;" 
	                 		textField="text" valueField="id" data="CardTypes" value="0"  onvaluechanged = "display"/>
				</span>
				
				<span class="item_intervel">
					<label>消费类型 ：</label>
					<input id="cmbConsume" class="mini-treeselect" url="<%=basePath %>/source/data/listTree.txt" multiSelect="true" 
					        textField="text" valueField="id" parentField="pid" checkRecursive="true"  width="450"
					        showFolderCheckBox="false"  showClose="true" popupWidth="500" showClose="true" 
					        oncloseclick="onCloseClick" onvaluechanged = "display"/> 
				</span>
		</div>
  		
  		
        <div id="chartdiv" align="center" style="width: 100%;height: 100%">Chart will load here</div>        
  </body>
	<script type="text/javascript"> 
	var ConsumptionTypes = [{ id: 0, text: "== 请选择 ==" },{ id: 1, text: "消费" }, { id: 2, text: "储蓄服务"}, { id: 3, text: "信用卡服务"},{ id: 7, text: "工资"},{ id: 6, text: "报销"},{ id: 8, text: "资金流向"}];
   	var CardTypes = [{ id: 0, text: "== 请选择  ==" },{ id: "招商银行一卡通（金卡）", text: "招商银行一卡通（金卡）" }, { id: "中国建设银行（龙卡通431）", text: "中国建设银行（龙卡通431）"}, { id: "中国建设银行（龙卡通450）", text: "中国建设银行（龙卡通450）"}];
		var Years = [{ id: "2014", text: "2014" }, { id: "2013", text: "2013"}];
		
		mini.parse();
		
		$(document).ready(function(){
			init();
			
			display();
		});
		
		function init(){
			mini.get("cmbYear").setValue("2014");
			
			mini.get("cmbConsumptionType").setValue("1");
		}
		
		function display(){
			var width = $("#chartdiv").width();
			var height = $("#chartdiv").height();
			var chart = new FusionCharts("<%=basePath %>/source/fusioncharts/Line.swf", "ChartId", width, height, "0", "0");
			
			//查询条件
			var year = mini.get("cmbYear").getValue();
	    	var cardtype = mini.get("cmbCardTypes").getValue();
	    	if(cardtype == "0"){
	    		cardtype = "";
	    	}
	    	var consume = mini.get("cmbConsume").getValue();
	    	if(consume!=null && consume!="" && consume!="0"){
	    		consume = $.trim(consume);
	    	}else{
	    		consume = "";
	    	}
	    	var consumption = mini.get("cmbConsumptionType").getValue();
	    	if(consumption!=null && consumption!="" && consumption != "0"){
	    		consumption = $.trim(consumption);
	    	}else{
	    		consumption = "";
	    	}
			var param = {"year":year,"cardtype":cardtype,"consume":consume,"consumption":consumption};
			
			$.ajax({
				type: "post",
				async: true,
				data: param,
				url: "<%=basePath%>/AccountMain/SaveAccountBillReportAjax_countMonthConsumptionTypeByLine.action",
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
		}
		
		function onCloseClick(e) {
            var obj = e.sender;
            obj.setText("");
            obj.setValue("");
            
            display();
        }
	</script>
</html>
