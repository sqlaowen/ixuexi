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
					<input type="hidden" id="userId" name="userId" value="${model.userId }" />
					<input type="hidden" id="userStatus" name="userStatus" value="0" />
					<table class="table table-bordered table-hover">
						<tr>
							<td class="wh100">用户名：</td>
							<td>
								<input type="text" id="userName" name="userName" value="${model.userName }" />
							</td>
							<td class="wh100">账号：</td>
							<td>
								<input type="text" id="userAccount" name="userAccount" value="${model.userAccount }" />
							</td>
						</tr>
						<tr>
							<td>密码：</td>
							<td>
								<input type="text" id="userPwd" name="userPwd" value="${model.userPwd }" />
							</td>
							<td>性别：</td>
							<td>
								<select id="userSex" name="userSex">
									<option value="">--请选择--</option>
									<option value="0">男</option>
									<option value="1">女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>电话：</td>
							<td>
								<input type="text" id="userPhone" name="userPhone" value="${model.userPhone }" />
							</td>
							<td>备注：</td>
							<td>
								<input type="text" id="userNote" name="userNote" value="${model.userNote }" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		$(function(){
			if(''!='${model.userSex}')
				$('#userSex').val('${model.userSex}');
		})
				
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
			$.post('${ctx}/user/save',$('#form').getFormsValue(),function(rev){
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