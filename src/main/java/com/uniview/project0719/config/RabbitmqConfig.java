package com.uniview.project0719.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zx
 * @date ：Created in 2024/8/2 12:10
 * @description：
 * @modified By：
 * @version: $
 */
//@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue checkStatus(){
        return new Queue("orderStatusQueue");
    }
    @Bean
    public CustomExchange delayExchange() {
        Map map = new HashMap<>();
        map.put("x-delayed-type", "direct");
        return new CustomExchange("orderDelayExchange", "x-delayed-message", true, false, map);
    }
    @Bean
    public Binding checkStatusToDelayExchange(Queue checkStatus, CustomExchange delayExchange) {
        return BindingBuilder.bind(checkStatus).to(delayExchange).with("checkStatus").noargs();
    }
}
