/**
 * FileName: AppConsumer
 * Author:   MITE
 * Date:     2018/7/11 16:53
 * History:
 * <author>          <time>          <version>          <desc>
 * ��������           �޸�ʱ��           �汾��              ����
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
    private static final String URL = "tcp://localhost:61616";//61616��MQ��Ĭ�϶˿�
    private static final String TOPICNAME = "topic-test";

    public static void main(String[] args) throws JMSException {
        //1������ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2������Connection
        javax.jms.Connection connection = connectionFactory.createConnection();
        //3����������
        connection.start();
        //4�������Ự
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5������һ��Ŀ��
        Destination destination=session.createTopic(TOPICNAME);
        //6������һ��������
        MessageConsumer consumer = session.createConsumer(destination);
        //7������һ�������������������첽������
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage message1= (TextMessage) message;
                try {
                    System.out.println("����"+message1.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //9�������߼���ֹͣ�������Զ��ر�
    }
}