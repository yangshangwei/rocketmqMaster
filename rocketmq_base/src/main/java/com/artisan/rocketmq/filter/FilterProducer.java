package com.artisan.rocketmq.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author 小工匠
 * @version v1.0
 * @create 2019-11-11 23:30
 * @motto show me the code ,change the word
 * @blog https://artisan.blog.csdn.net/
 * @description
 **/
public class FilterProducer {


    /***
     * TAG-FILTER-1000 ---> 布隆过滤器
     * 过滤掉的那些消息。直接就跳过了么。下次就不会继续过滤这些了。是么。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("filter_sample_group");
        producer.setNamesrvAddr("192.168.18.130:9876;192.168.18.131:9876");
        producer.start();

        for (int i = 0; i < 3; i++) {
            Message msg = new Message("TopicFilter",
                    "TAG-FILTER",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            // Set some properties. 生产者设置属性，消费者端通过Tag+该属性定制消息
            msg.putUserProperty("a", String.valueOf(i));
            if (i % 2 == 0) {
                msg.putUserProperty("b", "artisan");
            } else {
                msg.putUserProperty("b", "smart artisan");
            }
            producer.send(msg);
        }

        producer.shutdown();
    }

}
