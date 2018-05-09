<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<title>Insert title here</title>
<script type="text/javascript" src="${ctx }/resource/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/ue/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx }/resource/ue/ueditor.all.js"></script>
<script type="text/javascript">
	var ue=null;
	$(function(){
		ue=UE.getEditor('ue'
				//focus时自动清空初始化时的内容
	            ,{autoClearinitialContent:false
	            //关闭字数统计
	            ,wordCount:false
	            //服务器统一请求接口路径
	            ,serverUrl:'${ctx}/ue/init'
	            ,lang:'zh-cn'
	            //关闭elementPath
	            ,elementPathEnabled:false
	            //默认的编辑区域高度
	            ,initialFrameHeight:300});
	});
</script>
</head>
<body>
<script id="ue" name="ue" type="text/plain"></script>
</body>
</html>