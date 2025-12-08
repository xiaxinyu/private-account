<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../system/template/header.jsp"%>
<script type="text/javascript" src="${BasePath}/scripts/credit.js"></script>
<script type="text/javascript" src="${BasePath}/datasource/creidt-datasource.js"></script>
<script type="text/javascript" src="${BasePath}/datasource/consume-datasource.js"></script>
<link rel="stylesheet" type="text/css" href="${BasePath}/css/default/credit/CreditBill.css">

<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 32px">
		<div style="margin-top: 3px;">
			<span class="item_intervel">
	           	<label>Date</label>
				<input id="dateStart" class="easyui-datebox" style="width: 100px;"
					   phptag="phptag_home_m_1_1001"/>
	           	~ 
				<input id="dateEnd" class="easyui-datebox" style="width: 100px;"/>
			</span>
	          
	        <span class="item_intervel">
	           	<label>Type</label>
				<input id="cmbConsumptionType" class="easyui-combobox"
					   data-options="data:credit_datasource.consumptionType,valueField:'id',textField:'text'"
					   style="width: 100px;">
			</span>
	                
	        <span class="item_intervel">
				<label>Card</label>
				<input id="cmbCardTypes" class="easyui-combobox"
					   data-options="data:credit_datasource.cardTypes,valueField:'id',textField:'text'"
					   style="width: 130px;">
			</span>
	
	          <span class="item_intervel">
	          	<label>Consume</label>
				<select id="cmbConsume" class="easyui-combotree"
						data-options="data:consume_datasource" multiple
						style="width:200px;"></select>
	          </span>
	          
	          <span class="item_intervel">
	          	<input id="txtDemo" class="easyui-textbox" style="width:120px"> 
	          </span>
	          
	          <span class="item_intervel">
	              <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchCredits()" >Search</a>  
	              <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="">Export</a>
	          </span>
          </div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="datagrid"  fit="true"
			data-options="rownumbers:true,singleSelect:true,pagination:true,pageSize:50">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">ID</th>
					<th data-options="field:'cardId',width:120">Card type</th>
					<th data-options="field:'transactionDate',width:80,align:'center',formatter:app.date.format">Book date</th>
					<th data-options="field:'transactionDesc',width:250,align:'left'">Transaction desc</th>
					<th data-options="field:'balanceCurrency',width:80,align:'center'">Currency</th>
					<th data-options="field:'balanceMoney',width:100,formatter:app.money.rmb">Balance money</th>
					<th data-options="field:'consumptionType',width:90,align:'center',formatter:fmt.consumptionType,editor:{type:'combobox',options:{valueField:'id',textField:'text',data:credit_datasource.consumptionType}}">Type</th>
					<th data-options="field:'consumeID',width:200,align:'left',formatter:fmt.consume,editor:{type:'combotree',options:{data:consume_datasource}}">Consume Type</th>
					<th data-options="field:'demoArea',width:420,align:'left',editor:{type:'textbox'}">Description</th>
				</tr>
			</thead>
		</table>
	</div>
</body>

<script type="text/javascript">
var datagrid = '#datagrid';
var editIndex = undefined;
$(function() {
	$(datagrid).datagrid({
		method:'post',
		title:'Total:<span id="totalConsume" style="color:red">0</span>',
		url: app_url + '/credit/getCredits',
		onDblClickRow:function(rowIndex, rowData){
			$(this).datagrid('cancelEdit', editIndex);
			editIndex = rowIndex;
			$(this).datagrid('beginEdit', rowIndex);
		},
		onClickRow:function(rowIndex, rowData){
			$(this).datagrid('cancelEdit', editIndex);
		},
		onLoadSuccess:function(data){
			var rowDatas = data.rows;
			if(rowDatas != undefined && rowDatas.length > 0){
				var total = 0;
				for(var i=0;i<rowDatas.length;i++){
					total += rowDatas[i].balanceMoney;
				}
				$("#totalConsume").html(Math.round(total));
			}
		}
	});
	
	var pager = $(datagrid).datagrid('getPager'); // get the pager of datagrid
	pager.pagination({
		buttons : [ {
			iconCls : 'icon-cancel',
			handler : deleteCredit
		}, {
			iconCls : 'icon-edit',
			handler : function() {
				alert('edit');
			}
		}, {
			iconCls : 'icon-save',
			handler : updateCredit
		} , {
			iconCls : 'icon-undo',
			handler : cancelEdit
		}]
	});
});

$(function(){
    $.parser.onComplete = function(){
        $('#dateStart').datebox({onSelect:function(){searchCredits();}});
        $('#dateEnd').datebox({onSelect:function(){searchCredits();}});
        $('#cmbConsumptionType').combobox({onSelect:function(){searchCredits();}});
        $('#cmbCardTypes').combobox({onSelect:function(){searchCredits();}});
        $('#cmbConsume').combotree({onChange:function(){searchCredits();}});
    };
});

