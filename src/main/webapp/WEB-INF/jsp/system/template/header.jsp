<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
request.setAttribute("BasePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>== 账单管理 ==</title>    
	<script type="text/javascript" src="<%=basePath %>/plugins/jquery-easyui-1.11.4/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/plugins/jquery-easyui-1.11.4/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/scripts/app-plugin.js"></script>
    <script type="text/javascript" src="<%=basePath %>/scripts/date.js"></script>
    <script type="text/javascript" src="<%=basePath %>/plugins/echarts/echarts-2.2.7.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/plugins/jquery-easyui-1.11.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/plugins/jquery-easyui-1.11.4/themes/icon.css">
    <script type="text/javascript">
    	var app_url = '${BasePath}';
        if (typeof require !== 'undefined') {
            require.config({
                paths: {
                    echarts: app_url + '/plugins/echarts/dist'
                }
            });
        }
    </script>
    
  </head>
