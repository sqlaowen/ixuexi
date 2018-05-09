package com.warez;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Created by iyou on 2016/12/3.
 */
public class JiandanJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(JSON.toJSONString(shardingContext));

        switch (shardingContext.getShardingItem()) {
            case 0:
                System.out.println("do something by sharding item 0");
                break;
            case 1:
                System.out.println("do something by sharding item 1");
                break;
            case 2:
                System.out.println("do something by sharding item 2");
                break;
            default:
                System.out.println("此项是间谍...");
                break;
        }
    }
}
