package com.study.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class Sender {

    public static final String QUEUENAME = "chatbot";
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * fanoutExchange 模式发送
     * @param s
     */
    public void sendBroadcast(String s) {
//        String context = "hello " + new Date();
//        System.out.println("Sender:" + context);
//        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.amqpTemplate.convertAndSend("fanoutExchange", "", s);
    }

    /**
     * derict 的方式发送
     * @param s
     */
    public void derictA(String s) {
        this.amqpTemplate.convertAndSend("directExchange", "DIRECT_EXCHANGE_A", s);
    }

    /**
     * derict 的方式发送
     * @param s
     */
    public void derictB(String s) {
        this.amqpTemplate.convertAndSend("directExchange", "DIRECT_EXCHANGE_B", s);
    }
    /**
     * derict 的方式发送 但是没有binding key
     * @param s
     */
    public void derictNull(String s) {
        this.amqpTemplate.convertAndSend("directExchange", "", s);
    }
}
