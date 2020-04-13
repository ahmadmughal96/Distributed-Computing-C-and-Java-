//-------------Ahmad Amjad Mughal-----------
//-------------121672--------------
//-------------BSCS-6C-------------
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;

/*Subscriber class sets the Connection, creates the Channel, Declares the queue and Exchange type
and waits for message to be received from Queue using binding-key*/
public class Subscriber 
{
	private static final String EXCHANGE = "MyExchange";
	
	public static void main(String[] argv) throws Exception,java.lang.InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE, "");

        System.out.println(".......Waiting for messages.......");
//Superclass DefaultChannel that overrides handleDelivery method by passing required arguments
//Here RoutingKey is "" and fan-out message is delivered to all subscribers.
        Consumer consumer = new DefaultConsumer(channel) {
    		@Override
    		public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
    				byte[] body) throws UnsupportedEncodingException {
    			String message = new String(body, "UTF-8");
    			System.out.println("Message Received : " + message + "'");
    		}
    	};
    	channel.basicConsume(queueName, true, consumer);
    }
}
