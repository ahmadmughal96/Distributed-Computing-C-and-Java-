//Ahmad Amjad Mughal
//121672
//BSCS-6C
//----------------------LAB-02 Client Server Architecture-------------------//
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

//Client Side that sends the message which is then handled by clientHandler
public class ClientSide {
    private static Socket socket;
    private static BufferedReader inputStream;
    private static DataOutputStream outputStream;
    //Initializations of each instance for client to read and send the packet and then print it
	public static void main(String[] args) {
        try {
            ClientSide.socket = new Socket("127.0.0.1", 45000);
            ClientSide.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ClientSide.outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

      
//Thread that ask user to enter the message and then sends it to ClientHandler	
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        String text = inputStream.readLine();
                        System.out.println(text);
                    }
                    catch (IOException e) {
                        break;
                    }
                }
            }
        }).start();
//Thread that prints received from specific client. 
        new Thread(new Runnable() {
            public void run() 
            {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    try {
                        String text = scanner.nextLine();
                        text += '\n';
                        outputStream.writeBytes(text);
                    }
                    catch (IOException e) {
                        break;
                    }
                }
            }
        }).start();
	}
	
}
