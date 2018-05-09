package cn.ziran.xredis;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/notenv/applicationContext.xml")
public class Dom4jTest {

  @Test
  public void test01() throws Exception{
    SAXReader asxReader = new SAXReader();
    Document doc = asxReader.read(this.getClass().getResourceAsStream("/env/sms.cfg.xml"));
    System.out.println("读取sms.cfg.xml配制成功...");
    List list = doc.getRootElement().elements("sms");
    
  }
  
  @Value("sms.cfg")
  private String smsCfg;
  @Test
  public void test02() throws Exception{
    System.out.println(smsCfg);
  }
  
}
