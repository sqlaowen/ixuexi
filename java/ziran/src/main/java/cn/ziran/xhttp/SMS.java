package cn.ziran.xhttp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class SMS {

	public static void main(String[] args) {
		SMS sms = new SMS();
		// 发送
		// sms.send();
		// 余额查询
		sms.overage();
	}

	// 发送
	public void send() {
		// 设置请求地址
		PostMethod postMethod = new PostMethod("http://sh2.ipyy.com/sms.aspx");
		// 设置用户浏览器为MicrosoftIE6.0
		postMethod.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
		// 设置参数编码格式
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		postMethod.addParameter("action", "send");
		postMethod.addParameter("userid", "");
		postMethod.addParameter("account", "hpwcs10");
		postMethod.addParameter("password", "hpwcs10");
		// postMethod.addParameter("mobile", "18101345070,18801333290");
		postMethod.addParameter("mobile", "18101345070");
		postMethod.addParameter("content", "您的验证码是012345请不要把验证码泄露给其他人。如非本人操作，可不用理会！【华信科技】");
		// postMethod.addParameter("sendTime", "");
		// postMethod.addParameter("extno", "");
		int status = 0;
		HttpClient httpClient = new HttpClient();
		try {
			status = httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (status != 200) {
			System.out.println("---- 请求失败...");
		} else {
			try {
				String xml = new String(postMethod.getResponseBody(), "UTF-8");
				System.out.println(xml);
				// 返回如下的xml
				// <?xml version="1.0" encoding="utf-8" ?>
				// <returnsms>
				// <returnstatus>Success</returnstatus>
				// <message>操作成功</message>
				// <remainpoint>5</remainpoint>
				// <taskID>1509301337131924</taskID>
				// <successCounts>2</successCounts>
				// </returnsms>

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 余额查询
	public void overage() {
		// 设置请求地址
		PostMethod postMethod = new PostMethod("http://sh2.ipyy.com/sms.aspx");
		// 设置用户浏览器为MicrosoftIE6.0
		postMethod.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
		// 设置参数编码格式
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		postMethod.addParameter("action", "overage");
		postMethod.addParameter("userid", "");
		postMethod.addParameter("account", "hpwcs10");
		postMethod.addParameter("password", "hpwcs10");

		int status = 0;
		HttpClient httpClient = new HttpClient();
		try {
			status = httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (status != 200) {
			System.out.println("---- 请求失败...");
		} else {
			try {
				String xml = new String(postMethod.getResponseBody(), "UTF-8");
				System.out.println(xml);
				// 返回如下的xml
				// <?xml version="1.0" encoding="utf-8" ?>
				// <returnsms>
				// <returnstatus>Sucess</returnstatus>
				// <message>操作成功</message>
				// <payinfo>预付</payinfo>
				// <overage>4</overage>
				// <sendTotal>146</sendTotal>
				// </returnsms>

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
