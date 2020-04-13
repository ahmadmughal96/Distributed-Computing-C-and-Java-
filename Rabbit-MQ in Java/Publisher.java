//-------------Ahmad Amjad Mughal-----------
//-------------121672--------------
//-------------BSCS-6C-------------
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

//Publisher class that creates connection and defines a name of exchange and type of Exchange
public class Publisher 
{
	private static final String EXCHANGE = "MyExchange";
	public static void main(String[] argv)
	{
		try {
			Publisher.ConnectionConfig(argv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
/*ConnectionConfig function sets the configurations and sends a message using fan-out binding to all
  subscribers.*/
	public static void ConnectionConfig(String[] argv) throws Exception
	{
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//Type off Channel
        channel.exchangeDeclare(EXCHANGE, "fanout");
//MEssage which needs to be delivered
        String message = argv.length < 1 ? "Message: Ahmad Amjad Mughal is here!" :
            String.join(" ", argv);
//Message is published to exchange
        channel.basicPublish(EXCHANGE, "", null, message.getBytes());
        System.out.println("MESSAGE DELIVERED..........'\n" + message + "'");

	}
}
