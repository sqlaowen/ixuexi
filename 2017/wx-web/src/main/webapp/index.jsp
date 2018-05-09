<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="chrome=1" />
	<link rel="stylesheet" type="text/css" href="${ctx }/js/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/js/ztree/zTreeStyle.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/js/artDialog/ui-dialog.css" />
	<script src="${ctx}/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/easyui/jquery.easyui.min.js"></script>
	<script src="${ctx}/js/artDialog/dialog-plus-min.js"></script>

	<title>诸葛修车网</title>
	<script type="text/javascript">
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		var zNodes =[
			{ id:1, pId:0, name:"基础服务", open:true},
			{ id:11, pId:1, name:"自定义回复", open:true},
			{ id:112, pId:11, name:"关注时回复", url:"${ctx}/wx/message/index?msgtype=1&wxid=100", target:"frmMain"},
			{ id:113, pId:11, name:"默认回复", url:"${ctx}/wx/message/index?msgtype=2&wxid=100", target:"frmMain"},
			{ id:112, pId:11, name:"菜单回复", url:"${ctx}/wx/message/index?msgtype=3&wxid=100", target:"frmMain"},
			{ id:12, pId:1, name:"自定义菜单", url:"${ctx}/wx/menu/index", target:"frmMain"}
		];
		$(document).ready(function(){
			$.fn.zTree.init($("#ztree"), setting, zNodes);
		});
	</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:5px">
	hello world ! 
</div>
<div data-options="region:'west',split:true,title:'菜单导航'" style="width:180px;padding:3px;overflow-x:hidden;">
	<ul id="ztree" class="ztree"></ul>
</div>
<div data-options="region:'center'" style="overflow: hidden;">
	<iframe name="frmMain" style="width: 100%; height: 100%;" src="javascript:;" scrolling="auto" noresize="noresize" frameborder="0"></iframe>
</div>
</body>
</html>
