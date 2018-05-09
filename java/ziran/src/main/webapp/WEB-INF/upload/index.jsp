<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>upload</title>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resource/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/webuploader/webuploader.css">
<script type="text/javascript" src="${ctx }/resource/webuploader/webuploader.js"></script>
</head>
<body>
	<h3>请求服务时间：${reqTime}</h3>
	${result }
	<br />${param.result }
	<form action="${pageContext.request.contextPath }/upfile" enctype="multipart/form-data" method="post">
		<div>
			文件1：<input type="file" name="file1" />
		</div>
		<div>
			文件2：<input type="file" name="file2" />
		</div>
		<div>
			文件3：<input type="file" name="file3" />
		</div>
		<div>
			<input type="submit" value="上传" />
		</div>
	</form>

	<h3>webupload</h3>
	<div id="uploader" class="wu-example">
		<!--用来存放文件信息-->
		<div id="thelist" class="uploader-list"></div>
		<div class="btns">
			<div id="picker">选择文件</div>
			<button id="ctlBtn" onclick="uploader.upload()" class="btn btn-default">开始上传</button>
		</div>
	</div>
	<script type="text/javascript">
	var uploader = WebUploader.create({
	    // swf文件路径
	    swf: '${ctx}/resource/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server:  '${ctx}/upfile',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#picker',
	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false,
	 // 选完文件后，是否自动上传。
	    auto: true,
	 // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    }
	});
	
	 // 当有文件被添加进队列的时候
	uploader.on( 'fileQueued', function( file ) {
		$list=$('#thelist');
	    $list.append( '<div id="' + file.id + '" class="item">' +
	        '<h4 class="info">' + file.name + '</h4>' +
	        '<p class="state">等待上传...</p>' +
	    '</div>' );
	});
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress .progress-bar');

	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<div class="progress progress-striped active">' +
	          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
	          '</div>' +
	        '</div>').appendTo( $li ).find('.progress-bar');
	    }

	    $li.find('p.state').text('上传中');

	    $percent.css( 'width', percentage * 100 + '%' );
	});
	uploader.on( 'uploadSuccess', function( file ) {
	    $( '#'+file.id ).find('p.state').text('已上传');
	});

	uploader.on( 'uploadError', function( file ) {
	    $( '#'+file.id ).find('p.state').text('上传出错');
	});

	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').fadeOut();
	}); 
	</script>
</body>
</html>