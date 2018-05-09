package com.warez.algorithm;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by iyou on 2017/1/14.
 */
public class DatabaseShardingStrategy implements SingleKeyDatabaseShardingAlgorithm<String> {

    private Logger log = LoggerFactory.getLogger(DatabaseShardingStrategy.class);

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
        log.info("DatabaseShardingStrategy.doEqualSharding -> availableTargetNames : {}", availableTargetNames);
        log.info("DatabaseShardingStrategy.doEqualSharding -> shardingValue : {}", JSON.toJSONString(shardingValue));
        for (String each : availableTargetNames) {
            if (each.endsWith(Math.abs(shardingValue.getValue().hashCode()) % 2 + "")) { //分2库4表
                log.info("DatabaseShardingStrategy.doEqualSharding :{}", each);
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<String> values = shardingValue.getValues();
        for (String value : values) {
            for (String each : availableTargetNames) {
                if (each.endsWith(Math.abs(value.hashCode()) % 2 + "")) {
                    result.add(each);
                }
            }
        }
        log.info("DatabaseShardingStrategy.doInSharding : {}", result);
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
        log.info("DatabaseShardingStrategy.doInSharding -> availableTargetNames : {}", availableTargetNames);
        log.info("DatabaseShardingStrategy.doInSharding -> shardingValue : {}", JSON.toJSONString(shardingValue));
        return null;
    }
}
