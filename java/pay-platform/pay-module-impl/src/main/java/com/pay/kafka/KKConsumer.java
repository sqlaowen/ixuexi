package com.pay.kafka;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KKConsumer {

  private static Logger log = LoggerFactory.getLogger(KKConsumer.class);

  public void kkMsgConsumer(Map<String, Map<Integer, String>> msgs) {
    for (Map.Entry<String, Map<Integer, String>> entry : msgs.entrySet()) {
      LinkedHashMap<Integer, String> messages = (LinkedHashMap<Integer, String>) entry.getValue();
      Set<Integer> keys = messages.keySet();
      for (Integer i : keys) {
        log.debug("消费者kafka,Partition:{}", i);
      }
      Collection<String> values = messages.values();
      for (Iterator<String> iterator = values.iterator(); iterator.hasNext();) {
        String msg = iterator.next();
        log.debug("消费者kafka,topic:{},消息:{}", entry.getKey(), msg);
      }
    }
  }
}
