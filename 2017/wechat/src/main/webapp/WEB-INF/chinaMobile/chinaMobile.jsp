<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>信息查询</title>
    <!-- Bootstrap -->
    <link href="${ctx}/plugins/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-12 column" style="margin-top: 10px;">
                    请输入正确的卡号/ICCID/IMSI
                </div>
                <div class="col-md-12" style="margin-top: 10px;">
                    <select name="province" id="province" class="form-control">
                        <%--<option value="henan">河南</option>--%>
                        <option value="jiangsu">江苏</option>
                    </select>
                    <label class="control-label"></label>
                    <input type="text" id="txtCard" placeholder="卡号/ICCID/IMSI" class="form-control"/>
                </div>
                <div class="col-md-12" style="margin-top: 10px;">
                    <input type="button" class="btn btn-success form-control" id="fetch" value="查询"/>
                </div>
            </div>
            <div class="alert alert-danger" style="margin-top: 10px; display: none;">错误:请输入"卡号/ICCID/IMSI"</div>
            <div class="list-group" style="margin-top: 10px; display: none;">
                <a href="#" class="list-group-item active">码号信息</a>
                <div class="list-group-item">
                    用户状态:<span id="status"></span>
                </div>
                <div class="list-group-item">
                    开关机状态:<span id="status2008"></span>
                </div>
                <%--<div class="list-group-item">
                    开卡时间:<span id="kaikaTime"></span>
                </div>--%>
                <div class="list-group-item">
                    激活时间:<span id="jihuoTime"></span>
                </div>
                <div class="list-group-item">
                    剩余天数:<span id="shengyuTime"></span>
                </div>
                <div class="list-group-item">
                    余额:<span id="balance"></span>
                </div>
                <div class="list-group-item">
                    套餐:<span id="prodname"></span>
                </div>
                <div class="list-group-item">
                    套餐总量:<span id="total"></span>
                </div>
                <div class="list-group-item">
                    套餐使用总量:<span id="used"></span>
                </div>
                <div class="list-group-item">
                    套餐剩余总量:<span id="left"></span>
                </div>
                <%--<div class="list-group-item">
                    网卡号:<span id="msisdn"></span>
                </div>
                <div class="list-group-item">
                    IMSI:<span id="imsi"></span>
                </div>
                <div class="list-group-item">
                    ICCID:<span id="iccid"></span>
                </div>--%>
                <div class="list-group-item">
                    当月短信:<span id="sms"></span>
                </div>
                <div class="list-group-item">
                    终端IP:<span id="ip"></span>
                </div>
                <div class="list-group-item">
                    接入点:<span id="apn"></span>
                </div>
                <div class="list-group-item">
                    接入方式:<span id="rat"></span>
                </div>
                <div class="list-group-item">
                    GPRS在线状态:<span id="gprsStatus"></span>
                </div>
                <div class="list-group-item">
                    <a href="/internet/recharge" class="btn btn-default">充值</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        var card = $('#txtCard');
        $('#fetch').click(function () {
            var arr = ['balance', 'prodname', 'total', 'used', 'left', 'msisdn', 'imsi', 'iccid', 'sms', 'status', 'status2008', 'ip', 'apn', 'rat', 'gprsStatus', 'kaikaTime', 'jihuoTime', 'shengyuTime'];

            $('div.alert-danger').css('display', 'none');
            if (undefined == card.val() || '' == $.trim(card.val())) {
                $('div.alert-danger').css('display', 'block');
                return;
            }
            $.getJSON('${ctx}/internet/fetch', {openId: '${openId}', cardNo: card.val(), province:$('#province').val()}, function (rev) {
                if ('1' == rev.errcode) {
                    $('div.alert-danger').text(rev.errmsg);
                    $('div.alert-danger').css('display', 'block');
                    $('div.list-group').css('display','none')
                    return;
                }
                $('div.list-group').css('display','block')
                $.each(arr, function (i, v) { //先清空
                    $('#' + v).text('');
                });
                $.each(rev, function (key) { //重新赋值
                    $('#' + key).text(rev[key]);
                });
            })

        });
    });
</script>
</body>
</html>
