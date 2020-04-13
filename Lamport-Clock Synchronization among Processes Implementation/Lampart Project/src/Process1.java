import java.net.*;
import java.text.SimpleDateFormat;  
import java.util.Date; 

class Process1
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            Date date = null;
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            while(true)
               {
            	  date = new Date();         		
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  System.out.println("P1 Executing event 1< " +sentence+" > ");                                   
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  sentence = java.time.LocalTime.now() + ", Process=1";                 
                  System.out.println("P1 Executing event 2< " +sentence+" > ");
                  sendData = sentence.getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
               }
            
      }
}