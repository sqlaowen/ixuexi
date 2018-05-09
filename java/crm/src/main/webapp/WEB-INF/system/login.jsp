<%@page import="com.x2016.util.AccessTokenThread"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1" />
<script src="${ctx}/resource/jquery-1.10.2.min.js"></script>
	
<script type="text/javascript">
	$(function(){
		if(top.location.href!=this.location.href){
			top.location.href=this.location.href;
		}
	});
</script>
<body>
	<ul>
	<c:forEach items="${error }" var="key">
		<li>${key }</li>
   	</c:forEach>
	</ul>
	<form method="post" action="${ctx }/login">
		<table>
			<tr>
				<td>账号:</td>
				<td>
					<input type="text" name="userAccount" id="userAccount" value="${userAccount }" />
				</td>
			</tr>
			<tr>
				<td>密码:</td>
				<td>
					<input type="text" name="userPwd" id="userPwd" value="${userPwd }" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<for><input type="checkbox" name="rememberMe"/>记住我</for>
				</td>
			</tr>
		</table>
		<input type="submit" value="登录" />
	</form>
  tooken:	<%=AccessTokenThread.accessToken.getAccessToken() %>
</body>
</html>