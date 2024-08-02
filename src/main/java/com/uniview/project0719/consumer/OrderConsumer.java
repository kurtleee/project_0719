package com.uniview.project0719.consumer;

import com.rabbitmq.client.Channel;
import com.uniview.project0719.entity.Sort;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.repository.SortRepository;
import com.uniview.project0719.repository.UserOrderRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ：zx
 * @date ：Created in 2024/8/2 12:26
 * @description：
 * @modified By：
 * @version: $
 */
//@Component
public class OrderConsumer {
//    @Autowired
    private UserOrderRepository userOrderRepository;
//    @Autowired
    private SortRepository sortRepository;

    @RabbitListener(queues = "checkOrderStatus")
    public void receiver(String msg, Channel channel, Message message) {
        UserOrder userOrderByOrderId = userOrderRepository.findUserOrderByOrderId(msg);
        if (userOrderByOrderId.getStatus() == 1) {
            userOrderByOrderId.setStatus(4);
            userOrderRepository.save(userOrderByOrderId);
        }else {
            Sort sort = new Sort();
            sort.setUserOrder(userOrderByOrderId);
            sort.setStatus(2);
            sortRepository.save(sort);
        }
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
