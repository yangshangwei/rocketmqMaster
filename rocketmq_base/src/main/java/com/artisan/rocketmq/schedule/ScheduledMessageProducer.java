package com.artisan.rocketmq.schedule;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Date;

/**
 * @author 小工匠
 * @version v1.0
 * @create 2019-11-10 17:23
 * @motto show me the code ,change the word
 * @blog https://artisan.blog.csdn.net/
 * @description
 **/

public class ScheduledMessageProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("ExampleConsumer");

        producer.setNamesrvAddr("192.168.18.130:9876;192.168.18.131:9876");

        producer.start();
        int totalMessagesToSend = 3;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TestTopic", ("Hello scheduled message " + i).getBytes());
            //延时消费  6-->2分钟
            message.setDelayTimeLevel(6);
            // Send the message
            producer.send(message);
        }

        System.out.printf("message send is completed .%n" + new Date());
        producer.shutdown();
    }
}
