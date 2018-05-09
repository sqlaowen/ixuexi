package cn.ziran.xredis;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronExpression;

public class XCronParse {

  public static void main(String[] args) throws Exception {

    CronExpression expression = new CronExpression("0 2/7 * * * ?");
    
    Date d = expression.getNextValidTimeAfter(new Date());
    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
    for(int i=0;i<20;i++){
      d = expression.getNextValidTimeAfter(d);
      System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
    }
    
  }
}
