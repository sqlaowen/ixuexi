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
					<input type="hidden" id="menuId" name="menuId" value="${model.menuId }" />
					<input type="hidden" id="wxId" name="wxId" value="${wxId }" />
					<input type="hidden" id="menuPid" name="menuPid" value="${model.menuPid }" />
					<table class="table table-bordered table-hover">
						<tr>
							<td class="wh100">菜单名:</td>
							<td>
								<input type="text" id="menuName" name="menuName" value="${model.menuName }" />
							</td>
							<td class="wh100">位置:</td>
							<td>
								<select name="menuSeq" id="menuSeq">
									<option value="">--请选择--</option>
									<optgroup label="位置1">
										<option value="1">一级菜单1</option>
										<option value="11">子菜单1*1</option>
										<option value="12">子菜单1*2</option>
										<option value="13">子菜单1*3</option>
										<option value="14">子菜单1*4</option>
										<option value="15">子菜单1*5</option>
									</optgroup>
									<optgroup label="位置2">
										<option value="2">一级菜单2</option>
										<option value="21">子菜单2*1</option>
										<option value="22">子菜单2*2</option>
										<option value="23">子菜单2*3</option>
										<option value="24">子菜单2*4</option>
										<option value="25">子菜单2*5</option>
									</optgroup>
									<optgroup label="位置3">
										<option value="3">一级菜单3</option>
										<option value="31">子菜单3*1</option>
										<option value="32">子菜单3*2</option>
										<option value="33">子菜单3*3</option>
										<option value="34">子菜单3*4</option>
										<option value="35">子菜单3*5</option>
									</optgroup>
								</select>
							</td>
						</tr>
						<tr>
							<td>动作类型:</td>
							<td>
								<select id="menuType" name="menuType">
									<option value="">--请选择--</option>
									<option value="VIEW">VIEW</option>
									<option value="CLICK">CLICK</option>
								</select>
							</td>
							<td></td>
							<td>
							</td>
						</tr>
					</table>
				</div>
				<div id="xx"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$('#menuType').on('change',function(){
				var $self=$(this);
				var n1=$self.parent().next();
				n1.text('');
				var n2=$self.parent().next().next();
				n2.html('');
				switch(this.value){
					case 'VIEW':
						n1.text('url:');
						n2.html('<input type="text" id="menuUrl" name="menuUrl" value="${model.menuUrl }" />');
						break;
					case 'CLICK':
						n1.text('回复消息:');
						var input=fnGetMsg();
						n2.html(input);
						break;
					default:
						n1.text('');
						n2.html('');
						break;
				}
			});
						
			 //修改初始化
			if ('${model.menuId}') {
				$('#menuSeq').val('${model.menuSeq}');
				$('#menuType').val('${model.menuType}').change();
				switch ('${model.menuType}') {
				case 'VIEW':
					$('#menuUrl').val('${model.menuUrl}');
					break;
				case 'CLICK':
					$('#menuKey').val('${model.menuKey}');
					break;
				default:
					break;
				}
			}
		});
		var dialog = parent.dialog.getCurrent();
		dialog.button([{
			value : '取消',
			callback : function() {
			}
		}, {
			value : '提交',
			callback : fnSave
		}]);
		function fnSave() {
			var $this = this;
			//验证
			var errMsg=[];
			if($('#menuName').val().trim()==''){
				errMsg.push('请填写[菜单名]...');
			}
			if($('#menuSeq').val()==''){
				errMsg.push('请选择[位置]...');
			} else {
				if(',1,2,3,'.indexOf(','+$('#menuSeq').val()+',')<0 && $('#menuType').val()==''){
					errMsg.push('请选择[动作类型]...');
				}
			}
			if($('#menuUrl').length>0){
				if($('#menuUrl').val().trim()==''){
					errMsg.push('请填写[url]...');
				}
			}
			if($('#menuKey').length>0){
				if($('#menuKey').val().trim()==''){
					errMsg.push('请选择[回复消息]...');
				}
			}
			if(errMsg !=''){
				var d = parent.dialog({
					title : '验证不通过...',
					content : errMsg.join('<br>')
				}).show();
				setTimeout(function(){
					d.close().remove();
				},2000);
				return false;
			}
			//提交
			$.post('${ctx}/wx/menu/save', $('#form').getFormsValue(), function(rev) {
				console.log(rev);
				if ('fail' == rev.code) {
					var d = parent.dialog({
						title : '额。。。囧，操作失败，请稍后再试...',
						width : 300,
						content : rev.msg
					}).show();
				} else {
					setTimeout(function() {
						$this.close('success').remove();
					}, 200);
				}
			}, 'json');
			return false;
		}

		//
		function fnGetMsg() {
			var input = '';
			input += '<select id="menuKey" name="menuKey">';
			input += '<option value="">--请选择---</option>';
			$.ajax({
				url : '${ctx}/wx/message/getautorespagelist',
				type : 'POST',
				data : {
					wxId : '${wxId}',
					msgType : 3,
					pageNum : 1,
					pageSize : 1000
				},
				dataType : 'json',
				async : false,
				success : function(rev) {
					//console.log(rev);
					$.each(rev.list, function(i, v) {
						input += '<option value="'+v.resId+'">' + v.resName
								+ '</option>';
					});
				},
				error : function() {
					console.log('error');
				}
			});
			input += '</select>';
			//console.log(input);
			return input;
		}
	</script>
</body>
</html>