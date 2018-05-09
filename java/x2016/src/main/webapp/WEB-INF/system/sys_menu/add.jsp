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
					<input type="hidden" id="permType" name="permType" value="0" />
					<input type="hidden" id="permStatus" name="permStatus" value="0" />
					<table class="table table-bordered table-hover">
						<tr>
							<td class="wh100">菜单名：</td>
							<td>
								<input type="text" id="permName" name="permName" value="${model.permName }" />
							</td>
							<td class="wh100">url：</td>
							<td>
								<input type="text" id="permUrl" name="permUrl" value="${model.permUrl }" />
							</td>
						</tr>
						<tr>
							<td>父菜单：</td>
							<td>
								<select id="permPid" name="permPid">
									<option value="0">--请选择--</option>
								</select>
							</td>
							<td>排序：</td>
							<td>
								<input type="text" id="permSeq" name="permSeq" value="${model.permSeq }" />
							</td>
						</tr>
						<tr>
							<td>备注：</td>
							<td>
								<input type="text" id="permNote" name="permNote" value="${model.permNote }" />
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
		$(function(){
			$.ajax({
				url:'${ctx}/perm/menu'
				,type:'POST'
				,data:{}
				,dataType:'json'
				,async:false
				,success:function(rev){
					fnDigui(rev,0);
				}
				,error:function(xhr,ts,e){}
			});
			if('${model.permId}'){
				$('#permPid').val('${model.permPid}');
			}
		});
		function fnDigui(data,k){
			$.each(data,function(i,v){
				var option ='<option value="'+v.permId+'">'+fnPadNbsp(v.permName,4*k)+'</option>';
				$('#permPid').append(option);
				if(v.childs.length>0)
					fnDigui(v.childs,k+1);
			});
		}
		
		function fnPadNbsp(str,len){
			for(i=0;i<len;i++){
				str='&nbsp;'+str;
			}
			return str;
		}
		
		var dialog=parent.dialog.getCurrent();
		dialog.button([{
				value:'取消'
				,callback:function(){}
			}
		
			<shiro:hasPermission name="smenu:save">
			,{
				value:'提交'
				,callback:fnSave
				} 
			</shiro:hasPermission>
			
		]);
		function fnSave(){
			var $this=this;
			$.post('${ctx}/menu/save',$('#form').getFormsValue(),function(rev){
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