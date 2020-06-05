<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>== 储蓄卡账单管理 ==</title>    
	<script type="text/javascript" src="<%=basePath %>/source/jquery/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/source/ui/miniui.js"></script>
	<script type="text/javascript" src="<%=basePath %>/source/javascript/SystemBase.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/source/ui/themes/default/miniui.css"  />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/source/css/SystemBase.css"  />
  </head>
  
  <body>
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <span class="item_intervel">
             	<label>交易日期：</label>
             	 <input id="dateStart" class="mini-datepicker" format="yyyy-MM-dd" style="width:100px;"/>
             	 ~
             	 <input id="dateEnd" class="mini-datepicker" format="yyyy-MM-dd" style="width:100px;"/>
            </span>
            
            <span class="item_intervel">
             	<label>类型：</label>
             	<input id="cmbConsumptionType" class="mini-combobox" style="width:150px;" 
             		textField="text" valueField="id" data="ConsumptionTypes" value="0"  />   
             </span>
	                 
             <span class="item_intervel">
				<label>Card类型 ：</label>
				<input id="cmbCardTypes" class="mini-combobox" style="width:120px;" 
             		textField="text" valueField="id" data="CardTypes" value="0"  onvaluechanged = "display"/>
			</span>
	
            <span class="item_intervel">
            	<label>消费类型：</label>
            	<input id="cmbConsume" class="mini-treeselect" url="<%=basePath %>/source/data/listTree.txt" multiSelect="true" 
			     textField="text" valueField="id" parentField="pid" checkRecursive="true"  width="200"
			     showFolderCheckBox="false"  showClose="true" popupWidth="200" /> 
            </span>
            
            <span class="item_intervel">
                <a class="mini-button" onclick="search()">查询</a>
                <a class="mini-button" onclick="deleteRow()">删除</a>
                <a class="mini-button" onclick="exportExcel()">导出</a>
            </span>
        </div>
        
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:93%;" allowResize="true"
        url="<%=basePath %>/AccountAjax/SaveAccountBillAjax_querySaveBill.action"  idField="id"  pageSize="100" 
        allowCellEdit="true" allowCellSelect="true" multiSelect="false"  editNextOnEnterKey="true"  editNextRowCell="true">        
        <div property="columns">           
            <div type="indexcolumn" ></div>         
	        <div type="checkcolumn" ></div>
            <div field="id"  name="id">ID</div>        
            <div field="cardId" width="150" headerAlign="center" align="center"  allowSort="true">储蓄卡卡号 </div>    
            <div field="billingDate" width="100" dateFormat="yyyy-MM-dd" headerAlign="center" align="center" allowSort="true">记账日 </div> 
            <div field="transactionDate" width="130" dateFormat="yyyy-MM-dd HH:mm:ss" headerAlign="center" align="center" allowSort="true">交易日期</div>
            <div field="transactionAddress" width="200" headerAlign="left" allowSort="true">交易地点</div> 
            <div type="comboboxcolumn" field="consumptionType" width="80" headerAlign="center" align="center" allowSort="true">类型
            	<input property="editor" class="mini-combobox" style="width:100%;" data="ConsumptionTypes" onvaluechanged="onConsumptionTypeChanged"/>        
            </div> 
            <div field="expenses" width="80" headerAlign="center" dataType="currency" currencyUnit="￥" align="right" allowSort="true">支出</div>
            <div field="income" width="80" headerAlign="center" dataType="currency" currencyUnit="￥" align="right" allowSort="true">收入</div>            
            <div field="balance" width="80" headerAlign="center" dataType="currency" currencyUnit="￥" align="right" allowSort="true">账户余额</div>
            <div field="consumeID" displayField="consumeName" width="200"  headerAlign="center" align="center" allowSort="true">消费类型
	           <input property="editor" class="mini-treeselect" url="<%=basePath %>/source/data/listTree.txt" multiSelect="true" 
	                textField="text" valueField="id" parentField="pid" allowInput="true" onvaluechanged="onConsumeChange"/>
	        </div>   
            <div field="thoerCardId" width="200" headerAlign="center" allowSort="true">对方卡号</div>
            <div field="thoerAccountName" width="100" headerAlign="center" allowSort="true">对方户名</div>
            <div field="balanceCurrency" width="50" headerAlign="center" allowSort="true">币种</div>
            <div field="transactionDesc" width="200" headerAlign="left" allowSort="true">摘要</div>
            <div field="cardTypeName" width="170" headerAlign="center" allowSort="true">卡类型名称</div>
	        <div field="demoArea" width="200" headerAlign="center" >备注
               	<input property="editor" class="mini-textarea" style="width:200px;" minWidth="200" minHeight="50" onvaluechanged="onDemoChange"/>
           	</div>
        </div>
    </div>
  </body>
  
   <script type="text/javascript">
        var ConsumptionTypes = [{ id: 0, text: "== 请选择 ==" },{ id: 1, text: "消费" }, { id: 2, text: "储蓄服务"}, { id: 3, text: "信用卡服务"},{ id: 7, text: "工资"},{ id: 6, text: "报销"},{ id: 8, text: "资金流向"}];
   		var CardTypes = [{ id: 0, text: "== 请选择 ==" },{ id: "招商银行一卡通（金卡）", text: "招商银行一卡通（金卡）" }, { id: "中国建设银行（龙卡通431）", text: "中国建设银行（龙卡通431）"}, { id: "中国建设银行（龙卡通450）", text: "中国建设银行（龙卡通450）"}];
   		
   		mini.parse();
        var grid = mini.get("datagrid1");
        grid.load();
        grid.hideColumn("ID");
    </script>
    
    <script type="text/javascript">
	    function onConsumptionTypeChanged(e) {
	    	 var row = grid.getSelected();
	    	 var params ={"ID":row.id,"CONSUMPTION_TYPE":e.value}; 
	    	 
	    	 $.ajax({
                 url: "<%=basePath %>/AccountMain/SaveAccountBillOperate_updateAccountBill.action",
                 data:params,
                 type:"post",
                 success: function (value) {
                	 grid.reload();
                 },
                 error: function () {
                 }
             });
	    }
	    
	    function onConsumeChange(e){
	    	var row = grid.getSelected();
	    	var params ={"ID":row.id,"CONSUME_ID":this.value,"CONSUME_NAME":this.text}; 
	    	 $.ajax({
                 url: "<%=basePath %>/AccountMain/SaveAccountBillOperate_updateConsumeForAccountBill.action",
                 type:"post",
                 data:params,
                 success: function (value) {
                	 grid.reload();
                 },
                 error: function () {
                 }
             });
	    }
	    
	    
	    function onDemoChange(e){
	    	var row = grid.getSelected();
	    	var params ={"ID":row.id,"DEMO":this.value}; 
	    	 $.ajax({
                 url: "<%=basePath %>/AccountMain/SaveAccountBillOperate_updateDemoForAccountBill.action",
                 type:"post",
                 data:params,
                 success: function (value) {
                	 grid.reload();
                 },
                 error: function () {
                 }
             });
	    }
    </script>
    
    <script type="text/javascript">
	    function search() {
	    	//时间
	    	var dateStart = mini.get("dateStart").getValue();
	    	var dateEnd = mini.get("dateEnd").getValue();
	    	if(dateStart!=null && dateStart!=""){
	    		dateStart = dateStart.Format("yyyy-MM-dd");    
	    	}else{
	    		dateStart = "";
	    	}
	    	if(dateEnd!=null && dateEnd != ""){
	    		dateEnd = dateEnd.Format("yyyy-MM-dd");    
	    	}else{
	    		dateEnd = "";
	    	}
	    	
	    	//类型
	    	var consumptionType = mini.get("cmbConsumptionType").getValue();
	    	if(consumptionType!=null && consumptionType!="" && consumptionType != "0"){
	    		consumptionType = $.trim(consumptionType);
	    	}else{
	    		consumptionType = "";
	    	}
	    	
	    	//消费类型
	    	var consume = mini.get("cmbConsume").getValue();
	    	if(consume!=null && consume!=""){
	    		consume = $.trim(consume);
	    	}else{
	    		consume = "";
	    	}
	    	
	    	//Card类型
	    	var cardtype = mini.get("cmbCardTypes").getValue();
	    	if(cardtype == "0"){
	    		cardtype = "";
	    	}
	    	
	        grid.load({"dateStart":dateStart,"dateEnd":dateEnd,"consumptionType":consumptionType,"consume":consume,"cardtype":cardtype});
	    }
	    
	    function onKeyEnter(e) {
	        search();
	    }
    </script>
    
    <script type="text/javascript">
    	function deleteRow(){
    		 var row = grid.getSelected();
	    	 var params ={"ID":row.ID}; 
	    	 
	    	 $.ajax({
                 url: "<%=basePath %>/AccountMain/SaveAccountBillOperate_deleteAccountBill.action",
                 data:params,
                 type:"post",
                 success: function (value) {
                	 grid.reload();
                 },
                 error: function () {
                 }
             });
    	}
    </script>
    

	<script type="text/javascript">
		function exportExcel() {
			//时间
			var dateStart = mini.get("dateStart").getValue();
			var dateEnd = mini.get("dateEnd").getValue();
			if (dateStart != null && dateStart != "") {
				dateStart = dateStart.Format("yyyy-MM-dd");
			} else {
				dateStart = "#";
			}
			if (dateEnd != null && dateEnd != "") {
				dateEnd = dateEnd.Format("yyyy-MM-dd");
			} else {
				dateEnd = "#";
			}
		
			//类型
			var consumptionType = mini.get("cmbConsumptionType").getValue();
			if (consumptionType != null && consumptionType != ""
					&& consumptionType != "0") {
				consumptionType = $.trim(consumptionType);
			} else {
				consumptionType = "#";
			}
		
			//消费类型
			var consume = mini.get("cmbConsume").getValue();
			if (consume != null && consume != "") {
				consume = $.trim(consume);
			} else {
				consume = "#";
			}
			
			//参数
			var params =  dateStart + "," + dateEnd + ","  + consumptionType + "," + consume;

			var form = $("<form>");//定义一个form表单
			form.attr("style", "display:none");
			form.attr("target", "download");
			form.attr("method", "post");
			form.attr("action", "<%=basePath %>/AccountMain/SaveAccountBillOperate_exportAccountBillToExcel");
			$("body").append(form);//将表单放置在web中
			
			var input = $("<input>");
			input.attr("type", "hidden");
			input.attr("name", "exportparams");
			input.attr("value", params);
			form.append(input);

			form.submit();//表单提交 
		}
	</script>
</html>
