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


</head>
<body>
	<div>
		<form id="form1">
			<table class="table table-striped table-bordered table-hover">
				
				<tr>
					<td>表达式：</td>
					<td>
						<input id="cronExpression" name="cronExpression" type="text" />
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	<script type="text/javascript">
		
		$(function() {
			var dialog=top.dialog.getCurrent();
			//console.log(dialog);
			dialog.title('修改cron表达式');
			dialog.button([
						{
							value : '确定',
							callback : function() {
								var self=this;
								$.ajax({
									type:'POST'
									,url:'${ctx}/${jobName}/${jobGroup}/update'
									,data:{cronExpression:$('#cronExpression').val()}
									,dataType:'text'
									,success:function(data){
										if(data=='success'){
											self.title('修改成功...');
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