<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <input type="hidden" id="wxId" name="wxId" value="${model.wxId }"/>
                <input type="hidden" id="msgType" name="msgType" value="${model.msgType }"/>
                <input type="hidden" id="resId" name="resId" value="${model.resId }"/>
                <input type="hidden" id="disableState" name="disableState" value="0"/>
                <input type="hidden" id="resState" name="resState" value="${model.resState }"/>
                <table class="table table-bordered table-hover">
                    <tr>
                        <td class="wh100">名称:</td>
                        <td>
                            <input type="text" id="resName" name="resName"
                                   value="${model.resName }"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="wh100">消息类型:</td>
                        <td>
                            <select class="form-control" id="resType" name="resType">
                                <option  value="">请选择</option>
                                <option value="text">文本</option>
                                <option value="news">图文</option>
                            </select>
                        </td>
                    </tr>

                    <%--<tr>
                        <td class="wh100">状态:</td>
                        <td>
                            <select class="form-control" id="resState" name="resState">
                                <option value="">请选择</option>
                                <option value="0">可用</option>
                                <option value="1">不可用</option>
                            </select>
                        </td>
                    </tr>--%>
                    <tr style="display:none" id="trMsgContent">
                        <td class="wh100">消息内容:</td>
                        <td>
                           <textarea id="msgContent" name="msgContent" style="width: 500px;height: 100px">${model.msgContent}</textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $("#resType").change(function () {
            if($(this).val()=="text"){
                $("#trMsgContent").css("display","");
            }else{
                $("#trMsgContent").css("display","none");
            }
        });
        if($("#resId").val()==""){
            $("#msgType").val("${param.msgtype}");
            $("#wxId").val("${param.wxid}");
            $("#resState").val("1");
        }else{
            $("#resType").val("${model.resType }");
            $("#resState").val("${model.resState }");
            $("#resType").attr("disabled","disabled");
        }
        if($("#resType").val()=="text"){
            $("#trMsgContent").css("display","");
        }
    })
    var dialog = parent.dialog.getCurrent();
    dialog.button([{
        value: '取消'
        , callback: function () {

        }
    }, {
        value: '提交'
        , callback: fnSave
    }
    ]);
    function saveValid(){
        if($.trim($("#resName").val())==""){
            alert("请输入名称！");
            $("#resName").focus();
            return false;
        }
        if($.trim($("#resType").val())==""){
            alert("请选择消息类型！");
            $("#resType").focus();
            return false;
        }
        if($.trim($("#resType").val())=="text" && $.trim($("#msgContent").val()) == ""){
            alert("请输入消息内容！");
            $("#msgContent").focus();
            return false;
        }
        return true;
    }
    function fnSave() {
        if(!saveValid()){
            return false;
        }
        var $this = this;
        $.post('${ctx}/wx/message/save', $('#form').getFormsValue(), function (rev) {
            console.log(rev);
            if ('fail' == rev.code) {
                var d = parent.dialog({
                    title: '额。。。囧，操作失败，请稍后再试...'
                    , width: 300
                    , content: rev.msg
                }).show();
            } else {
                setTimeout(function () {
                    $this.close('success').remove();
                }, 200);
            }
        }, 'json');
        return false;
    }

</script>
</body>
</html>
