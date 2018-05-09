package com.pay.combine.ali;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.pay.utils.MD5;
import com.pay.utils.RSA;

/**
 * 类名：AlipayNotify 功能：支付宝通知处理类 详细：处理支付宝各接口通知返回 版本：3.3 日期：2012-08-17
 * 说明：以下代码只是为了方便商户测试而提供的样例代码， 商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考 注意*************************
 * 调试通知返回时，可查看或改写log日志的写入TXT里的数据，来检查通知返回是否正常
 */
public class AlipayNotify {

	/**
	 * 验证消息是否是支付宝发出的合法消息
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @param sign_type
	 *            签名方式 [MD5, RSA]
	 * @param key
	 *            sign_type为MD5时为商户私钥, sign_type为RSA时为支付宝公钥
	 * @param verifyUrl
	 *            支付宝消息验证地址
	 * @param partner
	 *            合作身份者ID，以2088开头由16位纯数字组成的字符串
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String> params, String sign_type, String key, String verifyUrl,
			String partner) {

		// 判断responsetTxt是否为true，isSign是否为true
		// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		// isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String responseTxt = "true";
		if (params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id, verifyUrl, partner);
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		boolean isSign = getSignVeryfy(params, sign, sign_type, key);

		// 写日志记录（若要调试，请取消下面两行注释）
		// String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign +
		// "\n 返回回来的参数：" +
		// AlipayCore.createLinkString(params);
		// AlipayCore.logResult(sWord);

		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据反馈回来的信息，生成签名结果
	 * 
	 * @param Params
	 *            通知返回来的参数数组
	 * @param sign
	 *            比对的签名结果
	 * @param sign_type
	 *            签名方式 [MD5, RSA]
	 * @param key
	 *            sign_type为MD5时为商户私钥, sign_type为RSA时为支付宝公钥
	 * @return 生成的签名结果
	 */
	private static boolean getSignVeryfy(Map<String, String> Params, String sign, String sign_type, String key) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
		// 获取待签名字符串
		String preSignStr = AlipayCore.createLinkString(sParaNew);
		// 获得签名验证结果
		boolean isSign = false;
		if (sign_type.equalsIgnoreCase("MD5")) {
			isSign = MD5.verify(preSignStr, sign, key, "utf-8");
		} else if (sign_type.equalsIgnoreCase("RSA")) {
			isSign = RSA.verify(preSignStr, sign, key, "utf-8");
		}
		return isSign;
	}

	/**
	 * 获取远程服务器ATN结果,验证返回URL
	 * 
	 * @param notify_id
	 *            通知校验ID
	 * @param veryfyUrl
	 *            支付宝消息验证地址
	 * @param partner
	 *            合作身份者ID，以2088开头由16位纯数字组成的字符串
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String verifyResponse(String notify_id, String verifyUrl, String partner) {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求

		String verify_url = verifyUrl + "partner=" + partner + "&notify_id=" + notify_id;

		return checkUrl(verify_url);
	}

	/**
	 * 获取远程服务器ATN结果
	 * 
	 * @param urlvalue
	 *            指定URL路径地址
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String checkUrl(String urlvalue) {
		String inputLine = "";

		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}

		return inputLine;
	}

	public static void main(String[] args) {
		String url = "https://mapi.alipay.com/gateway.do?service=notify_verify&partner=2088211554961671&notify_id=2057e0b8fe4088824ef2bb04704866885f";
		System.out.println(checkUrl(url));
	}
}
