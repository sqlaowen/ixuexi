<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/headerEdit.jsp" %>
    <script src="${ctx}/js/ajaxfileupload.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div id="form">
                <input type="hidden" id="contentId" name="contentId" value="${model.contentId }"/>
                <input type="hidden" id="resId" name="resId" value="${model.resId }"/>
                <input type="hidden" id="status" name="status" value="0"/>
                <table class="table table-bordered table-hover">
                    <tr>
                        <td style="width: 120px;">标题:</td>
                        <td>
                            <input type="text" id="title" name="title" value="${model.title }"/>
                        </td>
                        <td class="wh100">描述:</td>
                        <td>
                            <input type="text" id="description" name="description"
                                   value="${model.description }"/>
                        </td>
                    </tr>
                    <tr>
                        <td>图片url:</td>
                        <td>
                            <div id="divPicFile">
                                <input type="file" class="form-control" id="picFile"
                                       name="picFile" style="display:none"/>
                            </div>
                            <input type="button" value="上传" id="btnUpload"
                                   class="btn btn-default"/> <input type="hidden" id="picUrl"
                                                                    name="picUrl"
                                                                    value="${model.picUrl }"/>
                            <%--<input type="text" id="picUrl" name="picUrl" value="${model.picUrl }"/>--%>
                        </td>
                        <td>消息url:</td>
                        <td>
                            <input type="text" id="url" name="url" value="${model.url }"/>
                        </td>
                    </tr>
                    <tr>
                    	<td>排序:</td>
                        <td>
                        	<input type="text" id="contentSeq" name="contentSeq" value="${model.contentSeq }"/>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="picView" style="display:none">
                        <td>图片预览:</td>
                        <td colspan="3">
                            <div class="form-horizontal">
                                <a href="${tfsImgPath}${model.picUrl }" target="_blank">
                                    <img src="${tfsImgPath}${model.picUrl }" id="picImg"
                                         style="width:100px;height:100px"/>
                                </a>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        if ("${param.id}" == "") {
            $("#resId").val("${param.resid}");
        }
        $("#picFile").change(function () {
            uploadFile();
        });
        $("#btnUpload").on("click", function () {
            $("#picFile").click();
        });
        if ($("#picUrl").val() != "") {
            $("#picView").css("display", "");
        }
    });
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
    function fnSave() {
        var $this = this;
        $.post('${ctx}/wx/content/save', $('#form').getFormsValue(), function (rev) {
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
    function uploadFile() {
        if ($("#picFile").val() == "") {
            alert("请选择要上传的文件");
            return;
        }
        $.ajaxFileUpload({
            url: '${ctx}/wx/upload/uploadfile',
            secureuri: false,
            fileElementId: 'picFile',//file标签的id
            dataType: 'json',//返回数据的类型
            success: function (data, status) {
                if (data.code == "ok") {
                    $("#picView").css("display", "");
                    $("#picUrl").val(data.realPath);
                    $("#picImg").attr("src", "${tfsImgPath}" + data.realPath);
                    $("#picImg").parent().attr("href", "${tfsImgPath}" + data.realPath);
                } else {
                    alert(data.message);
                }
            },
            error: function (data, status, e) {
                alert("上传失败");
            }
        });
        $("#divPicFile").html("");
        $("#divPicFile")
                .html(
                '<input type="file"  class="form-control" id="picFile" name="picFile" value="'
                + (index++)
                + '" onchange="uploadFile()" style="display:none"/>');

    }
    var index = 0;
</script>
</body>
</html>
