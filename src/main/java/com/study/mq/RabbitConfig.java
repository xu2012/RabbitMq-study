package com.study.mq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUENAME = "chatbot";

    @Bean("queueA")
    public Queue a_queue() {
        return new Queue("QUEUE_A");
//        return QueueBuilder.durable("QUEUE_A").build();
    }

    @Bean("queueB")
    public Queue b_queue() {

        return new Queue("QUEUE_B");
//        return QueueBuilder.durable("QUEUE_B").build();
    }

    /**
     * direct：会把消息路由到哪些BindingKey和RoutingKey完全匹配的队列中。(exchange和routingKey需要一致)
     *
     * @return 例如：amqpTemplate.convertAndSend("fanoutExchange", "DIRECT_EXCHANGE_A", s);
     */
    @Bean("directExchange")
    public DirectExchange directExchange() {
//        return  new DirectExchange("")
        return (DirectExchange) ExchangeBuilder.directExchange("directExchange").durable(true).build();
    }

    /**
     * fanout：会把所有发送到该交换器的消息路由到所有与该交换器绑定的队列中。（发送的时候可以只指定exchange，不需要指定routingKey）
     *
     * @return 例如：amqpTemplate.convertAndSend("fanoutExchange", "", s);
     */
    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange("");
        return (FanoutExchange) ExchangeBuilder.fanoutExchange("fanoutExchange").durable(true).build();
    }


    @Bean
    public Binding bindDirectA(@Qualifier("queueA") Queue queue, @Qualifier("directExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("DIRECT_EXCHANGE_A").noargs();
    }

    @Bean
    public Binding bindDirectB(@Qualifier("queueB") Queue queue, @Qualifier("directExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("DIRECT_EXCHANGE_B").noargs();
    }

    @Bean
    public Binding bindFanoutA(@Qualifier("queueA") Queue queue, @Qualifier("fanoutExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("FANOUT_EXCHANGE_A").noargs();
    }

    @Bean
    public Binding bindFanouttB(@Qualifier("queueB") Queue queue, @Qualifier("fanoutExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("FANOUT_EXCHANGE_B").noargs();
    }
}
