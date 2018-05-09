<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="com.laowen.masterpage" prefix="tag" %>
<tag:ContentPage materPageId="master">

    <tag:ContentPlaceHolder id="css">

    </tag:ContentPlaceHolder>

    <tag:Content contentPlaceHolderId="title">
        管理平台
    </tag:Content>

    <tag:Content contentPlaceHolderId="breadcrumb">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">首页</a>
            </li>
            <li class="active">控制台</li>
        </ul>
    </tag:Content>

    <tag:Content contentPlaceHolderId="page-content">
        <div class="row">
            <div class="col-xs-12">
                欢迎使用物联网管理平台
            </div>
        </div>
    </tag:Content>

    <tag:Content contentPlaceHolderId="javascript">
        <script type="text/javascript">

        </script>
    </tag:Content>
</tag:ContentPage>