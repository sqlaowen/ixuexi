package cn.ziran.xutil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class XXuuid {

  public static void main(String[] args) {
    final List<Integer> list1=new ArrayList<Integer>();
    final List<Integer> list2=new ArrayList<Integer>();
    
    for(int j=0;j<200;j++){
      new Thread(new Runnable() {
        public void run() {
          Integer n=null;
          for(int i=0;i<1000;i++){
            n= Math.abs(UUID.randomUUID().hashCode());
            if(list1.contains(n)){
              list2.add(n);
              System.out.println(Thread.currentThread().getName()+"  "+list2.size());
            } else{
              list1.add(n);
            }
          }
        }
      },"线程"+j).start();
      
      //System.out.println(StringUtils.join(list2, ","));
    }
  }
}
