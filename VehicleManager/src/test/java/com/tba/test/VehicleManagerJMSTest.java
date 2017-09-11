package com.tba.test;

import java.util.UUID;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.tba.model.Direction;
import com.tba.model.Vehicle;

/**
 * 
 * @author ekber
 *
 * Test class for JMS send/receive
 */
public class VehicleManagerJMSTest {

	public static final String AMQ_BROKER_URL = "tcp://localhost:61616";
    public static final String QUEUE_NAME = "testQueue";

    protected ConnectionFactory activeMQConnectionFactory;
    protected JmsTemplate jmsTemplate;

    @Before
    public void setUp() {
        activeMQConnectionFactory = new ActiveMQConnectionFactory(AMQ_BROKER_URL);
        jmsTemplate = new JmsTemplate(activeMQConnectionFactory);
        final Destination theTestDestination = new ActiveMQQueue(QUEUE_NAME);
        jmsTemplate.setDefaultDestination(theTestDestination);
        jmsTemplate.setReceiveTimeout(500L);
    }

    @Test
    public void someIntegrationTest() throws Exception {
        System.out.println("Test starting...");
        sendMessages();
        receiveMessages();
        System.out.println("Test done!");
    }

    protected void sendMessages() {
        for (int i = 1; i <= 10; i++) {
        	Vehicle vehicle = new Vehicle();
        	vehicle.setVehicleId(UUID.randomUUID().toString());
        	vehicle.setVehicleName("Vehicle-" + i);
        	vehicle.setDirection(Direction.FORWARD);
        	
            jmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
					ObjectMessage objectMessage = session.createObjectMessage(vehicle);
					return objectMessage;
                }
            });
            
            System.out.println("Vehicle-" + i + " sent");
        }
    }

    protected void receiveMessages() throws Exception {
        Message theReceivedMessage = jmsTemplate.receive();
        while (theReceivedMessage != null) {
            if (theReceivedMessage instanceof ObjectMessage) {
                final ObjectMessage message = (ObjectMessage) theReceivedMessage;
                System.out.println(message + " received");
            }

            theReceivedMessage = jmsTemplate.receive();
        }
        System.out.println("All vehicles received!");
    }
}