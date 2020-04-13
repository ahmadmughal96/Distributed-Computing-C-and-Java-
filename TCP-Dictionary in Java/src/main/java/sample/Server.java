//---------------------------------Lab-05-------------------------------//
//NAME: AHMAD AMJAD MUGHAL----------------------------------------------//
//REG NO: 12w1672-------------------------------------------------------//
//CLASS: BSCS_6C--------------------------------------------------------//
package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	
	private int port;
	private WordDictionary dictionary;
	
	public Server(int port) {
		this.port = port;
		dictionary = WordDictionary.getDictionary();
	}
	
	public void start() throws IOException {
		ServerSocket socket = createSocket(port);
		System.out.println("Server is running .....");
		Socket conn;
		String line = "";
		
		while(true) {
			conn = socket.accept();
			System.out.println("Client Connected " + conn.getInetAddress().toString() + " at port " + conn.getPort());
			ClientThread thread = new ClientThread(socket, conn, dictionary);
			thread.start();
		}
	}
	
	public ServerSocket createSocket(int port) throws IOException{
		ServerSocket socket = null;
		socket = new ServerSocket(port);
		return socket;
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server(5000);
		server.start();
	}
}