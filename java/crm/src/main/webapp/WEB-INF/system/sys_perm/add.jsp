<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/headerEdit.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div id="form">
					<input type="hidden" id="permId" name="permId" value="${model.permId }" />
					<input type="hidden" id="permPid" name="permPid" value="${model.permPid }" />
					<input type="hidden" id="permType" name="permType" value="1" />
					<input type="hidden" id="permStatus" name="permStatus" value="0" />
					<table class="table table-bordered table-hover">
						<tr>
							<td class="wh100">权限名：</td>
							<td>
								<input type="text" id="permName" name="permName" value="${model.permName }" />
							</td>
							<td class="wh100">编码：</td>
							<td>
								<input type="text" id="permCode" name="permCode" value="${model.permCode }" />
							</td>
						</tr>
						<tr>
							<td>排序:</td>
							<td>
								<input type="text" id="permSeq" name="permSeq" value="${model.permSeq }" />
							</td>
							<td>备注：</td>
							<td>
								<input type="text" id="permNote" name="permNote" value="${model.permNote }" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			if(''==$('#permPid').val()){
				$('#permPid').val('${param.pid}');
			}
		});
		var dialog=parent.dialog.getCurrent();
		dialog.button([{
				value:'取消'
				,callback:function(){}
			},{
				value:'提交'
				,callback:fnSave
			} 
		]);
		function fnSave(){
			var $this=this;
			if(''==$('#permPid').val()){
				var d = parent.dialog({
					title : '额。。。囧...'
					,content:'非法请求,请检查页面来源...'
				}).show();
				return false;
			}
			$.post('${ctx}/perm/save',$('#form').getFormsValue(),function(rev){
				if('fail'==rev.code){
					var d = parent.dialog({
						title : '额。。。囧，操作失败，请稍后再试...'
						,content:rev.msg
					}).show();
				} else{
					setTimeout(function() {
						 $this.close('success').remove();
					}, 200);
				}
			},'json');
			return false;
		}
	</script>
</body>
</html>