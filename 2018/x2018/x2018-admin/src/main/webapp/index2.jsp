<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="test" value="this is test"/>
<div style="height:4000px;background-color:red;margin-top: 0px;">

    <h1>哈哈</h1>
</div>
<h1>哈哈</h1>
<script type="text/javascript">
    $(function () {
        alert('${test}');
        //alert(${test});
        alert('这是子页面');
        alert(x);
        alert($('#frame'));
    });
</script>