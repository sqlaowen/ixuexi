package cn.ziran.xservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 190838914965128130L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("  <head><title>A Servlet</title></head>");
		out.println("  <body>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </body>");
		out.println("</html>");
		out.flush();
		out.close();

		System.out.println("……doGet……");
		System.out.println("---------------------------------");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("  <head><title>A Servlet</title></head>");
		out.println("  <body>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </body>");
		out.println("</html>");
		out.flush();
		out.close();

		System.out.println("……doPost……");
		System.out.println("---------------------------------");
	}

	@Override
	public void destroy() {

		System.out.println("……destory……");
		System.out.println("---------------------------------");
	}

	@Override
	public void init() throws ServletException {

		System.out.println("……init……");
		System.out.println("---------------------------------");
	}

}
