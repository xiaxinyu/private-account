<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "== Private Account ==");
   request.getSession().setAttribute("ctx", request.getContextPath());
   request.getSession().setAttribute("skin", "default");
%><%@ include file="header.jsp" %>
  <body class="easyui-layout">   
    <div data-options="region:'north',href:'${ctx}/north.html'" style="height:85px;"></div>
    <div data-options="region:'center',border:false" >
    	<div class="easyui-layout" data-options="fit:true">
	    	<div data-options="title:'Navigator',region:'west',width:200,split:true,href:'<%=request.getContextPath() %>/menu.html'"></div>
		    <div data-options="region:'center',href:'<%=request.getContextPath() %>/navigation.html'" ></div>
	    </div>
    </div>

	<div id="loginArea" style="display: none;">
	    <div id="buttons">
			<a href="#" class="easyui-linkbutton" onclick="login()" style="margin-right: 20px;">Login</a>
		</div>
	    <div id="loginDialog" data-options="buttons:'#buttons'" >
	    	<form id="loginForm" action="${ctx}/application/login" method="post">
		    	<table style="width: 96%;height: 86%;">
		    		<tr>
		    			<td style="width: 20%;text-align: right;">Username:</td>
		    			<td style="text-align: left;">
		    				<input type="text" id="login-username" name = "userName" style="width: 200px;"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td style="width: 20%;text-align: right;">Password:</td>
		    			<td style="width: 20%;text-align: left;">
		    				<input type="password" id="login-password" name = "password" style="width: 200px;"/>
		    			</td>
		    		</tr>
		    	</table>
	    	</form>
	    	<form id="logoutForm" action="${ctx}/application/logout" method="post">
	    		<input type="hidden" name="userName" value="${app_username}">
	    	</form>
	    </div>
    </div>
</body>  

<script type="text/javascript">
var username = '${app_username}';
if(username == ''){
	createLoginDialog();
}else{
	openLoginDialog();
}

function createLoginDialog(){
	$('#loginDialog').dialog({
	    title: 'Login',
	    width: 320,
	    height: 160,
	    closed: false,
	    cache: false,
	    modal: true
	});
}

function openLoginDialog(){
	$('#loginDialog').dialog('refresh');
}

function login(){
	var username = $("#login-username").val();
	var passowrd = $("#login-password").val()
	if($.trim(username) == undefined || $.trim(username) == ''){
		app.messager.show({title:'Warning', msg: 'Username cannot be null!'});
		return;
	}
	if($.trim(passowrd) == undefined || $.trim(passowrd) == ''){
		app.messager.show({title:'Warning', msg: 'Password cannot be null'});
		return;
	}
	$("#loginForm").submit();
}

function logout(){
	$("#logoutForm").submit();
}
</script>

<%@ include file="footer.jsp" %>
