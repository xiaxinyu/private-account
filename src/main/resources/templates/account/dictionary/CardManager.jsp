<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>== 账单管理 ==</title>    
	<script type="text/javascript" src="<%=basePath %>/source/jquery/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/source/ui/miniui.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/source/ui/themes/default/miniui.css"  />
  </head>
  
  <body>
	<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
            <div title="south" region="south" showSplit="false" showHeader="false" height="30" bodyStyle="border:0;overflow:hidden;">    
            </div>
            <div title="center" region="center" bodyStyle="overflow:hidden;">    
                <!--Splitter-->
                <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
                    <div size="180" maxSize="250" minSize="100" showCollapseButton="true">
						<ul id="leftTree" class="mini-tree" url="<%=basePath %>/source/data/CardManager.txt" style="width:180px;padding:5px;" 
					        showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false" 
					        showArrow="true" expandOnNodeClick="true">
				    	</ul>
                    </div>
                    <div showCollapseButton="false">
						<div id="mainTabs" class="mini-tabs bg-toolbar" activeIndex="0" style="width:100%;height:100%;"      
    					bodyStyle="border:0;background:white;">        
    						<div title="Home" url="../../docs/api/overview.html" >        
						    </div>
						</div>
                    </div>        
                </div>
            </div>
        </div>  
  </body>
</html>
