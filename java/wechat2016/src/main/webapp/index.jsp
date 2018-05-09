<%@page import="com.x2016.util.AccessTokenThread"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1" />
<script src="http://lib.sinaapp.com/js/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<title>index</title>
<script type="text/javascript">
	$(function(){
		$('#tempList').click(function(){
			$.post('${ctx }/temp/msg/list',{});
		});
		$('#setIndustry').click(function(){
			$.post('${ctx }/temp/msg/set/industry',{});
		});
		$('#getIndustry').click(function(){
			$.post('${ctx }/temp/msg/get/industry',{});
		});
		$('#getTempId').click(function(){
			$.post('${ctx }/temp/msg/get/tempid',{});
		});
		$('#send').click(function(){
			$.post('${ctx }/temp/msg/send',{});
		});
		$('#cmenu').click(function(){
			$.post('${ctx }/menu/create',{});
		});
		$('#gmenu').click(function(){
			$.post('${ctx }/menu/get',{});
		});
		$('#dmenu').click(function(){
			$.post('${ctx }/menu/del',{});
		});
	});
</script>
</head>
<body>
	${ctx }
	<h2>hello world!</h2>
	AccessToken: <%=AccessTokenThread.accessToken.getAccessToken() %> <br/>
	Expiresin: <%=AccessTokenThread.accessToken.getExpiresin() %> <br/>
	
	<a href="#" id="tempList">获取模板消息列表</a>
	<br/> <a href="#" id="setIndustry">设置模板消息所属行业</a>
	<br/> <a href="#" id="getIndustry">获取模板消息所属行业</a>
	<br/> <a href="#" id="getTempId">获得模板ID</a>
	<br/> <a href="#" id="send">发送模板消息</a>
	<br/>
	<br/> <a href="#" id="cmenu">设置菜单</a>
	<br/> <a href="#" id="gmenu">查询菜单</a>
	<br/> <a href="#" id="dmenu">删除菜单</a>
</body>
</html>