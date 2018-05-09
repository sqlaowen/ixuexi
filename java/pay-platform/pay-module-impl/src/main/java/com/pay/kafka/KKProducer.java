package com.pay.kafka;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

public class KKProducer {

  private static Logger log = LoggerFactory.getLogger(KKProducer.class);

  private static MessageChannel channel;
  public void setChannel(MessageChannel channel) {
    KKProducer.channel = channel;
  }

  public static void kkMsgProducer(String topic, String strMsg) {
    log.debug(String.format("生产者kafka,topic:%s,消息:%s,时间:%s", topic, strMsg, new Date()));
    channel.send(MessageBuilder.withPayload(strMsg).setHeader(KafkaHeaders.TOPIC, topic).build());
  }
}
