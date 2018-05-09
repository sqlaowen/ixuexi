package cn.ziran.xredis;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/springRedis/applicationContext-redis.xml")
public class RedisServiceImplTest {

  @Autowired
  private RedisService redisService;
  
  @Test
  public void test01() throws InterruptedException{
    redisService.setData("key1", "value2");
    System.out.println(redisService.getData("key1"));
    redisService.resetTimeOut("key1", 1);
    Thread.sleep(1500);
    System.out.println(redisService.getData("key1"));
    
    redisService.setHashData("hashk1", "hk1", 1+"");
    redisService.setHashData("hashk1", "hk1", "abc");
    redisService.setHashData("hashk1", "hk2", 11+"");
    redisService.setHashData("hashk1", "hk3", 22+"");
    redisService.setHashData("hashk1", "hk4", 33+"");
    redisService.setHashData("hashk1", "hk5", 44+"");
    redisService.setHashData("hashk1", "hk6", 55+"");
    
    Map<String, List<String>> ss = redisService.getHashKeysAndValues("hashk1");
    System.out.println(ss);
  }
  
  @Test
  public void testSetDataStringStringInt() {
    String key ="stack2";
    redisService.pushStack(key, "1");
    redisService.pushStack(key, "2");
    redisService.pushStack(key, "3");
    redisService.pushStack(key, "4");
    redisService.pushStack(key, "5");
    redisService.popStack(key);
    System.out.println(redisService.index(key, 0));
    System.out.println(redisService.index(key, 1));
    System.out.println(redisService.index(key, 2));
    System.out.println(redisService.index(key, 3));
  }

  @Test
  public void testSetDataStringString() {
    String key ="stack1";
    redisService.remove(key, 2, "3");
    System.out.println(redisService.index(key, 0));
    System.out.println(redisService.index(key, 1));
    System.out.println(redisService.index(key, 2));
    System.out.println(redisService.index(key, 3));
  }

  @Test
  public void testGetData() {
    
    String key ="duilie5";
    Long r = redisService.inQueue(key, null);
    redisService.inQueue(key, "2");
    redisService.inQueue(key, "3");
    redisService.inQueue(key, "4");
    redisService.inQueue(key, "5");
    redisService.outQueue(key);
    System.out.println(redisService.index(key, 0));
    System.out.println(redisService.index(key, 1));
    System.out.println(redisService.index(key, 2));
    System.out.println(redisService.index(key, 3));
  }

  @Test
  public void testGetDatas() {
    String SESSION_KEY = "store-app.session.menulist.ceshi123";
    redisService.delHashData("asdfsa","sadfsdf");
  }

