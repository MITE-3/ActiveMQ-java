/**
 * FileName: AppProducer
 * Author:   MITE
 * Date:     2018/7/11 16:15
 * History:
 * <author>          <time>          <version>          <desc>
 * ��������           �޸�ʱ��           �汾��              ����
 */
package com.imooc.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author MITE
 * @create 2018/7/11
 * @since 1.0.0
 */
public class AppProducer {
    private static final String URL = "tcp://localhost:61616";//61616��MQ��Ĭ�϶˿�
    private static final String QUEUENAME = "queue-test";

    public static void main(String[] args) throws JMSException {
        //1������ConnectionFactory
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(URL);
        //2������Connection
        Connection connection = connectionFactory.createConnection();
        //3����������
        connection.start();
        //4�������Ự
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//��һ��������ʾ�Ƿ��������д����ڶ���������ʾ�Զ�Ӧ�����
        //5������һ��Ŀ��
        Destination destination=session.createQueue(QUEUENAME);
        //6������һ��������
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 50; i++) {
            //7��������Ϣ
            TextMessage textMessage=session.createTextMessage("������Ϣ"+i);
            //8��������Ϣ
            producer.send(textMessage);
            System.out.println("������Ϣ"+i);
        }
        //9���ر�����
        connection.close();
    }
}