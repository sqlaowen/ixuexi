<%@page import="com.zgxcw.wx.utils.CustomizedPropertyConfigurer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% 
	String wxId=CustomizedPropertyConfigurer.getContextProperty("wxId");
	request.setAttribute("wxId", wxId);
	request.setAttribute("tfsImgPath", CustomizedPropertyConfigurer.getContextProperty("tfs.img.file.path"));
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1" />
<link rel="stylesheet" type="text/css" href="${ctx }/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/js/bootstrap/css/bootstrap-responsive.min.css" />
<script src="${ctx}/js/jquery-1.10.2.min.js"></script>
<script src="${ctx}/js/common.js"></script>

<style type="text/css">
.wh100 {
	width: 100px;
}
.container-fluid{
	padding:0;
}
</style>
