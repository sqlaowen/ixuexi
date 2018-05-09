package cn.ziran.ejob;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Created by wenshiwei on 2016/12/2.
 */
public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(JSON.toJSONString(shardingContext));
////        try {
//            switch (shardingContext.getShardingItem()) {
//                case 0:
////                    Thread.sleep(1000);
//                    System.out.println("do something by sharding item 0");
//                    break;
//                case 1:
////                    Thread.sleep(2000);
//                    System.out.println("do something by sharding item 1");
//                    break;
//                case 2:
//                    System.out.println("do something by sharding item 2");
//                    break;
//                default:
////                    Thread.sleep(3000);
//                    System.out.println("此项是间谍...");
//                    break;
//            }
////        } catch (InterruptedException e) {
////            System.out.println("当前线程:%s, sleep 异常:%s");
////        }
    }
}
