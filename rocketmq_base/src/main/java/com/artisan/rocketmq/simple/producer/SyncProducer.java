package com.artisan.rocketmq.simple.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author 小工匠
 * @version v1.0
 * @create 2019-11-10 1:46
 * @motto show me the code ,change the word
 * @blog https://artisan.blog.csdn.net/
 * @description 同步发送消息
 **/

public class SyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("Artisan_ProducerGroup");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.18.130:9876;192.168.18.131:9876");
        // 设置超时时间，默认3秒
        producer.setSendMsgTimeout(10_000);
        //Launch the instance.
        producer.start();
//        for (int i = 0; i < 100; i++) {
//            //Create a message instance, specifying topic, tag and message body.
//            Message msg = new Message("TopicArtisan" /* Topic */,
//                    "TagArtisan" /* Tag */,
//                    ("Artisan:Hello RocketMQ  " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//            );
//            //Call send message to deliver message to one of brokers.
//            SendResult sendResult = producer.send(msg);
//            System.out.printf("%s%n", sendResult);
//        }


        //Create a message instance, specifying topic, tag and message body.
        Message msg = new Message("TopicArtisan" /* Topic */,
                "TagArtisan" /* Tag */,
                ("Artisan:Hello RocketMQ  ").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
        );
        //Call send message to deliver message to one of brokers.
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n", sendResult);

        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}