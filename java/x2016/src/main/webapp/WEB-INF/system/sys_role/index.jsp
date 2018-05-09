<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/treegrid/css/jquery.treegrid.css">
<script src="${ctx}/resource/treegrid/js/jquery.treegrid.js"></script>
<script src="${ctx}/resource/treegrid/js/jquery.treegrid.bootstrap2.js"></script>

<title>hello world</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span7">
				<div>
					<table class="table table-bordered table-hover">
						<tr>
							<td colspan="6" style="text-align: right;">
							<shiro:hasPermission name="srole:add">
								<a href="javascript:;" class="btn btn-default" onclick="fnAdd()">添加</a>
							</shiro:hasPermission>
							</td>
						</tr>
						
					</table>
				</div>
				<div>
					<table id="tableRole" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>角色</th>
								<th>编码</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="text-right">
						<ul class="pagination" id="pager" style="margin: 0 0;"></ul>
					</div>
				</div>
			</div>
			<div class="span5">
				<div>
					<table class="table table-bordered table-hover">
						<tr>
							<td colspan="6" style="text-align: right;">
								<a href="javascript:;" class="btn btn-default" onclick="fnSaveGrant()">保存授权</a>
							</td>
						</tr>
						
					</table>
				</div>
				<div>
					<table id="resource" class="table tree table-bordered table-hover">
						<thead>
							<tr>
								<th style="width:60%;">资源</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- 角色 -->
	<script type="text/javascript">
		$(function(){
			fnSearch(1);
		});
		
		function fnSearch(currentPage){
			$('#tableRole').initTable({
				url : '${ctx }/role/list'
				,pageNum : currentPage
				,callback : callback
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
					+'<td>'
					<shiro:hasPermission name="srole:edit">
						+' <a href="javascript:;" class="btn btn-default" onclick="fnAdd(\''+v.roleId+'\')">修改</a>'
					</shiro:hasPermission>
					<shiro:hasPermission name="srole:delete">
						+' <a href="javascript:;" class="btn btn-default" onclick="fnDel(\''+v.roleId+'\')">删除</a>'
					</shiro:hasPermission>
					<shiro:hasPermission name="srole:perm">
						+' <a href="javascript:;" class="btn btn-default" onclick="fnPerm(\''+v.roleId+'\',this)">权限</a>'
					</shiro:hasPermission>
					+'</td>'
					+'</tr>';
				$('#tableRole > tbody').append(tr);			
			});
		}
	
		//添加/修改
		function fnAdd(id){
			var url='${ctx}/role/add';
			if(id)
				url+='?id='+id;
			top.dialog({
				id:'fnAdd'
				,title:'添加/修改'
				,width:668
				,url:url
				,onclose: function () {
					if(this.returnValue){
						fnSearch();
					}	
				}
			}).show();
		}
		//删除
		function fnDel(id){
			$.post('${ctx}/role/del',{id:id},function(rev){
				if('fail'==rev.code){
					var d = parent.dialog({
						title : '额。。。囧，操作失败，请稍后再试...'
						,content:rev.msg
					}).show();
				} else{
					fnSearch();
				}
			},'json');
		}
		
	</script>
	<!-- 权限 -->
	<script type="text/javascript">
		$(function(){
			$('#resource tbody').on('click','tr',function(){
				//console.log(this);
				$(this).toggleClass('success');
			});
			
			$.post('${ctx}/perm/list',{},function(rev){
				//console.log(rev);
				fnDigui(rev);
				$('.tree').treegrid();
			},'json');
		});
		
		function fnDigui(data){
			$.each(data,function(i,v){
				var tr='<tr class="treegrid-'+v.permId;
					if(0!=v.permPid)
						tr+=' treegrid-parent-'+v.permPid;
					tr+='"';
					tr+=' id='+v.permId;
					tr+='>';
					tr+='<td>'+v.permName+'</td>';
					tr+='<td>'+null2Val(v.permNote,'')+'</td>';
					tr+='</tr>';
				$('#resource tbody').append(tr);
				if(v.childs.length>0)
					fnDigui(v.childs);
			});
		}
		
		//权限
		function fnPerm(id,objLink){
			var $tr=$(objLink).parent().parent();
			$tr.siblings().removeClass('success');
			$tr.addClass('success');
			
			$('#resource tbody tr').removeClass('success');
			$.post('${ctx}/perm/role/',{roleId:id},function(rev){
				//console.log(rev);
				$.each(rev,function(i,v){
					$('#resource tbody tr').each(function(j,k){
						if($(k).attr('id')==v.permId)
							$(k).addClass('success');
					});
				});
			},'json')
		}
		//保存授权
		function fnSaveGrant(){
			var roleId='';
			$('#tableRole >tbody> tr').each(function(i,v){
				if($(v).hasClass('success'))
					roleId=$(v).attr('id');
			});
			var permIds='';
			$('#resource >tbody > tr').each(function(j,k){
				if($(k).hasClass('success')){
					permIds+=$(k).attr('id')+',';
				}
			});
			//console.log(roleId);
			//console.log(permIds);
			if(''==roleId){
				var d=top.dialog({content:'请选择角色...'}).show();
				setTimeout(function(){d.close().remove();}, 500);
				return;
			}
			if(''==permIds){
				var d=top.dialog({content:'请选择权限...'}).show();
				setTimeout(function(){d.close().remove();}, 500);
				return;
			}
			$.post('${ctx}/role/grant',{roleId:roleId,permIds:permIds},function(rev){
				console.log(rev);
			},'json');
		}
	</script>
</body>
</html>