package api.authc.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.authc.single.dao.RefUserRoleMapper;

import api.authc.single.service.RefUserRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class RefUserRoleServiceTest {

  @Autowired
  private RefUserRoleService refUserRoleService;
  
  @Autowired
  private RefUserRoleMapper refUserRoleMapper;
  
  @Test
  public void test01(){
    List<String> list=new ArrayList<String>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");
    refUserRoleService.saveBatch("1", list);
  }
  
  @Test
  public void test02(){
    List<Map<String,Object>> map= refUserRoleMapper.findXX();
   System.out.println(map);
  }
  
  @Test
  public void test03(){
    List<Map<String,Object>> mapList=new ArrayList<>();
    Map<String,Object> map=new HashMap<>();
    map.put("refId", "r1");
    map.put("userId","u1");
    map.put("roleId", "ro1");
    mapList.add(map);
    
    map=new HashMap<>();
    map.put("refId", "r2");
    map.put("userId","u2");
    map.put("roleId", "ro2");
    mapList.add(map);
    
    int x= refUserRoleMapper.saveBatch(mapList);
    System.out.println(x);
  }
}
