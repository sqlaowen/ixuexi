<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<title>pdf view</title>
<script type="text/javascript" src="${ctx }/resource/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/Flexpaper/flexpaper_flash.js"></script>
<script type="text/javascript">
$(function(){
	var fp = new FlexPaperViewer('${ctx }/resource/Flexpaper/FlexPaperViewer',
	'viewerPlaceHolder', {
		config : {
			SwfFile : '${ctx }/resource/Flexpaper/test.swf',
			EncodeURI:true,
			Scale : 0.6,
			ZoomTransition : 'easeOut',
			ZoomTime : 0.5,
			ZoomInterval : 0.2,
			FitPageOnLoad : true,
			FitWidthOnLoad : true,
			FullScreenAsMaxWindow : false,
			ProgressiveLoading : true,
			MinZoomSize : 0.2,
			MaxZoomSize : 5,
			SearchMatchAll : false,
			InitViewMode : 'SinglePage',
			ViewModeToolsVisible : true,
			ZoomToolsVisible : true,
			NavToolsVisible : true,
			CursorToolsVisible : true,
			SearchToolsVisible : true,
			localeChain : 'zh_CN'
		}
	});
});
	</script>
</head>
<body>
<div>
	<a id="viewerPlaceHolder" style="width: 1080px;height:500px; margin:auto auto; display: block">
	</a>
</div>
</body>
</html>



















