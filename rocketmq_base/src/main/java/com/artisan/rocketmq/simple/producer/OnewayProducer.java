package com.artisan.rocketmq.simple.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author 小工匠
 * @version v1.0
 * @create 2019-11-10 12:45
 * @motto show me the code ,change the word
 * @blog https://artisan.blog.csdn.net/
 * @description
 **/

public class OnewayProducer {

    public static void main(String[] args) throws Exception{

        DefaultMQProducer producer = new DefaultMQProducer("tl_message_group");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.18.130:9876;192.168.18.131:9876");

        producer.setSendMsgTimeout(10000);

        producer.start();
        for (int i = 0; i < 1; i++) {
            Message msg = new Message("TopicOneWay" /* Topic */,
                    "TagSendOne" /* Tag */,
                    "OrderID198",
                    ("Hello RocketMQ test i " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            producer.sendOneway(msg);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
