<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../system/template/header.jsp"%>
 <body>
     <form method="post" action="${BasePath}/v1/credit/upload.html" enctype="multipart/form-data">
         <input type="file" name="creditBillFile">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" value="提交">
     </form>
</body>
<%@ include file="../../system/template/footer.jsp"%>
