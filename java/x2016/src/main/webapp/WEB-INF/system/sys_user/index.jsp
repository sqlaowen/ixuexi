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
					<table id="tb1" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>用户名</th>
								<th>账号</th>
								<th>性别</th>
								<th>电话</th>
								<th>注册时间</th>
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
		</div>
	</div>
	<!-- 角色 -->
	<script type="text/javascript">
		$(function(){
			fnSearch(1);
			
			$('#tb1 tbody').on('click','tr',function(){
				$(this).siblings().removeClass('success');
				$(this).addClass('success');
			});
			
		});
		
		function fnSearch(currentPage){

			$('#tb1').initTable({
				url : '${ctx }/user/list'
				,pageSize : 1
				,pageNum : currentPage
				,callback : callback
			});
		}
		
		function callback(data){
			//console.log(data);
			$('#tb1 > tbody').empty();
			if(data.length==0){
				var tr="<tr><td colspan='"+$('#tb1').find('thead').find('tr').find('th').length+"'>暂无数据...</td></tr>";
				$('#tb1 > tbody').append(tr);
				return;
			}
			$.each(data,function(i,v){
				var tr='<tr id="'+v.userId+'">'
					+'<td>'+v.userName+'</td>'
					+'<td>'+v.userAccount+'</td>'
					+'<td>'+v.userSex+'</td>'
					+'<td>'+v.userPhone+'</td>'
					+'<td>'+v.createTime+'</td>'
					+'<td>'+v.userNote+'</td>'
					+'<td>'
						+' <a href="javascript:;" class="btn btn-default" onclick="fnToRole(\''+v.userId+'\')">角色</a>'
						+' <a href="javascript:;" class="btn btn-default" onclick="fnAdd(\''+v.userId+'\')">修改</a>'
						+' <a href="javascript:;" class="btn btn-default" onclick="fnDel(\''+v.userId+'\')">删除</a>'
					+'</td>'
					+'</tr>';
				$('#tb1 > tbody').append(tr);			
			});
		}
	
		//添加/修改
		function fnAdd(id){
			var url='${ctx}/user/add';
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
			$.post('${ctx}/user/del',{id:id},function(rev){
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
		//分配角色
		function fnToRole(userId){
			top.dialog({
				id:'fnAdd'
				,title:'添加/修改'
				,width:668
				,height:410
				,url:'${ctx}/role/list0?userId='+userId
			}).show();
		}
		
	</script>	
</body>
</html>