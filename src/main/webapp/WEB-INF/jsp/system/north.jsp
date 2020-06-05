<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="northArea" style="width: 100%;height: 82px;background-size: 100% auto;background-repeat:no-repeat;background-position:left 0;background-image: url('${ctx}/images/${skin}/system/mainPage.jpg');">
	<div style="text-align:right;">
		<div style=" position: absolute; right:20px; top: 60px; ">
			<c:choose>
				<c:when test="${not empty app_username}">
					<span>${app_username}</span>&nbsp;|&nbsp;<span onclick="logout()">Logout</span> 
				</c:when>
				<c:otherwise>
					<span onclick="createLoginDialog()">Login</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>