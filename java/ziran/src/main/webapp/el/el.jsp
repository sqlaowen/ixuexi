<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL 表达式</title>
</head>
<body>
	<h3>EL变量搜索顺序：page > request > session > application</h3>
	<!-- EL表达式语言中定义了11个隐含对象，使用这些隐含对象可以很方便地获取web开发中的一些常见对象，并读取这些对象的数据。
　　语法：${隐式对象名称}：获得对象的引用 -->
	<!-- pageContext,pageSocpe,requestScope,sessionScope,applicationScope,param,paramValues,header,headerValues,cookie,initParam -->
	request.serverPort : ${pageContext.request.serverPort }
	<br> response.contentType : ${pageContext.response.contentType }
	<br> out.bufferSize : ${pageContext.out.bufferSize }
	<br>
	session.maxInactiveInterval:${pageContext.session.maxInactiveInterval }
	<br> exception.message : ${pageContext.exception.message }
	<br>
	servletContext.contextPath:${pageContext.servletContext.contextPath }
	<h3>empty</h3>
	${empty pageScope.xx ?'变量为空':'变量不为空' }
	<h3>EL运算</h3>
	'1'+'2'： ${'1'+'2' }
	<br> 1=1 : ${1==1 } ${1 eq 1 }
	<br> 1!=1 : ${1!=1 }
	<%-- ${1 ne 1 } --%>
	<br> 1<1 : ${1 < 1 } ${1 lt 1 }
	<br> 1<=1 : ${1 <= 1 } ${1 le 1 }
	<br> 1>=1 : ${1 >=1 } ${1 ge 1 }
	<br> true && true : ${true && true } ${true and true }
	<br> true && false : ${true && false } ${true and false }
	<br> true || true : ${true || true } ${true or true }
	<br> true || false : ${true || false } ${true or false }
	<br> !true : ${!true } ${not true }
	<br> !false : ${!false } ${not false }
</body>
</html>