<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/js/treegrid/css/jquery.treegrid.css">
<script src="${ctx}/js/treegrid/js/jquery.treegrid.js"></script>
<script src="${ctx}/js/treegrid/js/jquery.treegrid.bootstrap2.js"></script>

<title>index</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			
			<div class="span12">
				<div id="form">
					<table class="table table-bordered table-hover">
						<tr>
							<td colspan="6" style="text-align: right;">
								<a href="javascript:;" class="btn btn-default" onclick="fnAdd()">添加</a>
								<a href="javascript:;" onclick="fnWXSync()">同步到公众号</a>
							</td>
						</tr>
						
					</table>
				</div>
				<div>
					<table id="table1" class="table tree table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>菜单名</th>
								<th>菜单类型</th>
								<th>key</th>
								<th>url</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			fnGetMenu();
		});
			
		function fnGetMenu(){
			$.post('${ctx}/wx/menu/list',{wxId:'${wxId}'},function(rev){
				$('#table1 tbody').empty();
				if(rev.length==0){
					var tr="<tr><td colspan='"+$('#table1').find('thead').find('tr').find('th').length+"'>暂无数据...</td></tr>";
					$('#table1 > tbody').append(tr);
					return;
				}
				$.each(rev,function(i,v){
					if(v.menuPid==null){
						fnBuildTR(v);
						$.each(rev,function(j,k){
							if(v.menuId==k.menuPid){
								fnBuildTR(k);
							} 
						});
					}
				});
				$('.tree').treegrid();
			},'json');
		}
		
		function fnBuildTR(v){
			var tr='<tr class="treegrid-'+v.menuId;
			if(null != v.menuPid)
				tr+=' treegrid-parent-'+v.menuPid;
			tr+='">';
				tr+='<td>'+v.menuName+'</td>';
				tr+='<td>'+null2Val(v.menuType,'')+'</td>';
				tr+='<td>'+null2Val(v.menuKey,'')+'</td>';
				tr+='<td>'+null2Val(v.menuUrl,'')+'</td>';
				tr+='<td>'
					+' <a href="javascript:;" class="btn btn-default" onclick="fnAdd(\''+v.menuId+'\')">修改</a>'
					+' <a href="javascript:;" class="btn btn-default" onclick="fnDel(\''+v.menuId+'\')">删除</a>'
					'</td>';
			tr+='</tr>';
			$('#table1 tbody').append(tr);
		}

		//添加/修改
		function fnAdd(id){
			var url='${ctx}/wx/menu/add';
			if(id)
				url+='?menuId='+id;
			top.dialog({
				id:'fnAdd'
				,title:'添加/修改'
				,width:668
				,url:url
				,onclose: function () {
					if(this.returnValue){
						fnGetMenu();
					}	
				}
			}).show();
		}
					
		//删除
		function fnDel(id){
			$.post('${ctx}/wx/menu/del',{id:id},function(rev){
				if(rev.code=='success'){
					fnGetMenu();
				} else{
					var d=top.dialog({
						title : '额。。。囧，操作失败，请稍后再试...'
						,content:rev.msg
					}).show();
					setTimeout(function(){
						d.close().remove();
					},500);
				}
			},'json');
		}
		
		//同步到微信
		function fnWXSync(){
			$.post('${ctx}/wx/menu/sync',{wxId:'${wxId}'},function(rev){
				var title='同步成功...';
				if(rev.code=='fail'){
					title='额。。。囧，操作失败，请稍后再试...';
				}
				var d=top.dialog({
					title : title
					,content:rev.msg
				}).show();
				setTimeout(function(){
					d.close().remove();
				},1500);
			},'json');
		}
	</script>
</body>
</html>