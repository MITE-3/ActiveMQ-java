/**
 * FileName: AppProducer
 * Author:   MITE
 * Date:     2018/7/11 16:15
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
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
    private static final String URL = "tcp://localhost:61616";//61616是MQ的默认端口
    private static final String QUEUENAME = "queue-test";

    public static void main(String[] args) throws JMSException {
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(URL);
        //2、创建Connection
        Connection connection = connectionFactory.createConnection();
        //3、启动连接
        connection.start();
        //4、创建会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//第一个参数表示是否在事务中处理，第二个参数表示自动应答机制
        //5、创建一个目标
        Destination destination=session.createQueue(QUEUENAME);
        //6、创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 50; i++) {
            //7、创建消息
            TextMessage textMessage=session.createTextMessage("发送消息"+i);
            //8、发布消息
            producer.send(textMessage);
            System.out.println("发送消息"+i);
        }
        //9、关闭连接
        connection.close();
    }
}