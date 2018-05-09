<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/header.jsp" %>

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
                            <a href="javascript:;" class="btn btn-default"
                               onclick="fnGoback()">返回</a>
                        </td>
                    </tr>

                </table>
            </div>
            <div>
                <table id="table1" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>标题</th>
                        <th>描述</th>
                        <th>图片url</th>
                        <th>消息url</th>
                        <th>排序</th>
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
    $(function () {
        fnSearch();
    });

    function fnSearch() {
        $('#table1').initTable({
            url: '${ctx}/wx/content/list'
            , pageSize: 100
            , callback: fnCallback
            , exParam: {resId: '${param.id}'}
            , isPage: false
        });
    }

    function fnCallback(rev) {
        $('#table1 tbody').empty();
        if (rev.length == 0) {
            var tr = "<tr><td colspan='" + $('#table1').find('thead').find('tr').find('th').length + "'>暂无数据...</td></tr>";
            $('#table1 > tbody').append(tr);
            return;
        }
        $.each(rev, function (i, v) {
            var tr = '<tr>';
            tr += '<td>' + v.title + '</td>';
            tr += '<td>' + null2Val(v.description, '') + '</td>';
            tr += '<td>';
            if (null2Val(v.picUrl, '') != '') {
                tr += '<img src="${tfsImgPath}' + v.picUrl + '" style="width:50px;height:50px"/>';
            }
            tr += '</td>';
            tr += '<td>' + null2Val(v.url, '') + '</td>';
            tr += '<td>'+null2Val(v.contentSeq,'')+'</td>';
            tr += '<td>'
                    + ' <a href="javascript:;" class="btn btn-default" onclick="fnAdd(\'' + v.contentId + '\')">修改</a>'
                    + ' <a href="javascript:;" class="btn btn-default" onclick="fnDel(\'' + v.contentId + '\')">删除</a>'
            '</td>';
            tr += '</tr>';
            $('#table1 tbody').append(tr);
        });
    }

    //添加/修改
    function fnAdd(id) {
        var url = '${ctx}/wx/content/add?resid=${param.id}';
        if (id)
            url += '&id=' + id;
        top.dialog({
            id: 'fnAdd'
            , title: '添加/修改'
            , width: 730
            , height: 260
            , url: url
            , onclose: function () {
                if (this.returnValue) {
                    fnSearch();
                }
            }
        }).show();
    }

    //删除
    function fnDel(id) {
        $.post('${ctx}/wx/content/del', {id: id}, function (rev) {
            if (rev.code == 'success') {
                fnSearch();
            } else {
                var d = top.dialog({
                    title: '额。。。囧，操作失败，请稍后再试...'
                    , content: rev.msg
                }).show();
                setTimeout(function () {
                    d.close().remove();
                }, 500);
            }
        }, 'json');
    }

    //返回
    function fnGoback() {
        window.history.back();
    }

</script>
</body>
</html>
