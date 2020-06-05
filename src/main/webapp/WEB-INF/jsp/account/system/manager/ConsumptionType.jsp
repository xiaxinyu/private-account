<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/system/header.jsp" %>

<div id="tools">
	<span class="toolBtnSpace"><a href="#" class="icon-edit" onclick="editRow()"></a></span>
	<span class="toolBtnSpace"><a href="#" class="icon-save" onclick="saveRow()"></a></span>
	<span class="toolBtnSpace"><a href="#" class="icon-reload" onclick="saveRow()"></a></span>
</div>

<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'消费类型树',width:300,href:'${ctx}/account/system/manager/ConsumptionTypeTree.jsp'" ></div>
    <div id="detailDiv" data-options="title:'消费类型列表',region:'center',href:'${ctx}/account/system/manager/ConsumptionTypeTable.jsp',tools:'#tools'"></div>
</body>
<%@ include file="/system/footer.jsp" %>
