<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx }/resource/bootstrap/css/bootstrap.min.css" />
<script src="${ctx}/resource/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/webuploader/webuploader.css">
<script type="text/javascript" src="${ctx }/resource/webuploader/webuploader.js"></script>

</head>
<body>
	<div>
		<form id="form1">
			<table class="table table-striped table-bordered table-hover">
				<tr>
					<td style="width:80px;">jar包：</td>
					<td>
						<div id="uploader" class="wu-example">
							<div class="btns">
								<div id="picker">选择文件</div>
							</div>
							<!--用来存放文件信息-->
							<div id="thelist" class="uploader-list">
								<div>
									<h4>&nbsp;</h4>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>任务名：</td>
					<td>
						<input id="name" name="name" type="text" />
					</td>
				</tr>
				<tr>
					<td>任务组：</td>
					<td>
						<input id="group" name="group" type="text" />
					</td>
				</tr>
				<tr>
					<td>表达式：</td>
					<td>
						<input id="cronExpression" name="cronExpression" type="text" />
					</td>
				</tr>
				<tr>
					<td>任务类：</td>
					<td>
						<input id="className" name="className" type="text" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		
		$(function() {
			var uploader = WebUploader.create({
			    // swf文件路径
			    swf: '${ctx}/resource/webuploader/Uploader.swf',
			    // 文件接收服务端。
			    server:'${ctx}/upfile',
			    // 选择文件的按钮。可选。
			    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
			    pick: '#picker'
			 	// 选完文件后，是否自动上传。
			    ,auto: true
			    // 上传数量
			    //,fileNumLimit:1
			 	// 只允许选择jar文件。
			     ,accept: {
			        title: 'jar',
			        extensions: 'jar',
			        mimeTypes: 'application/java-archive'
			    } 
			});
			$('#picker >div.webuploader-pick').removeClass('webuploader-pick').addClass('btn btn-default');
			// 当有文件被添加进队列的时候
			uploader.on( 'fileQueued', function( file ) {
				console.log(file);
				$list=$('#thelist');
				$list.empty();
			    $list.append( '<div id="' + file.id + '" class="item">' +
			        '<h4 class="info">' + file.name + '</h4>' +
			    '</div>' );
			});
			uploader.on( 'uploadSuccess', function( file,res ) {
				
				var d=parent.dialog({content:res._raw}).show();
				setTimeout(function () {
					d.close('reload').remove();
			    }, 2000);
			});
			// 文件上传失败，显示上传出错。
			uploader.on( 'uploadError', function( file ) {
				var d=parent.dialog({content:'额。。。囧，文件上传失败，请稍后再试...'}).show();
				setTimeout(function(){d.close().remove();},500);
			});
			
			
			var dialog=parent.dialog.getCurrent();
			//console.log(dialog);
			dialog.title('添加任务');
			
			dialog.button([
						{
							value : '确定',
							callback : function() {
								 var self=this;
								 $.ajax({
									 type:'POST'
									 ,url:'${ctx}/add'
									 ,data:$('#form1').serialize()
									 ,dataType:'text'
									 ,success:function(data){
										 if(data=='success'){
												self.title('添加成功...');
												setTimeout(function () {
													self.close('reload').remove();
											    }, 200);
											}else{
												var d=parent.dialog({content:'额。。。囧，执行命令失败，请稍后再试...'}).show();
												setTimeout(function(){d.close().remove();},500);
											}
									 }
									 ,error:function(){
										 var d=parent.dialog({content:'额。。。囧，执行命令失败，请稍后再试...'}).show();
										 setTimeout(function(){d.close().remove();},500);
									 }
								 });
								return false; 
							},
							focus : true
						}, {
							value : '取消',
							callback : function() {
								
							}
						} ]);
			});
	</script>
</body>
</html>