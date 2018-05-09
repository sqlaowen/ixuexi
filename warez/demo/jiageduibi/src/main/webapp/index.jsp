<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <script type="text/javascript" src="${ctx }/resource/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx }/resource/highcharts.js"></script>
    <script type="text/javascript" src="${ctx }/resource/index.js"></script>
    <title>历史价格比对</title>
</head>
<body>
    <div id="msg"></div>
    <!-- 曲线图 -->
    <div id="chart"></div>
</body>
</html>