package api.pay.combine.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pay.consts.PaymentSourceConst;

import api.pay.combine.dto.AliDirectPayDto;
import api.pay.combine.dto.AliMergePayDto;
import api.pay.combine.dto.AliMergePayItemDto;
import api.pay.combine.dto.ResData;
import api.pay.combine.service.PayService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class PayServiceTest {

  @Autowired
  private PayService payService;
  
  //网关支付
  @Test
  public void test01(){
    AliDirectPayDto aliPayDto=new AliDirectPayDto();
    //aliPayDto.setExtraCommonParam("{'id':1,'name':'wenshiwei'}");
    aliPayDto.setNotifyUrl("http://www.baidu.com");
    aliPayDto.setOrderId("test20160312006");
    aliPayDto.setReturnUrl("http://www.baidu.com");
    //aliPayDto.setSpDetail("菜单手机");
    aliPayDto.setSpName("手机");
    Long t=System.currentTimeMillis()+2*24*60*60*1000;
    aliPayDto.setTimeOut(new Date(t));
    aliPayDto.setTotalFee(1l);
    aliPayDto.setPaymentSource(PaymentSourceConst.PAYSOURCE_ORDER);
    ResData<String> resData= payService.aliDirectPay(aliPayDto);
    System.out.println(resData);
  }
  
//单笔查询
  @Test
  public void test02(){
    String xml=payService.aliTradeQuery("order20160118010", "cc71ff501f9e4086847f54dedf8d434b");
    System.out.println(xml);
  }
  
//合并支付
  @Test
  public void test03(){
    AliMergePayDto aliMergePayDto=new AliMergePayDto();
    aliMergePayDto.setNotifyUrl("http://www.baidu.com");
    aliMergePayDto.setReturnUrl("http://www.baidu.com");
    aliMergePayDto.setPaymentSource(PaymentSourceConst.PAYSOURCE_ORDER);
    
    List<AliMergePayItemDto> itemList=new ArrayList<AliMergePayItemDto>();
    AliMergePayItemDto itemDto=new AliMergePayItemDto();
    itemDto.setExtraCommonParam("{'id':001,'name':'zgtx'}");
    itemDto.setOrderId("order20160120001");
    itemDto.setSpDetail("huwei8650");
    itemDto.setSpName("手机");
    Long t=System.currentTimeMillis()+100*60*1000;
    itemDto.setTimeOut(new Date(t));
    itemDto.setTotalFee(1l);
    itemList.add(itemDto);
    
    itemDto=new AliMergePayItemDto();
    itemDto.setExtraCommonParam("{'id':2,'name':'wenshiwei'}");
    itemDto.setOrderId("order20160118007");
    itemDto.setSpDetail("2huwei8650");
    itemDto.setSpName("手机2");
    t=System.currentTimeMillis()+10*60*1000;
    itemDto.setTimeOut(new Date(t));
    itemDto.setTotalFee(1l);
    //itemList.add(itemDto);
    
    aliMergePayDto.setItemList(itemList);
    ResData<String> resData= payService.aliMergePay(aliMergePayDto);
    System.out.println(resData);
  }
  
//关闭交易
  @Test
  public void test04(){
    ResData<String> resData=payService.aliCloseTrade("order20160118010", "");
    System.out.println(resData);
  }
}
