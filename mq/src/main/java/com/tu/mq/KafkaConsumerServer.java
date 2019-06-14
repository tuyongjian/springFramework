package com.tu.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * Created by tuyongjian on 2019/3/5.
 * 消费者监听,自动监听是否有消息需要消费
 */
public class KafkaConsumerServer implements MessageListener<String, String> {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumerServer.class);

    public void onMessage(ConsumerRecord<String, String> record) {
        logger.info("--------kafka开始消费---");
        String topic  = record.topic();
        String key = record.key();
        String value = record.value();
        long offset = record.offset();
        int partition = record.partition();

        logger.info("topic -----------"+topic);
        logger.info("key -------------"+key);
        logger.info("value -------------"+value);
        logger.info("offset -------------"+offset);
        logger.info("partition -------------"+partition);
        logger.info("--------kafka消费结束---");

    }
}
