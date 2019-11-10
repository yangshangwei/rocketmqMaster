package com.artisan.rocketmq.simple.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 小工匠
 * @version v1.0
 * @create 2019-11-10 12:18
 * @motto show me the code ,change the word
 * @blog https://artisan.blog.csdn.net/
 * @description 异步消息
 **/

public class AsyncProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("Artisan_ProducerGroup");
        producer.setNamesrvAddr("192.168.18.130:9876;192.168.18.131:9876");

        //设置发送失败重试机制
        producer.setRetryTimesWhenSendAsyncFailed(5);

        producer.start();

        int messageCount = 1;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            Message msg = new Message("TopicAsyn",
                    "TagAsyn",
                    "OrderID188",
                    "I m sending msg content ArtisanInfo".getBytes(RemotingHelper.DEFAULT_CHARSET));
            //消息发送成功后，执行回调函数
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());

                    System.out.printf("%-10d OK %s %n", index,
                            sendResult);
                }
                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await(5, TimeUnit.SECONDS);

        producer.shutdown();

    }
}
