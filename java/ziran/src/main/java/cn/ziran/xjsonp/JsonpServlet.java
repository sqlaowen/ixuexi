package cn.ziran.xjsonp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonpServlet extends HttpServlet {

	private static final long serialVersionUID = 6345259393897548241L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String callbackfun = request.getParameter("mycallback");
		System.out.println(callbackfun);
		response.setContentType("text/json;charset=utf-8");

		callbackfun += "({'age':100,'name':'wenshiwei'})";
		System.out.println(callbackfun);

		response.getWriter().println(callbackfun);
	}
	//
	// $.ajax({
	// type:"post",
	// url:"localhost:8080/ziran/jsonp",
	// dataType:'jsonp',
	// jsonp:'callback-key', //如果不传，默认：callback
	// jsonpCallback:'callback-value', //如果不传，默认：jQuery+随机数
	// success:function(data) {
	// console.log(data);
	// }
	// });
	//
	// 发送url: url?callback-key=callback-value&_随机数
	//
	// --------------------------------------------------------------------
	// ----------------------- 示例如下 ----------------------------------
	// --------------------------------------------------------------------
	//
	// $.ajax({type:'post',url:'http://localhost:8080/ziran/jsonp',dataType:'jsonp',success:function(data){console.log(data);}})
	// http://localhost:8080/ziran/jsonp?callback=jQuery20308015107081325902_1443603030168&_=1443603030175
	//
	// $.ajax({type:'post',url:'http://localhost:8080/ziran/jsonp',dataType:'jsonp',jsonpCallback:'callbackFun',success:function(data){}})
	// http://localhost:8080/ziran/jsonp?callback=callbackFun&_=1443603030176
	//
	// $.ajax({type:'post',url:'http://localhost:8080/ziran/jsonp',dataType:'jsonp',jsonp:'mycallback',jsonpCallback:'callbackFun',success:function(data){}})
	// http://localhost:8080/ziran/jsonp?mycallback=callbackFun&_=1443603030177

}
