//Ahmad Amjad Mughal
//121672
//BSCS-6C
//----------------------LAB-02 Client Server Architecture-------------------//
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;
//ClientHandler Handles all the clients in a pool and assign an id to each client
public class ClientHandler extends Thread {
	private int idClient;
    private DataOutputStream outputStream;
	private Socket socket;
	BufferedReader breader;
	//Constructor with no arguments
	public ClientHandler(){}
	//Defined function CLientDetails that sets the id and socket 
	public void ClientDetails(int idClient, Socket socket) {
        try {
			this.idClient = idClient;
            this.socket = socket;
            this.outputStream = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e) { }
    }
	//run method of thread that calls the HandlingDetails function that separates the text and id 
	@Override
	public void run() 
	{
		HandlingDetails(idClient);
	}
	//function that gets the id of each client from server 
	public void HandlingDetails(int idClient)
	{
		{
			try {
				breader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while (true) {
					String text = breader.readLine();
					//if text starts with @ there's an id
					if (text.startsWith("@")) {
						int recipientId = Integer.parseInt(text.substring(1, 2));
						ClientHandler recipient = ServerSide.clients.get(recipientId);
						if (recipient != null) {
							recipient.sendMessage(idClient, text.substring(3), true);
						}

						continue;
					}
					//Perform an entry into Hash Map and links the client and server
					for (Map.Entry<Integer, ClientHandler> client: ServerSide.clients.entrySet()) {
						client.getValue().sendMessage(idClient, text, false);
					}
				}
			}
			catch (IOException e) { }
		}
	}
	public void sendMessage(int sender, String message, boolean privateMsg) {
		try {
			message = message + " from sender " + sender + "\n";
			outputStream.writeBytes(message);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
