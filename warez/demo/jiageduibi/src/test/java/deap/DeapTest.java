package deap;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.x2017.util.HttpReqUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.List;

/**
 * Created by wenshiwei on 2017/2/23.
 */
public class DeapTest {

    @Test
    public void test01(){
       String html= HttpReqUtil.httpGetRequest("https://www.jd.com/");
        System.out.println(html);
        Document doc = Jsoup.parse(html);
        doc.getElementsByTag("a").forEach(x->{
            String url = x.attr("href");
            System.out.println(url);
        });
    }

    @Test
    public void test02() throws Exception{
        final WebClient webClient=new WebClient();
//        webClient.getOptions().setCssEnabled(false);//关闭css
//        webClient.getOptions().setJavaScriptEnabled(false);//关闭js
        final HtmlPage page=webClient.getPage("https://www.jd.com");
//        System.out.println(page.asXml());
        List<HtmlAnchor> achList=page.getAnchors();
        for(HtmlAnchor ach:achList){
            System.out.println(ach.getHrefAttribute());
        }
        webClient.close();
    }
}
