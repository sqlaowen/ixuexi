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
        <div class="col-md-12 column" style="margin-top: 10px;">
            <div class="form-group">
                <input type="text" class="form-control" name="cardNo" placeholder="请输入物联网卡号" style="height: 45px;"/>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <table class="table">
            <tr>
                <td>
                    <input type="button" name="btnRMB" class="btn btn-lg" value="5元"/>
                </td>
                <td>
                    <input type="button" name="btnRMB" class="btn btn-lg" value="10元"/>
                </td>
                <td>
                    <input type="button" name="btnRMB" class="btn btn-lg" value="20元"/>
                </td>
                <td>
                    <input type="button" name="btnRMB" class="btn btn-lg" value="50元"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <input type="button" class="btn btn-lg btn-block btn-success" value="立即充值">
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $('input[name="btnRMB"]').on('click',function () {
            $('input[name="btnRMB"]').removeClass(function(){return 'btn-success';});
            $(this).toggleClass('btn-success');
        });
    })
</script>
</body>
</html>