package cn.ziran;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

public class XDom4 {

  public static void main(String[] args) throws UnsupportedEncodingException, DocumentException {
    //System.out.println(getXml());
    String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
        + "<alipay><is_success>T</is_success><request>"
        + "<param name=\"trade_no\">2016040700001000880090017075</param>"
        + "<param name=\"_input_charset\">utf-8</param>"
        + "<param name=\"service\">single_trade_query</param>"
        + "<param name=\"partner\">2088211554961671</param></request><response>"
        + "<trade><buyer_email>lihan_1568@163.com</buyer_email><buyer_id>2088002086316880</buyer_id>"
        + "<discount>0.00</discount><flag_trade_locked>0</flag_trade_locked>"
        + "<gmt_create>2016-04-07 11:14:37</gmt_create>"
        + "<gmt_last_modified_time>2016-04-07 11:14:49</gmt_last_modified_time>"
        + "<gmt_payment>2016-04-07 11:14:49</gmt_payment>"
        + "<is_total_fee_adjust>F</is_total_fee_adjust><operator_role>B</operator_role>"
        + "<out_trade_no>c6848ffb521d4e69b775114372674934</out_trade_no>"
        + "<payment_type>1</payment_type><price>0.01</price><quantity>1</quantity>"
        + "<seller_email>zhang9192@vip.qq.com</seller_email>"
        + "<seller_id>2088211554961671</seller_id><subject>SO2016040700060003_风帆蓄电池江西总代理</subject>"
        + "<to_buyer_fee>0.00</to_buyer_fee><to_seller_fee>0.01</to_seller_fee>"
        + "<total_fee>0.01</total_fee><trade_no>2016040700001000880090017075</trade_no>"
        + "<trade_status>TRADE_SUCCESS</trade_status><use_coupon>F</use_coupon></trade>"
        + "</response><sign>75c19728e88147303be7f3183defa51b</sign><sign_type>MD5</sign_type>"
        + "</alipay>";
    SAXReader saxReader = new SAXReader();
    InputStream in=new ByteArrayInputStream(xml.getBytes("UTF-8"));
    Document doc=saxReader.read(in);
    Element root=doc.getRootElement();
    Map<String, String> map=new HashMap<>();
    ele2map2(map,root);
    System.out.println(map);
    //root.addElement("sign").setText("abckkkkallalalala");;
//    for(Element e:  (List<Element>)root.elements()){
//      if(e.elements().size()==0){
//        System.out.println(e.getName());
//        System.out.println(e.getTextTrim());
//      } else {
//        
//      }
//    }
    
    //System.out.println(root);
  }
  
  @SuppressWarnings("unchecked")
  static void ele2map2(Map<String, String> map, Element ele) {
    for(Element e:  (List<Element>)ele.elements()){
      if(e.elements().size()==0){
        if(StringUtils.isNotBlank(e.attributeValue("name"))){
          map.put(e.attributeValue("name") , e.getTextTrim());
          System.out.println(e.attributeValue("name") +" --> "+ e.getTextTrim());
        } else {
          map.put(e.getName() , e.getTextTrim());
          System.out.println(e.getName() +" --> "+ e.getTextTrim());
        }
      } else {
        ele2map2(map,e);
      }
    }
  }
  
  static void ele2map(Map<String, Object> map, Element ele) {
    //System.out.println(ele);
    // 获得当前节点的子节点
    @SuppressWarnings("unchecked")
    List<Element> elements = ele.elements();
    if (elements.size() == 0) {
      // 没有子节点说明当前节点是叶子节点，直接取值即可
      map.put(ele.getName(), ele.getText());
    } else if (elements.size() == 1) {
      // 只有一个子节点说明不用考虑list的情况，直接继续递归即可
      Map<String, Object> tempMap = new HashMap<String, Object>();
      ele2map(tempMap, elements.get(0));
      map.put(ele.getName(), tempMap);
    } else {
      // 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
      // 构造一个map用来去重
      Map<String, Object> tempMap = new HashMap<String, Object>();
      for (Element element : elements) {
        tempMap.put(element.getName(), null);
      }
      Set<String> keySet = tempMap.keySet();
      for (String string : keySet) {
        Namespace namespace = elements.get(0).getNamespace();
        List<Element> elements2 = ele.elements(new QName(string, namespace));
        // 如果同名的数目大于1则表示要构建list
        if (elements2.size() > 1) {
          List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
          for (Element element : elements2) {
            Map<String, Object> tempMap1 = new HashMap<String, Object>();
            ele2map(tempMap1, element);
            list.add(tempMap1);
          }
          map.put(string, list);
        } else {
          // 同名的数量不大于1则直接递归去
          Map<String, Object> tempMap1 = new HashMap<String, Object>();
          ele2map(tempMap1, elements2.get(0));
          map.put(string, tempMap1);
        }
      }
    }
  }
  

//  public static void main(String[] args) throws InterruptedException {
//    final String xml=""
//        +"<xml>"
//        +"<return_code><![CDATA[SUCCESS]]></return_code>"
//        +"<return_msg><![CDATA[OK]]></return_msg>"
//        +"<appid><![CDATA[wx2421b1c4370ec43b]]></appid>"
//        +"<mch_id><![CDATA[10000100]]></mch_id>"
//        +"<nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>"
//        +"<sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>"
//        +"<result_code><![CDATA[SUCCESS]]></result_code>"
//        +"<prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>"
//        +"<trade_type><![CDATA[JSAPI]]></trade_type>"
//        +"</xml>";
//    
//    for(int i=0;i<50;i++){
//      new Thread(new Runnable() {
//        public void run() {
//          Long l1=System.currentTimeMillis();
//          ScanPayResData model =  (ScanPayResData) Util.getObjectFromXML(xml, ScanPayResData.class);
//          System.out.println("--------"+(System.currentTimeMillis()-l1));
//        }
//      }).start();
//    
//    }
//    System.out.println("over-----------");
//  }
  
  public static String getXml() {
    String xml="";
    xml+="<xml>";
    //xml+="<book category=\"COOKING\">";
    xml+="  <title lang=\"en\">Everyday Italian</title>";
    xml+="  <author>Giada De Laurentiis</author>";
    xml+="  <year>2005</year>";
    xml+="  <price>30.00</price>";
//    xml+="</book>";
//    xml+="<book category=\"CHILDREN\">";
//    xml+="  <title lang=\"en\">Harry Potter</title>";
//    xml+="  <author>J K. Rowling</author>";
//    xml+="  <year>2005</year>";
//    xml+="  <price>29.99</price>";
//    xml+="</book>";
//    xml+="<book category=\"WEB\">";
//    xml+="  <title lang=\"en\">XQuery Kick Start</title>";
//    xml+="  <author>James McGovern</author>";
//    xml+="  <author>Per Bothner</author>";
//    xml+="  <author>Kurt Cagle</author>";
//    xml+="  <author>James Linn</author>";
//    xml+="  <author>Vaidyanathan Nagarajan</author>";
//    xml+="  <year>2003</year>";
//    xml+="  <price>49.99</price>";
//    xml+="</book>";
//    xml+="<book category=\"WEB\">";
//    xml+="  <title lang=\"en\">Learning XML</title>";
//    xml+="  <author>Erik T. Ray</author>";
//    xml+="  <year>2003</year>";
//    xml+="  <price>39.95</price>";
//    xml+="</book>";
    xml+="</xml>";
    return xml;
  }
  
}