function deleteCredit(){
	var rowData = $(datagrid).datagrid("getSelected");
	if(rowData != null){
		$.messager.confirm('Confirm', 'Do you want to delete this credit record？', function(r){
			if (r){
			    credit.deleteCredit({'id':rowData.id}, function(data){
			    	if(data.returnCode == 'success'){
			    		$(datagrid).datagrid('reload');
			    		app.messager.show({title:'Success', msg:'Success to delete this credit record!'});
			    	}else{
			    		app.messager.show({title:'Fail', msg: data.returnMessage});
			    	}
			    },function(e){
					app.messager.show({title:'Error', msg: e.message});
				})
			}
		});
	}else{
		app.messager.show({title:'Delete credit record', msg:'Please select one credit record!'});
	}
}
	
var fmt = {
	consumptionType:function(value,row){
	           if (value == 0) {
					return;
				}
		var consumptionTypes = credit_datasource.consumptionType;
		for (var i = 0; i < consumptionTypes.length; i++) {
			if (consumptionTypes[i].id == value) {
			return consumptionTypes[i].text;
		}
		}
	},
	consume:function(value,row){
		var consumeTarget = consume_operate.find(value);
		if(consumeTarget != null){
			return consumeTarget.text;
		}
		return "";
	}
}

function updateCredit() {
	var rowData = $(datagrid).datagrid('getSelected');
	var rowIndex = $(datagrid).datagrid('getRowIndex',rowData);
	
	var consumptionTypeEidtor = $(datagrid).datagrid('getEditor', {index:rowIndex,field:'consumptionType'});
	var consumptionType = $(consumptionTypeEidtor.target).combobox('getValue');
	
	var consumeEidtor = $(datagrid).datagrid('getEditor', {index:rowIndex,field:'consumeID'});
	var consumeID = $(consumeEidtor.target).combotree('getValue');
	var consumeName = $(consumeEidtor.target).combotree('getText');
	
	var demoAreaEidtor = $(datagrid).datagrid('getEditor', {index:rowIndex,field:'demoArea'});
	var demoArea = $(demoAreaEidtor.target).textbox('getText');
	
	var params = {'id':rowData.id, 'consumptionType':consumptionType,'demoArea':$.trim(demoArea),'consumeID':consumeID,'consumeName':consumeName};
	credit.updateCredit(params, function(data){
    	if(data.returnCode == 'success'){
    		$(datagrid).datagrid('reload');
    	}else{
    		app.messager.show({title:'Fail', msg: data.returnMessage});
    	}
    });
}

function cancelEdit() {
	var rows = $(datagrid).datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		$(datagrid).datagrid('cancelEdit', i);
	}
}
</script>

<script type="text/javascript">
function searchCredits() {
    var dg = $(datagrid);
    if (!dg.length) {
        return;
    }
    var st = dg.data('datagrid');
    if (!st || !st.options) {
        return;
    }
    //Transaction date
	var dateStart = $('#dateStart').datebox('getValue');
	var dateEnd = $('#dateEnd').datebox('getValue');
	if(dateStart!=null && dateStart!=""){
		dateStart = $.trim(dateStart);    
	}else{
		dateStart = '';
	}
	if(dateEnd!=null && dateEnd != ""){
		dateEnd = $.trim(dateEnd);    
	}else{
		dateEnd = '';
	}
	
	//Type
	var consumptionType = $('#cmbConsumptionType').combobox('getValue');
	if(consumptionType!=null && consumptionType!="" && consumptionType != "0"){
		consumptionType = $.trim(consumptionType);
	}else{
		consumptionType = '';
	}
	
	//Card type
	var cardtype = $('#cmbCardTypes').combobox('getValue');
	if(cardtype!=null && cardtype!="" && cardtype != "0"){
		cardtype = $.trim(cardtype);
	}else{
		cardtype = '';
	}
	
	//Consume type
	var consume = $('#cmbConsume').combotree('getValues');
	if(consume!=null && consume!=""){
		consume = $.trim(consume.join(','));
	}else{
		consume = '';
	}
	//demo
	var demoArea = $.trim($('#txtDemo').textbox('getValue'));
    dg.datagrid('load',{'transactionDateStartStr':dateStart,'transactionDateEndStr':dateEnd,'consumptionType':consumptionType,'consumeID':consume,'cardTypeName':cardtype,'demoArea':demoArea,'weekName':''});
}
</script>

<%@ include file="../../system/template/footer.jsp"%>
