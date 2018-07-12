/**
 * FileName: AppConsumer
 * Author:   MITE
 * Date:     2018/7/11 16:53
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author MITE
 * @create 2018/7/11
 * @since 1.0.0
 */
public class AppConsumer {
    private static final String URL = "tcp://localhost:61616";//61616是MQ的默认端口
    private static final String TOPICNAME = "topic-test";

    public static void main(String[] args) throws JMSException {
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2、创建Connection
        javax.jms.Connection connection = connectionFactory.createConnection();
        //3、启动连接
        connection.start();
        //4、创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、创建一个目标
        Destination destination=session.createTopic(TOPICNAME);
        //6、创建一个订阅者
        MessageConsumer consumer = session.createConsumer(destination);
        //7、创建一个监听器，监听器是异步操作，
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage message1= (TextMessage) message;
                try {
                    System.out.println("接收"+message1.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //9、消费者监听停止后连接自动关闭
    }
}