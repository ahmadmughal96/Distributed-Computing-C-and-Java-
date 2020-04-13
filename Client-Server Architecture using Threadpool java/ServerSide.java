//Ahmad Amjad Mughal
//121672
//BSCS-6C
//----------------------LAB-02 Client Server Architecture-------------------//
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

//Server Side of the architecture
public class ServerSide {
	private static int id = 1;
	//Define a Hash to map messages to specific client ID 
	public static HashMap<Integer, ClientHandler> clients = new HashMap<>();
	//Main function that creates Server socket and puts all the clients 
	public static void main(String[] args) {
		Socket socket = null;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(45000);
			while(true) {
				socket = serverSocket.accept();
				ClientHandler clientHandler = new ClientHandler();
				clientHandler.ClientDetails(ServerSide.id, socket);
				clientHandler.start();
				ServerSide.clients.put(ServerSide.id, clientHandler);
				ServerSide.id++;
			}
		}
		catch (IOException e) { }
		finally {
			try {
				socket.close();
				serverSocket.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
