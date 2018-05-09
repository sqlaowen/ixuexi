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
        <table class="table table-bordered table-hover" id="form">
            <tr>
                <td style="text-align: right;width:80px;">
                    名称：
                </td>
                <td>
                    <input type="text" id="resName" name="resName" value=""/>
                </td>
                <td style="text-align: right;width:80px;">
                    类型：
                </td>
                <td>
                    <select class="form-control" id="resType" name="resType" style="width: 100px">
                        <option value="">请选择</option>
                        <option value="text">文本</option>
                        <option value="news">图文</option>
                    </select>
                </td>
                <td style="text-align: right;width:80px;">
                    状态：
                </td>
                <td>
                    <select class="form-control" id="resState" name="resState" style="width: 100px">
                        <option value="">请选择</option>
                        <option value="0">可用</option>
                        <option value="1">不可用</option>
                    </select>
                </td>
                <td>
                    <a href="javascript:;" class="btn btn-default" onclick="fnSearch()">查询</a>
                    <a href="javascript:;" class="btn btn-default" onclick="fnClean()">清空</a>
                </td>
            </tr>
            <tr>
                <td colspan="7" style="text-align: left;">
                    <a href="javascript:;" class="btn btn-default" onclick="fnAdd()">新增</a>
                </td>
            </tr>
        </table>

        <table id="table1" class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>名称</th>
                <th>消息类型</th>
                <th>状态</th>
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
<script>
    $(function () {
        fnSearch();
    });
    function fnClean() {
        $("#resName").val("");
        $("#resType").val("");
        $("#resState").val("");
    }
    function fnSearch() {
        $('#table1').initTable({
            url: '${ctx}/wx/message/getautorespagelist'
            , exParam: {WxId: "${wxId}", MsgType: '${param.msgtype}'}
            , pageSize: 20
            , callback: fnCallback
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
            tr += '<td>' + v.resName + '</td>';
            tr += '<td>' + showResType(v.resType) + '</td>';
            tr += '<td>' + showResState(v.resState) + '</td>';
            tr += '<td style="width: 240px;">';
            if (v.resType == 'news') {
                tr += ' <a href="${ctx}/wx/content/index?id=' + v.resId + '">详情</a>';
            }
            tr += ' <a href="javascript:;" class="btn btn-default" onclick="fnAdd(\'' + v.resId + '\')">修改</a>';
            tr += ' <a href="javascript:;" class="btn btn-default" onclick="fnDel(\'' + v.resId + '\')">删除</a>';
            if (v.resState == 1) {
                tr += ' <a href="javascript:;" class="btn btn-default" onclick="fnSetEnable(\'' + v.resId + '\',\'' + v.msgType + '\',\'' + v.wxId + '\')">' + (v.resState == 0 ? "禁用" : "启用") + '</a>';
            }
            tr += '</td>';
            tr += '</tr>';
            $('#table1 tbody').append(tr);
        });
    }

    function showResType(resType) {
        var result = resType;
        if (resType == "text") {
            result = "文本";
        } else if (resType == "news") {
            result = "图文";
        }
        return result;
    }
    function showResState(resState) {
        var result = resState;
        if (resState == 0) {
            result = "可用";
        } else if (resState == 1) {
            result = "不可用";
        }
        return result;
    }

    //添加/修改
    function fnAdd(id) {
        var url = '${ctx}/wx/message/add';
        if (id)
            url += '?id=' + id;
        else
            url += '?msgtype=${param.msgtype}&wxid=${wxId}';
        top.dialog({
            id: 'fnAdd'
            , title: '添加/修改'
            , width: 668
            , height : 300
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
        var d = top.dialog({
            title: '提示',
            content: '确认要删除吗？',
            okValue: '确定',
            width: 200,
            ok: function () {
                this.title('提交中…');
                $.post('${ctx}/wx/message/del', {id: id}, function (rev) {
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
            },
            cancelValue: '取消',
            cancel: function () {
            }
        });
        d.showModal();
    }
    //设置启用
    function fnSetEnable(resId, msgType, wxId) {
        $.post('${ctx}/wx/message/setenable', {
            resId: resId,
            msgType: msgType,
            wxId: wxId
        }, function (rev) {
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
</script>
</body>
</html>
