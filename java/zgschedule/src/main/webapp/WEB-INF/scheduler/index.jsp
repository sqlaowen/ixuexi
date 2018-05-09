<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx }/resource/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx }/resource/artDialog/ui-dialog.css" />
<script src="${ctx}/resource/jquery-1.10.2.min.js"></script>
<script src="${ctx}/resource/artDialog/dialog-plus-min.js"></script>

</head>
<body>
	<div class="panel panel-default">
	   <div class="panel-body text-right">
	      	<a href="javascript:;" class="btn btn-default" onclick="add()">添加</a>
	   </div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h5>计划中的任务</h5>
		</div>
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th>任务名</th>
				<th>任务组</th>
				<th>cron表达式</th>
				<th>状态</th>
				<th>类名</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${allJob }" step="1" var="job"
				varStatus="jobStatus">
				<tr>
					<td>${job.name }</td>
					<td>${job.group }</td>
					<td>${job.cronExpression }</td>
					<td>${job.status }</td>
					<td>${job.className }</td>
					<td>${job.description }</td>
					<td>
						<a href="javascript:;" onclick="execJob('${job.name }','${job.group }','stop')">暂停</a>
						<a href="javascript:;" onclick="edit('${job.name }','${job.group }','update')">修改cron表达示</a>
						<a href="javascript:;" onclick="execJob('${job.name }','${job.group }','startNow')">立即运行一次</a>
						<a href="javascript:;" onclick="execJob('${job.name }','${job.group }','resume')">恢复</a>
						<a href="javascript:;" onclick="execJob('${job.name }','${job.group }','delete')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h5>运行中的任务</h5>
		</div>
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th>任务名</th>
				<th>任务组</th>
				<th>cron表达式</th>
				<th>状态</th>
				<th>类名</th>
				<th>备注</th>
			</tr>
			<c:forEach items="${runJob }" step="1" var="job"
				varStatus="jobStatus">
				<tr>
					<td>${job.name }</td>
					<td>${job.group }</td>
					<td>${job.cronExpression }</td>
					<td>${job.status }</td>
					<td>${job.className }</td>
					<td>${job.description }</td>
				</tr>
			</c:forEach>
		</table>
</div>
</body>
<script type="text/javascript">
	//添加
	function add(){
		dialog({
			id:'addJob'
			,url:'${ctx }/add'
			,width:350
			,onclose: function () {
				if(this.returnValue)
					location.reload();				
			}
			,resize:true
		}).show();
	}
	//修改
	function edit(jobName, jobGroup, exec){
		var url='${ctx }/' + jobName + '/' + jobGroup + '/' + exec;
		dialog({
			id:'editCron'
			,url:url
			,width:400
			,onclose: function () {
				if(this.returnValue)
					location.reload();
			}
		}).show();
	}
	//执行
	function execJob(jobName, jobGroup, exec) {
		var url='${ctx }/' + jobName + '/' + jobGroup + '/' + exec;
		$.post(url, {}, function(data) {			
			if(data=='success'){
				var d=dialog({content:'执行成功...'}).show();
				setTimeout(function(){
					d.close().remove();
					location.reload();
				},200);
				
			} else{
				var d=dialog({content:'额。。。囧，执行命令失败，请稍后再试...'}).show();
				setTimeout(function(){d.close().remove();},500);
			}
		}, 'text');
	}
</script>
</html>