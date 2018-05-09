<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL 标签库</title>
</head>
<body>
	<h2>JSTL核心标签库——表达式标签(out,set,remove,catch)</h2>
	<%
		request.setAttribute("p1", "表达式标签<out>");
		request.setAttribute("p2", null);
	%>
	<h3>c:out 输出</h3>
	<c:out value="<h1>out out out</h1>" escapeXml="false"></c:out>
	<br>
	<c:out value="${p1 }" escapeXml="true" default="value为null时的值" />
	<br>
	<c:out value="${p2 }" escapeXml="true">
    	value为null时的值
    </c:out>
	<br>
	<h3>c:set c:remove</h3>
	<c:set var="ex1" scope="page">
    	set标签定义的第一个变量page
    </c:set>
	<c:set var="ex1" value="set标签定义的第二个变量request" scope="request"></c:set>
	<c:set var="ex1" value="set标签定义的第三个变量session" scope="session"></c:set>
	<c:set var="ex1" value="set标签定义的第四个变量application" scope="application"></c:set>
	<!-- remove -->
	<c:remove var="ex1" scope="page" />
	<c:out value="${ex1 }"></c:out>
	<br>
	<!-- set target -->
	<jsp:useBean id="pro1" class="cn.ziran.xjson.controller.TT"></jsp:useBean>
	<c:out value="${pro1.name }" />
	<br>
	<c:out value="${pro1.id }" />
	<br>
	<c:set target="${pro1 }" property="name">
    	set赋值name    
    </c:set>
	<c:set target="${pro1 }" property="id" value="2" />
	<c:out value="${pro1.name }" />
	<br>
	<c:out value="${pro1.id }" />
	<br>
	<!-- catch -->
	<c:catch var="err">
    	${1+'a' }
    </c:catch>
	${err }

	<!-- JSTL核心标签库——URL相关标签(import,url,redirect,param) -->
	<h2>JSTL核心标签库——URL相关标签(import,url,redirect,param)</h2>
	<h3>import</h3>
	<c:import url="import.jsp?v=1" charEncoding="UTF-8">
		<c:param name="id_">web005JSTL</c:param>
	</c:import>
	<br>
	<h3>url</h3>
	<c:url value="import.jsp" var="url1" scope="page" />
	<c:url value="import.jsp" var="url2" scope="page">
		<c:param name="id_">web005JSTL</c:param>
		<c:param name="rl">${url1 }</c:param>
	</c:url>
	<a href="${url1 }">url1生成的标签 ${url1 }</a>
	<br>
	<a href="${url2 }">url2生成的标签 ${url2 }</a>
	<br>
	<h3>redirect</h3>
	<%-- <c:redirect url="import.jsp">
		<c:param name="id_">web005JSTL</c:param>
		<c:param name="rl">${url1 }</c:param>
	</c:redirect> --%>
	<h2>JSTL核心标签库——流程控制(if,choose,when,otherwise)</h2>
	<!-- if -->
	<c:if test="${empty p2 }" var="test2" scope="page">
    	empty p2
    </c:if>
	<br> test2:${test2 }
	<br />
	<c:if test="${test2 }">
    	\${empty p2 }值为true
    </c:if>
	<br>
	<c:if test="${!test2 || not test2}">
    	\${empty p2 }值为false
    </c:if>
	<br>
	<!-- choose when otherwise -->
	<c:set var="cset1">
		<%=new Random().nextInt(100) %>
	</c:set>
	cset1 : ${cset1 }
	<br>
	<c:choose>
		<c:when test="${cset1 >=0 and cset1<30 }">
    		0<=cset1<30
    	</c:when>
		<c:when test="${cset1 ge 30 && cset1 lt 50 }">
    		30<=cset1<50
    	</c:when>
		<c:otherwise>
    		otherwise 50<=cset1<60
    	</c:otherwise>
	</c:choose>

	<h2>JSTL核心标签库——循环标签(forEach,forTokens)</h2>
	<%
    	List<String> list=new ArrayList<String>();
    	list.add("条件");
    	list.add("老师");
    	list.add("回报");
    	request.setAttribute("list", list);
     %>
	<!-- forEach -->
	<!-- 遍历元素，不包括第 一 个元素 设置begin=1, -->
	<c:forEach items="${list }" var="key1" begin="0" step="1"
		varStatus="vs">
    	${vs}
    	索引：${vs.index }　　　
    	是否第一次:${vs.first }　　　
    	是否最后一次${vs.last }　　　
    	计数:${vs.count }　　　
    	值: ${key1 }<br>
	</c:forEach>
	<br> 列举10以内的全部奇数
	<c:forEach var="i" begin="1" end="10" step="2">
      	${i }　　
      </c:forEach>
	<br>
	<!-- forTokens -->
	<p>java web:程序宝典、典型大全;java:自学手册、java api</p>
	分割符 : ; 、
	<br>
	<c:set var="cset2" value="java web:程序宝典、典型大全;java:自学手册、java api" />
	<c:forTokens items="${cset2 }" delims=":;、" var="str" varStatus="vs">
      	${str }
      	当前：${vs.current }
      	索引：${vs.index }　　　
    	是否第一次:${vs.first }　　　
    	是否最后一次${vs.last }　　　
    	计数:${vs.count }　<br>
	</c:forTokens>
</body>
</html>