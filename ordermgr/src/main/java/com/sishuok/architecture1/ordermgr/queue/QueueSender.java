package com.sishuok.architecture1.ordermgr.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueSender {
	public static void sendMsg(int customerUuid) {
		Connection connection = null;
		Session session = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.16.15.111:61626");
			connection = connectionFactory.createConnection();
			connection.start();
			
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("order-queue");
			MessageProducer producer = session.createProducer(destination);
			
			TextMessage message = session.createTextMessage(""+customerUuid);
			producer.send(message);
			
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}finally{
			try {
				session.close();
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
