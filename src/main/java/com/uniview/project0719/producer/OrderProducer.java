package com.uniview.project0719.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：zx
 * @date ：Created in 2024/8/2 12:17
 * @description：
 * @modified By：
 * @version: $
 */
//@Component
public class OrderProducer {
//    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(String msg,String routeKey,Integer ttl){
        rabbitTemplate.convertAndSend("orderDelayExchange", routeKey,msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(ttl);
                return message;
            }
        });
    }
}
