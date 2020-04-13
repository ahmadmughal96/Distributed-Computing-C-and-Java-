import java.net.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  

class Process3
{
	public static void main(String args[]) throws Exception
	{
	Date date = new Date();  
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	DatagramSocket clientSocket = new DatagramSocket();
	InetAddress IPAddress = InetAddress.getByName("localhost");
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	      String sentence = formatter.format(date) + ", Process=3 ";
	      System.out.println("P3 Executing event 1< " +sentence+" > ");
	      sendData = sentence.getBytes();
	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
	      clientSocket.send(sendPacket);
	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	      clientSocket.receive(receivePacket);
	      String modifiedSentence = new String(receivePacket.getData());
	      
	      System.out.println("P3 Executing event 2< " +modifiedSentence);
	      System.out.println("ACK Received");
	      clientSocket.close();
	}
}

