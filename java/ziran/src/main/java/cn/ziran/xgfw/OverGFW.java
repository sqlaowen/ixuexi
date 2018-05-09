package cn.ziran.xgfw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OverGFW {

	public static void main(String[] args) {
		PostMethod method = new PostMethod("http://www.socks163.com/index.php");
		// 设置浏览器为 firefox
		method.setRequestHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0");
		HttpClient client = new HttpClient();
		int status = 0;
		try {
			status = client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String html = "";
		if (status != 200) {
			System.out.println("---- 请求失败...");
		} else {
			try {
				html = new String(method.getResponseBody(), "UTF-8");
				// System.out.println(html);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByClass("jumbotron");
		Elements _html = elements.get(0).getElementsByClass("row-fluid").get(0).getElementsByClass("span4");

		String _config = "";
		for (Element e : _html) {
			// 主机
			String server = e.getElementsByTag("code").get(0).html();
			// 端口
			String serverPort = e.getElementsByTag("code").get(1).html();
			// 密钥
			String password = e.getElementsByTag("code").get(2).html();
			// 协议
			String deal = e.getElementsByTag("code").get(3).html();
			// 备注
			String remarks = "";
			if (_config.equals("")) {
				_config += "    {\r\n";
				_config += "      \"server\":\"" + server + "\"\r\n";
				_config += "      \"server_port\":\"" + serverPort + "\"\r\n";
				_config += "      \"password\":\"" + password + "\"\r\n";
				_config += "      \"method\":\"" + deal + "\"\r\n";
				_config += "      \"remarks\":\"" + remarks + "\"\r\n";
				_config += "    }\r\n";
			} else {
				_config += "    ,{\r\n";
				_config += "      \"server\":\"" + server + "\"\r\n";
				_config += "      \"server_port\":\"" + serverPort + "\"\r\n";
				_config += "      \"password\":\"" + password + "\"\r\n";
				_config += "      \"method\":\"" + deal + "\"\r\n";
				_config += "      \"remarks\":\"" + remarks + "\"\r\n";
				_config += "    }\r\n";
			}
			System.out.println("主机：" + server);
			System.out.println("端口：" + serverPort);
			System.out.println("密钥：" + password);
			System.out.println("协议：" + deal);
			// for (Element _e : e.getElementsByTag("code")) {
			// System.out.println(_e.html());
			// }
			System.out.println("-----------------------");
		}

		String filePath = "xx.json";
		File file = new File(filePath);
		try {
			file.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			String config = "";
			config = "{\r\n";
			config += "  \"configs\": [\r\n";
			config += _config;
			config += "  \"strategy\": \"com.shadowsocks.strategy.balancing\"\r\n";
			config += "  \"index\": -1\r\n";
			config += "  \"global\": false\r\n";
			config += "  \"enabled\": true\r\n";
			config += "  \"shareOverLan\": false\r\n";
			config += "  \"isDefault\": false\r\n";
			config += "  \"localPort\": 1080\r\n";
			config += "  \"pacUrl\": null\r\n";
			config += "  \"useOnlinePac\": false\r\n";
			config += "}";
			out.write(config);
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
