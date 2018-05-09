package x2016;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.x2016.service.WSService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class WSTest {
  
  @Autowired
  private WSService wsClient;
  
  @Test
  public void test01(){
   String str= wsClient.xxFn("wsService test success !");
   System.out.println("----> "+str);
  }

//  public static void main(String[] args) {
// // 创建WebService客户端代理工厂  
//    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
//    // 注册WebService接口  
//    factory.setServiceClass(WSService.class);  
//    // 设置WebService地址  
//    factory.setAddress("http://localhost:8080/ws/xx");  
//    WSService ws = (WSService) factory.create();  
//    System.out.println("message context is: " + ws.xxFn("cxf webservice"+System.currentTimeMillis()));  
//  }
}
