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
				<div id="form">
					<table class="table table-bordered table-hover">
						<tr>
							<td colspan="6" style="text-align: right;">
								<a href="javascript:;" class="btn btn-default" id="btnSearch">查询</a>
							</td>
						</tr>
						<tr>
							<td class="wh100">区域名：</td>
							<td>
								<input type="text" id="areaName" name="areaName" class="form-control" />
							</td>
							<td class="wh100">级别：</td>
							<td>
								<select id="areaLevel" name="areaLevel" class="form-control">
									<option value=""></option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
								</select>
							</td>
							<td class="wh100">状态：</td>
							<td>
								<select id="areaStatus" name="areaStatus" class="form-control">
									<option value=""></option>
									<option value="0">可用</option>
									<option value="1">禁用</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table id="tb1" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th style="width: 40px;"></th>
								<th>区域ID</th>
								<th>区域名</th>
								<th>父ID</th>
								<th>排序</th>
								<th>级别</th>
								<th>状态</th>
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
	<script type="text/javascript">
		//查询
		function fnSearch(currentPage) {
			$('#tb1').initTable({
				url : '${ctx }/area/list'
				,pageNum : currentPage
				,callback : callback
			});
		}
		//
		function callback(data){
			$('#tb1 > tbody').empty();
			if(data.length==0){
				var tr="<tr><td colspan='"+$('#tb1').find('thead').find('tr').find('th').length+"'>暂无数据...</td></tr>";
				$('#tb1 > tbody').append(tr);
				return;
			}
			$.each(data,function(i,v){
				var tr='<tr>'
					+'<td>'+v.n0+'</td>'
					+'<td>'+v.areaId+'</td>'
					+'<td>'+v.areaName+'</td>'
					+'<td>'+v.areaPid+'</td>'
					+'<td>'+v.areaSeq+'</td>'
					+'<td>'+v.areaLevel+'</td>'
					+'<td>'+v.areaStatus+'</td>'
					+'</tr>';
				$('#tb1 > tbody').append(tr);			
			});
		}

		$(function() {
			//$('#form').setFormsValue(urlParam2Obj());
			fnSearch(1);
			$('#btnSearch').on('click',function(){
				fnSearch(1);
			});
		});
	</script>
</body>
</html>