<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1" />
<link rel="stylesheet" type="text/css" href="${ctx }/resource/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/resource/ztree/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/resource/artDialog/ui-dialog.css" />
<script src="${ctx}/resource/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/resource/artDialog/dialog-plus-min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/tabPanel/skin/default/style.css" />
<script src="${ctx}/resource/tabPanel/tabPanel.js"></script>

<script type="text/javascript">
var setting = {
	view: {
		showLine: true,
		selectedMulti: false,
		dblClickExpand: false
	}
	,data: {
		simpleData: {
			enable: true
		}
	}
};
var tabPanel = null;
    $(document).ready(function () {
    	tabPanel = new TabPanel($('#tabArea'), {
            max: 3,
            defaultIcon: '${ctx}/resource/tabPanel/skin/default/table_cell.png',
            showClose: true,
            owIndex: 0
            ,onAdd: function (id, hd, bd) {
                //解决IE6首次不显示的问题
                if (!$.support.opacity && !$.support.style && window.XMLHttpRequest==undefined) {
                    bd[0].contentWindow.location.reload();
                }
            }
        });
    	
       $.post('${ctx}/perm/left',{},function(rev){
    	   //console.log(rev);
       	var nodes=[];
       	var node={};
       	$.each(rev,function(i,v){
       		node={};
       		if(i==0)
       			node.open='true';
       		node.id=v.permId;
       		node.pId=v.permPid;
       		node.name=v.permName;
       		if(v.permUrl!=null && v.permUrl!=''){
       			//node.url='${ctx}'+v.permUrl;
       			//node.url=v.permUrl;
       			node.url='javascript:tabMdi("'+v.permName+'","","'+v.permUrl+'")';
       			//node.target='frmMain';
       			node.target='_self';
       		}
       		nodes.push(node);
       	});
       	$.fn.zTree.init($("#ztree"), setting, nodes);
       },'json');
    });
    
  	//功能:全局的导航条刷新
    function tabMdi(title, target, url) {
        //页面跳转
        if (target != '_blank' && target != 'top') {
            tabPanel.showTab({title:title,url:url,icon:(this.panelIcon || '')}, false);
        }
    }
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:5px">
		hello world !<a href="${ctx }/logout">登出</a>
	</div>
	<div data-options="region:'west',split:true,title:'菜单导航'" style="width:160px;padding:3px;overflow-x:hidden;">
		<ul id="ztree" class="ztree"></ul>
	</div>
	<div id="tabArea" data-options="region:'center'" style="overflow: hidden;">
		<!-- <iframe name="frmMain" style="width: 100%; height: 100%;" src="" scrolling="auto" noresize="noresize" frameborder="0"></iframe> -->
	</div>
</body>

</html>