package com.pay.combine.wx;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.utils.CommonUtil;
import com.pay.utils.MD5;

/**
 * 签名
 * 
 * @author ziran
 * 
 */
public class Signature {

	private static Logger log = LoggerFactory.getLogger(Signature.class);

	public static String getSign(Map<String, String> map, String key) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getValue() != "") {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + key;
		result = MD5.sign(result, "", "UTF-8").toUpperCase();
		return result;
	}

	/**
	 * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
	 * 
	 * @param responseString
	 *            API返回的XML数据字符串
	 * @return API签名是否合法
	 * @throws DocumentException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean checkIsSignValidFromResponseString(String responseString, String key)
			throws UnsupportedEncodingException, DocumentException {

		Map<String, String> map = CommonUtil.getMapFromXML(responseString);
		if (!map.containsKey("sign")) {
			log.error("微信请求返回:{}不包含签名信息...", responseString);
			return false;
		}
		String signFromAPIResponse = map.get("sign").toString();
		if (signFromAPIResponse == "" || signFromAPIResponse == null) {
			log.debug("微信请求返回签名数据不存在，有可能被第三方篡改!!!");
			return false;
		}
		log.debug("服务器回包里面的签名是:" + signFromAPIResponse);
		// 清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
		map.put("sign", "");
		// 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
		String signForAPIResponse = Signature.getSign(map, key);

		if (!signForAPIResponse.equals(signFromAPIResponse)) {
			// 签名验不过，表示这个API返回的数据有可能已经被篡改了
			log.debug("微信请求返回的数据签名验证不通过，有可能被第三方篡改!!!");
			return false;
		}
		log.debug("恭喜，微信请求返回的数据签名验证通过!!!");
		return true;
	}

}
