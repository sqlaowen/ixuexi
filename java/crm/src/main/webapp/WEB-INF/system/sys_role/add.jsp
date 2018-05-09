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
					<input type="hidden" id="roleId" name="roleId" value="${model.roleId }" />
					<input type="hidden" id="roleStatus" name="roleStatus" value="0" />
					<table class="table table-bordered table-hover">
						<tr>
							<td class="wh100">角色：</td>
							<td>
								<input type="text" id="roleName" name="roleName" value="${model.roleName }" />
							</td>
							<td class="wh100">编码：</td>
							<td>
								<input type="text" id="roleCode" name="roleCode" value="${model.roleCode }" />
							</td>
						</tr>
						<tr>
							<td>备注：</td>
							<td>
								<input type="text" id="roleNote" name="roleNote" value="${model.roleNote }" />
							</td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
				
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
			$.post('${ctx}/role/save',$('#form').getFormsValue(),function(rev){
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