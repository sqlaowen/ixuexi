package com.luo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application.xml")
public class RedisTestServiceTest {// extends SpringTestCase {

  @Autowired
  private RedisTestService redisTestService;

  @Test
  public void getTimestampTest() throws InterruptedException {
    System.out.println("第一次调用：" + redisTestService.getTimestamp("param"));
    Thread.sleep(2000);
    System.out.println("2秒之后调用：" + redisTestService.getTimestamp("param"));
    Thread.sleep(11000);
    System.out.println("再过11秒之后调用：" + redisTestService.getTimestamp("param"));
  }
}