  @Test
  public void testDel() {
    
    String SESSION_KEY = "store-app.session.menulist.ceshi123";
    String value ="[{\"menuID\":\"/factory/index.shtml\",\"menuName\":\"首页\",\"menuUrl\":\"\"},{\"menuID\":\"myStore\",\"menuName\":\"店铺管理\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"\",\"menuName\":\"店铺基础设置\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"storeBaseSet\",\"menuName\":\"店铺基础设置\",\"menuUrl\":\"/factoryStoreBase/getFactoryStoreBase.shtml\"}]},{\"menuID\":\"\",\"menuName\":\"地址管理\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"addressManager\",\"menuName\":\"地址管理\",\"menuUrl\":\"/distriRecAddress/getReceiveAddress.shtml\"}]},{\"menuID\":\"\",\"menuName\":\"店铺公告\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"storeGongGao\",\"menuName\":\"店铺公告\",\"menuUrl\":\"/mystore/announcement/queryList.shtml?jump=1\"}]}]},{\"menuID\":\"qijiandian\",\"menuName\":\"我的旗舰店\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"\",\"menuName\":\"我的旗舰店\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"myStore\",\"menuName\":\"我的旗舰店\",\"menuUrl\":\"/distributor/index.shtml\"},{\"menuID\":\"distributorBaseSet\",\"menuName\":\"店铺基础设置\",\"menuUrl\":\"/distributor/baseInfo.shtml\"}]},{\"menuID\":\"\",\"menuName\":\"店铺公告\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"distributorGongGao\",\"menuName\":\"店铺公告\",\"menuUrl\":\"/mystore/announcement/queryList.shtml?jump=0\"}]},{\"menuID\":\"\",\"menuName\":\"签约管理\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"contractManagement\",\"menuName\":\"签约管理\",\"menuUrl\":\"/myflagship/contractManage/index.shtml\"}]}]},{\"menuID\":\"goods\",\"menuName\":\"商品\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"\",\"menuName\":\"商品审核\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"stayGoods\",\"menuName\":\"待提交商品\",\"menuUrl\":\"/distribution/staysubmit.shtml\"},{\"menuID\":\"approvalGoods\",\"menuName\":\"待审核商品\",\"menuUrl\":\"/distribution/approval.shtml\"},{\"menuID\":\"rejectGoods\",\"menuName\":\"驳回商品\",\"menuUrl\":\"/distribution/rejectgoods.shtml\"}]},{\"menuID\":\"\",\"menuName\":\"商品商城\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"salegoods\",\"menuName\":\"在售商品\",\"menuUrl\":\"/distribution/salegoods.shtml\"},{\"menuID\":\"stockoutgoods\",\"menuName\":\"缺货商品\",\"menuUrl\":\"/distribution/stockoutgoods.shtml\"},{\"menuID\":\"soldoutgoods\",\"menuName\":\"下架商品\",\"menuUrl\":\"/distribution/soldoutgoods.shtml\"}]},{\"menuID\":\"\",\"menuName\":\"正品商品\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"salegoods1\",\"menuName\":\"在售商品\",\"menuUrl\":\"/distribution/auth/salegoods.shtml\"},{\"menuID\":\"stockoutgoods1\",\"menuName\":\"缺货商品\",\"menuUrl\":\"/distribution/auth/stockoutgoods.shtml\"},{\"menuID\":\"soldoutgoods1\",\"menuName\":\"下架商品\",\"menuUrl\":\"/distribution/auth/soldoutgoods.shtml\"}]}]},{\"menuID\":\"trade\",\"menuName\":\"交易\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"\",\"menuName\":\"订单管理\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"dai1\",\"menuName\":\"待付款订单\",\"menuUrl\":\"/orderManagement/queryOrdersByDistributor.shtml?flag=2\"},{\"menuID\":\"dai2\",\"menuName\":\"待确认收货订单\",\"menuUrl\":\"/orderManagement/queryOrdersByDistributor.shtml?flag=4\"},{\"menuID\":\"dai3\",\"menuName\":\"交易完成订单\",\"menuUrl\":\"/orderManagement/queryOrdersByDistributor.shtml?flag=6\"},{\"menuID\":\"dai4\",\"menuName\":\"交易关闭订单\",\"menuUrl\":\"/orderManagement/queryOrdersByDistributor.shtml?flag=7\"},{\"menuID\":\"dai5\",\"menuName\":\"所有订单\",\"menuUrl\":\"/orderManagement/queryOrdersByDistributor.shtml?flag=9\"},{\"menuID\":\"dai6\",\"menuName\":\"订单商品明细\",\"menuUrl\":\"/orderManagement/queryOrdersByDistributor.shtml?flag=99\"}]}]},{\"menuID\":\"storeManager\",\"menuName\":\"商品管理\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"\",\"menuName\":\"添加新商品\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"selectCategory\",\"menuName\":\"添加新商品\",\"menuUrl\":\"/category/selectCategory.shtml\"}]},{\"menuID\":\"\",\"menuName\":\"商品审核\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"toStaySubmitGoods\",\"menuName\":\"待提交商品\",\"menuUrl\":\"/factory/toStaySubmitGoodsList.shtml\"},{\"menuID\":\"toStayApprovalGoods\",\"menuName\":\"待审核商品\",\"menuUrl\":\"/factory/toStayApprovalGoodsList.shtml\"},{\"menuID\":\"toRejectGoods\",\"menuName\":\"驳回商品\",\"menuUrl\":\"/factory/toRejectGoodsList.shtml\"}]},{\"menuID\":\"\",\"menuName\":\"在售商品\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"toSaleGoods\",\"menuName\":\"在售商品\",\"menuUrl\":\"/factory/toSaleGoodsList.shtml\"},{\"menuID\":\"toSoldOutGoods\",\"menuName\":\"下架商品\",\"menuUrl\":\"/factory/toSoldOutGoodsList.shtml\"}]},{\"menuID\":\"\",\"menuName\":\"商品回收站\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"togoodsRecycle\",\"menuName\":\"商品回收站\",\"menuUrl\":\"/factory/togoodsRecycleList.shtml\"}]}]},{\"menuID\":\"distribution\",\"menuName\":\"经销\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"\",\"menuName\":\"招商管理\",\"menuUrl\":\"\",\"menus\":[{\"menuID\":\"merchsManage1\",\"menuName\":\"招商发布\",\"menuUrl\":\"/distribution/merchsManage/merchsPublish/index.shtml\"},{\"menuID\":\"merchsManage2\",\"menuName\":\"招商申请\",\"menuUrl\":\"/distribution/merchsManage/merchsApply/index.shtml?flag=0\"},{\"menuID\":\"merchsManage3\",\"menuName\":\"招商详情\",\"menuUrl\":\"/distribution/merchsManage/merchsPublish/detail.shtml\"},{\"menuID\":\"merchsManage4\",\"menuName\":\"经销商权限\",\"menuUrl\":\"/distribution/author/list.shtml\"}]}]}]";
    redisService.setData(SESSION_KEY ,value,30*60);
    
  }
}
