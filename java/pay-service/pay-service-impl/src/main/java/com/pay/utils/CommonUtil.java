package com.pay.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class CommonUtil {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	/**
	 * 获取一定长度的随机字符串
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * xmlString 转 Map<String, String>
	 * 
	 * @param xmlString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapFromXML(String xmlString) {
		Map<String, String> map = new HashMap<>();
		try {
			SAXReader saxReader = new SAXReader();
			InputStream in = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			Document doc = saxReader.read(in);
			Element root = doc.getRootElement();
			for (Element e : (List<Element>) root.elements()) {
				map.put(e.getName(), e.getTextTrim());
			}
		} catch (Exception e) {
			log.error("----> Dom4j读取xml异常,xml:{},异常信息:{}", xmlString, e.getMessage());
		}
		log.debug("----> 输入xml:{},\r\n输出map:{}", xmlString, map);
		return map;
	}

	/**
	 * 递归读取xml节点和属性,返回map
	 * 
	 * @param xmlString
	 * @return
	 */
	public static Map<String, String> getMapFromXMLWithAttr(String xmlString) {
		Map<String, String> map = new HashMap<>();
		try {
			SAXReader saxReader = new SAXReader();
			InputStream in = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			Document doc = saxReader.read(in);
			Element root = doc.getRootElement();
			ele2map(map, root);
		} catch (Exception e) {
			log.error("----> Dom4j读取xml异常,xml:{},异常信息:{}", xmlString, e.getMessage());
		}
		log.debug("----> 输入xml:{},\r\n输出map:{}", xmlString, map);
		return map;
	}

	// map转xml
	public static String map2XML(Map<String, String> map) {
		if (null == map || map.size() == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		for (Iterator<?> iter = map.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			sb.append(String.format("<%s><![CDATA[%s]]></%s>", key, map.get(key), key));
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * bean2map
	 * 
	 * @param bean
	 * @param isExcludeNullValue
	 *            [true:排除空值, false:不排除空值]
	 * @return
	 */
	public static Map<String, String> bean2Map(Object bean, boolean isExcludeNullValue) {
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result.toString());
					} else {
						if (!isExcludeNullValue) {
							returnMap.put(propertyName, "");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("----> class2map error:{}", e.getMessage());
		}
		return returnMap;
	}

	/**
	 * Element转Map<String, String>
	 * 
	 * @param map
	 * @param ele
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static void ele2map(Map<String, String> map, Element ele) {

		for (Element e : (List<Element>) ele.elements()) {
			if (e.elements().size() == 0) {
				if (StringUtils.isNotBlank(e.attributeValue("name"))) {
					map.put(e.attributeValue("name"), e.getTextTrim());
				} else {
					map.put(e.getName(), e.getTextTrim());
				}
			} else {
				ele2map(map, e);
			}
		}
	}

	/**
	 * 通过反射的方式遍历对象的属性和属性值，方便调试
	 * 
	 * @param o
	 *            要遍历的对象
	 * @throws Exception
	 */
	public static void reflect(Object o) throws Exception {
		Class<?> cls = o.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.setAccessible(true);
			log.debug(f.getName() + " -> " + f.get(o));
		}
	}

	public static byte[] readInput(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
		return out.toByteArray();
	}

	public static String inputStreamToString(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}
	
	/**
	 * 字符串转base64img
	 * 
	 * @param str
	 * @return
	 */
	public static String str2Base64img(String str) {
		String base64img = null;
		try {
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 400, 400, hints);// 生成矩阵
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);
			base64img = "data:image/png;base64," + Base64.encode(stream.toByteArray());
		} catch (Exception e) {
			log.error("字符串转base64img异常,对应字符串:{}, 异常提示:{}", str, e.getMessage());
		}
		return base64img;
	}
}
