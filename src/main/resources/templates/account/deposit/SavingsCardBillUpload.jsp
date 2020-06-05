<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>== 储蓄卡账单导入 ==</title>
    <script type="text/javascript" src="<%=basePath %>/source/jquery/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/source/uploadify/jquery.uploadify.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/source/uploadify/uploadify.css">
  </head>
  
  <body>
	 <input type="file" id="uploadify" name="uploadify">
     <div id="fileQueue"></div>
     <a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>
     <a href="javascript:$('#uploadify').uploadify('cancel')">取消上传</a>

	<script type="text/javascript">		
		$(document).ready(function() {
			$("#uploadify").uploadify({  
				'auto'           : false, //選完文件後是否自動上傳 
				'swf'            : '<%=basePath %>/source/uploadify/uploadify.swf',
				'uploader'       : '<%=basePath %>/AccountMain/SaveAccountBillUploadAjax_uploadBillFileFromSavingCard.action',
				'queueID'        : 'fileQueue',//隊列id,用來展示上傳進度的  
				'queueSizeLimit' : 3,  //同時上傳文件的個數
				'fileTypeDesc'   : '上传账单',  
				'fileTypeExts'   : '*.xls;*.txt;', //控制可上傳文件的擴展名
				'multi'          : true,  //允許多文件上傳
				'buttonText'     : '上传账单',//按鈕上的文字
				'fileSizeLimit' : '200MB', //設置單個文件大小限制 
				'fileObjName' : 'uploadify',  //<input type="file"/>的name
				'method' : 'post',
				'removeCompleted' : true,//上傳完成後自動刪除隊列
				'onUploadSuccess' : function(file, data, response){//單個文件上傳成功觸發
		                               //data就是action中返回來的數據
	                                       文件傳上去後,你可以把文件的名稱等信息寫回來, 把值賦给隱藏的input,再提交過去將文件的路徑等其他信息存到數據庫,實現異步上傳,而且你還可以在這裏動態創建一個刪除按鈕, 點擊時發送ajax請求,把文件名發送過去,把剛上傳的視頻刪了
				},
				'onQueueComplete' : function(){//所有文件上傳完成
					
				}
			});  		    
		});			
	</script>
  </body>
</html>