<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



<table id="detaildg" fit="true">
	<thead>   
		<tr>   
		    <th data-options="field:'typeid',width:300,editor:{type:'validatebox'}">消费类型编码</th>   
		    <th data-options="field:'typename',width:300,editor:{type:'validatebox'}">消费类型名称</th>   
		    <th data-options="field:'pxh',width:200,align:'center',editor:{type:'numberbox'}">排序号</th>   
		</tr>   
	</thead>  
</table>
<script type="text/javascript">
	var detaildg = "#detaildg";
	$(detaildg).datagrid({
		url:'${ctx}/AccountMain/ConsumptionTypeAjax_getConsumptionTypeDetail.action',
		queryParams:{"id":"${param.id}"},
		border:false,
		singleSelect:true,
		rownumbers:true,
		//toolbar: '#toolbar',
		onDblClickRow:function(rowIndex, rowData){
			$(this).datagrid('beginEdit', rowIndex);        
		}
	});
	
	/** 编辑一行数据  */
	function editRow(){
		system.beginEdit(detaildg);
	}
	
	/** 保存一行数据 */
	function saveRow(){
		system.endEdit(detaildg);
	}
	
	
	var system = {
		beginEdit:function(datagridID){
			var selRow = $(datagridID).datagrid('getSelected');
			if(selRow != null && selRow != undefined){
				var index = $(datagridID).datagrid('getRowIndex',selRow);
				$(datagridID).datagrid('beginEdit', index);
			}
		},
		endEdit : function(datagridID){
			var rows = $(datagridID).datagrid("getRows");
			for(var rowIndex = 0;rowIndex < rows.length;rowIndex++){
				$(datagridID).datagrid('endEdit', rowIndex);  
			}
		}
	}; 
</script>


