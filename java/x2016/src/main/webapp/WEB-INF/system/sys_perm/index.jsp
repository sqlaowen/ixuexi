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
			<div class="span3">
				<div>
					<table id="menu" class="table tree table-bordered table-hover">
						<thead>
							<tr>
								<th>菜单列表</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
			<div class="span9">
				<div>
					<table class="table table-bordered table-hover">
						<tr>
							<td colspan="6" style="text-align: right;">
							<shiro:hasPermission name="sperm:add">
								<a href="javascript:;" class="btn btn-default" onclick="fnAdd('add')">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="sperm:edit">
								<a href="javascript:;" class="btn btn-default" onclick="fnAdd('edit')">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="sperm:delete">
								<a href="javascript:;" class="btn btn-default" onclick="fnDel()">删除</a>
							</shiro:hasPermission>
							</td>
						</tr>
						
					</table>
				</div>
				<div>
					<table id="perm" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>权限名</th>
								<th>编码</th>
								<th>排序</th>
								<th>描述</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 权限 -->
	<script type="text/javascript">
	//添加/修改
	function fnAdd(str){
		var url='${ctx}/perm/add';
		if('edit'==str){
			var id='';
			$('#perm >tbody > tr').each(function(j,k){
				if($(k).hasClass('success')){
					id=$(k).attr('id');
				}
			});
			if(''==id){
				var d=top.dialog({content:'请选择权限...'}).show();
				setTimeout(function(){d.close().remove();}, 500);
				return;
			}
			url+='?id='+id;
		}
		if('add'==str){
			var pid='';
			$('#menu >tbody> tr').each(function(i,v){
				if($(v).hasClass('success'))
					pid=$(v).attr('id');
			});
			if(''==pid){
				var d=top.dialog({content:'请选择菜单...'}).show();
				setTimeout(function(){d.close().remove();}, 500);
				return;
			}
			url+='?pid='+pid;
		}
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
	function fnDel(){
		var id='';
		$('#perm >tbody > tr').each(function(j,k){
			if($(k).hasClass('success')){
				id=$(k).attr('id');
			}
		});
		if(''==id){
			var d=top.dialog({content:'请选择权限...'}).show();
			setTimeout(function(){d.close().remove();}, 500);
			return;
		}
		$.post('${ctx}/perm/del',{id:id},function(rev){
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
	//根据菜单取权限
	function fnSearch(){
		$('#menu >tbody > tr').each(function(j,k){
			if($(k).hasClass('success')){
				$(k).click();
			}
		});
	}
	</script>
	
	<!-- 菜单/菜单->权限 -->
	<script type="text/javascript">
		$(function(){
			$('#perm tbody').on('click','tr',function(){
				$(this).siblings().removeClass('success');
				$(this).addClass('success');
			});
			
			//根据菜单取权限
			$('#menu tbody').on('click','tr',function(){
				//console.log(this);
				$(this).siblings().removeClass('success');
				$(this).addClass('success');
				
				$('#perm tbody').empty();
				$.post('${ctx}/perm/auth/'+$(this).attr('id'),{},function(rev){
					$.each(rev,function(i,v){
						var tr='<tr id="'+v.permId+'">';
							tr+='<td>'+v.permName+'</td>';
							tr+='<td>'+v.permCode+'</td>';
							tr+='<td>'+v.permSeq+'</td>';
							tr+='<td>'+v.permNote+'</td>';
							tr+='</tr>';
						$('#perm tbody').append(tr);
					});
				});
			});
			//取菜单
			$.post('${ctx}/perm/menu',{},function(rev){
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
					tr+='</tr>';
				$('#menu tbody').append(tr);
				if(v.childs.length>0)
					fnDigui(v.childs);
			});
		}
		
	</script>
</body>
</html>