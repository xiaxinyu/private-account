<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<ul id="consumptionTree">
</ul>
<script type="text/javascript">
$('#consumptionTree').tree({    
	onClick: function(node){
		var url = "${ctx}/account/system/manager/ConsumptionTypeTable.jsp?id=" + node.id;
		$("#detailDiv").panel('open').panel('refresh',url);

	}
}); 
	
$(document).ready(function(){
	 $.ajax({
         url: "${ctx}/AccountMain/ConsumptionTypeAjax_getConsumptionTypeData.action",
         type:"post",
         dataType:'json',
         success: function (value) {
        	 if(value != undefined && value != ""){
        		$('#consumptionTree').tree('append',{   
        		 	data: value
        		});
        	 }
         },
         error: function () {
         }
     });
});
</script>

