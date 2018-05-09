package service;

import impl.laowen.utils.HttpReqUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iyou on 2017/2/23.
 */
public class Test1 {
    public static void main(String[] args) {
//        String s="//list.jd.com/list.html?cat=1713,3259,3333&page=1&sort=sort_totalsales15_desc&trans=1&JL=4_2_0#J_main";
//        s="//list.jd.com/1713-3287-3797.html";
//        Pattern pattern = Pattern.compile("(\\/\\/list\\.jd\\.com\\/)(\\d+)-(\\d+)-(\\d+)\\.html");
//        Matcher matcher= pattern.matcher(s);
//        if(matcher.find()){
//           // System.out.println(String.format("<prop key=\"%s\">%s</prop>",x.text().trim(),"https:"+matcher.group(1)+matcher.group(2)));
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
//            System.out.println(matcher.group(4));
//        }

//        Pattern pattern = Pattern.compile("(\\/\\/list\\.jd\\.com\\/list.html\\?).*?(cat=\\d+,\\d+,\\d+)(.*)");
//        Matcher matcher= pattern.matcher(s);
//        if(matcher.find()){
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//        }

        String url="http://localhost:8080";
        url="https://www.jd.com/allSort.aspx";
        String html=HttpReqUtil.httpGetRequest(url);
        Document doc = Jsoup.parse(html);
        doc.getElementsByTag("a").forEach(x->{
            String href=x.attr("href").trim();
            if(href.matches("\\/\\/list(?:-chaoshi)?\\.jd\\.com\\/list.html.*")){
                Pattern pattern = Pattern.compile("(\\/\\/list(?:-chaoshi)?\\.jd\\.com\\/list\\.html\\?).*?(cat=\\d+,\\d+,\\d+).*?");
                Matcher matcher= pattern.matcher(href);
                if(matcher.find()){
                    //System.out.println(String.format("<prop key=\"%s\">%s</prop>",x.text().trim(),"https:"+matcher.group(1)+matcher.group(2)));
//                    System.out.println(matcher.group(1));
//                    System.out.println(matcher.group(2));
                }
                //System.out.println(String.format("<prop key=\"%s\">%s</prop>",x.text().trim(),"https:"+href));
            }
//            if(href.matches("\\/\\/list\\.jd\\.com\\/\\d+-\\d+-\\d+\\.html")){
//                Pattern pattern = Pattern.compile("(\\/\\/list\\.jd\\.com\\/)(\\d+)-(\\d+)-(\\d+)\\.html");
//                Matcher matcher= pattern.matcher(href);
//                if(matcher.find()){
//                    System.out.println(String.format("<prop key=\"%s\">%s</prop>",x.text().trim(),
//                            "https:"+matcher.group(1)+"list.html?cat="+
//                                    matcher.group(2)+","+
//                                    matcher.group(3)+","+
//                                    matcher.group(4)));
////                    System.out.println(matcher.group(1));
////                    System.out.println(matcher.group(2));
//                }
//            }
            if(href.matches("\\/\\/channel\\.jd\\.com\\/\\d+-\\d+\\.html")){
                Pattern pattern = Pattern.compile("(\\/\\/channel\\.jd\\.com\\/)(\\d+)-(\\d+)\\.html");
                Matcher matcher= pattern.matcher(href);
                if(matcher.find()){
                    System.out.println(String.format("<prop key=\"%s\">%s</prop>",x.text().trim(),
                            "https:"+matcher.group(1)+
                                    matcher.group(2)+"-"+
                                    matcher.group(3)+".html"));
//                    System.out.println(matcher.group(1));
//                    System.out.println(matcher.group(2));
                }
            }
        });

//        System.out.println("---------------------------");
//        System.out.println("---------------------------");
//        System.out.println("---------------------------");
//        doc.getElementsByTag("a").forEach(x->{
//            String href=x.attr("href").trim();
//            if(!href.matches("\\/\\/list\\.jd\\.com\\/list.html.*")){
//                System.out.println(String.format("<prop key=\"%s\">%s</prop>",x.text().trim(),href));
//            }
//        });
    }
}
