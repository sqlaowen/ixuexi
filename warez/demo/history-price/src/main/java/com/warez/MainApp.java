package com.warez;

import com.warez.utils.HttpReqUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xxx on 2017/1/26.
 */
public class MainApp {
    private Logger log= LoggerFactory.getLogger(MainApp.class);
    public static void main(String[] args) {

        String preUrl = "http://www.02xxoo.com";
        String urlFmt = "http://www.02xxoo.com/news/other/19_%s.html";
        for (int i = 1; i < 172; i++) {
            String url = String.format(urlFmt, i);

            String html = getHtml(url);
            if (StringUtils.isBlank(html)) {
                System.out.println("url请求3次均失败");
                continue;
            }
            //System.out.println(html);
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select("body > div:nth-child(2) > div > div.main > ul");
            elements.forEach(x -> {
                x.select("li > a").forEach(y -> {

                    String html2 = getHtml(preUrl + y.attr("href"));
                    Document doc2 = Jsoup.parse(html2);
                    String title = doc2.select("body > div:nth-child(2) > div > div.main > h1").get(0).text();
                    saveTxt(title);
                    saveTxt("\r\n\r\n");
                    String txt = doc2.select("body > div:nth-child(2) > div > div.main > div.news").get(0).html();
                    txt=txt.replaceAll("<strong>.*<\\/strong>","");
                    saveTxt(txt.replaceAll("<br>", "\r\n").replaceAll("<p></p>", ""));
                    saveTxt("\r\n--------------------------------------------------------------------------");
                    saveTxt("\r\n--------------------------------------------------------------------------");
                    saveTxt("\r\n--------------------------------------------------------------------------\r\n\r\n");
                    System.out.println("写入一个小说成功。。。");
                });
                System.out.println("=========》成功一页《==========");
            });
        }
    }

    static String getHtml(String url) {
        String html = HttpReqUtil.httpGetRequest(url, "gb2312");
        if (StringUtils.isBlank(html)) {
            for (int j = 0; j < 2; j++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("线程sleep异常：" + e.getMessage());
                }
                html = HttpReqUtil.httpGetRequest(url);
                if (StringUtils.isNotBlank(html)) {
                    break;
                }
            }
        }

        return html;
    }

    static void saveTxt(String txt) {

        try {
            File file = new File("d:/xiaoshu.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(txt.getBytes("utf-8"));
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("写入文件error:" + e.getMessage());
        }
    }

}
