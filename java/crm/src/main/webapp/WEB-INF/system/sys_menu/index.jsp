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
			<div class="span12">
				<div>
					<table class="table table-bordered table-hover">
						<tr>
							<td colspan="6" style="text-align: right;">
								<a href="javascript:;" class="btn btn-default" onclick="fnAdd()">添加</a>
							</td>
						</tr>
						
					</table>
				</div>
				<div>
					<table id="menu" class="table tree table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>名称</th>
								<th>资源路径</th>
								<th style="width:60px;">排序</th>
								<th>描述</th>
								<th style="width:15%;">操作</th>
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
			fnSearch();
		});
		
		function fnSearch(){
			$.post('${ctx}/perm/menu',{},function(rev){
				//console.log(rev);
				$('#menu tbody').empty();
				fnDigui(rev);
				$('.tree').treegrid();
			},'json');
		}
		
		function fnDigui(data){
			$.each(data,function(i,v){
				var tr='<tr class="treegrid-'+v.permId;
					if(0!=v.permPid)
						tr+=' treegrid-parent-'+v.permPid;
					tr+='">';
					tr+='<td>'+v.permName+'</td>';
					tr+='<td>'+null2Val(v.permUrl,'')+'</td>';
					tr+='<td>'+null2Val(v.permSeq,'')+'</td>';
					tr+='<td>'+null2Val(v.permNote,'')+'</td>';
					tr+='<td>'
							+' <a href="javascript:;" class="btn btn-default" onclick="fnAdd(\''+v.permId+'\')">修改</a>'
							+' <a href="javascript:;" class="btn btn-default" onclick="fnDel(\''+v.permId+'\')">删除</a>'
						+'</td>';
					tr+='</tr>';
				$('#menu tbody').append(tr);
				if(v.childs.length>0)
					fnDigui(v.childs);
			});
		}
				
		//添加/修改
		function fnAdd(id){
			var url='${ctx}/menu/add';
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
			$.post('${ctx}/menu/del',{id:id},function(rev){
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
</body>
</html>