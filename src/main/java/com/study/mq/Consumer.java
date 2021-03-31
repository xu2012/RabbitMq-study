package com.study.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(queues = {"QUEUE_A"})
    public void queueA(String s) {
        System.out.println("queueA received:-----" + s);
    }

    @RabbitListener(queues = {"QUEUE_B"})
    public void queueB(String s) {
        System.out.println("queueB received:-----" + s);
    }
}
