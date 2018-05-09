<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jsp" %>
<title>hello world</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div>
					<table id="tableRole" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>角色</th>
								<th>编码</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="text-right">
						<ul class="pagination" id="pager" style="margin: 0 0;"></ul>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<!-- 角色 -->
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
	
		$(function(){
			$('#tableRole tbody').on('click','tr',function(){
				$(this).toggleClass('success');
			});
			
			fnSearch(1);
		});
		
		function fnSearch(currentPage){
			$('#tableRole').initTable({
				url : '${ctx }/role/list'
				,pageNum : currentPage
				,callback : callback
				,pageSize:100
			});
		}
		
		function callback(data){
			$('#tableRole > tbody').empty();
			if(data.length==0){
				var tr="<tr><td colspan='"+$('#tableRole').find('thead').find('tr').find('th').length+"'>暂无数据...</td></tr>";
				$('#tableRole > tbody').append(tr);
				return;
			}
			$.each(data,function(i,v){
				var tr='<tr id="'+v.roleId+'">'
					+'<td>'+v.roleName+'</td>'
					+'<td>'+v.roleCode+'</td>'
					+'<td>'+null2Val(v.roleNote,'')+'</td>'
					
					+'</tr>';
				$('#tableRole > tbody').append(tr);			
			});
			
			//初始化角色选中状态
			$('#tableRole tbody tr').removeClass('success');
			$.post('${ctx}/role/userId',{userId:'${param.userId}'},function(rev){
				$.each(rev,function(i,v){
					$('#tableRole tbody tr').each(function(j,k){
						console.log(k);
						if($(k).attr('id')==v.roleId){
							$(k).addClass('success');
						}
					});
				});
			},'json');
		}
		
		//保存用戶ref角色
		function fnSave(dlg){
			var roleIds='';
			$('#tableRole >tbody > tr').each(function(j,k){
				if($(k).hasClass('success')){
					roleIds+=$(k).attr('id')+',';
				}
			});
			//console.log(roleIds);
			if(!'${param.userId}'){
				var d=top.dialog({content:'未知用户...'}).show();
				setTimeout(function(){d.close().remove();}, 500);
				return;
			}
			if(''==roleIds){
				var d=top.dialog({content:'请选择角色...'}).show();
				setTimeout(function(){d.close().remove();}, 500);
				return;
			}
			$.post('${ctx}/role/userRole',{userId:'${param.userId}',roleIds:roleIds},function(rev){
				console.log(rev);
				var d=parent.dialog.getCurrent();
				d.close().remove();
			},'json');
			return false;
		}
	
	</script>
	
</body>
</html>