//---------------------------------Lab-05-------------------------------//
//NAME: AHMAD AMJAD MUGHAL----------------------------------------------//
//REG NO: 12w1672-------------------------------------------------------//
//CLASS: BSCS_6C--------------------------------------------------------//
package sample;
import org.json.JSONException;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private String address;
	private int port;
	private Socket socket;
	
	public Client(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void request_server() throws IOException, JSONException {
		createSocket();
		queryWord("jolty");
		Scanner scanner = new Scanner(System.in);
		String operation;
		String word;
		System.out.println("1: Search a Word\n2: Add a Word\n3: Delete a Word");
		while (true){
			System.out.print("\nEnter the operation: ");
			operation = scanner.next();
			switch(operation)
			{
			case "1":
					System.out.print("Enter the word: ");
					word = scanner.next();
					this.queryWord(word);
					break;
			case "2":
					System.out.print("Enter the word: ");
					word = scanner.next();
					System.out.print("Enter its meaning: ");
					String meaning = scanner.next();
					this.addWord(word, meaning);
					break;
			case "3":
					System.out.print("Enter the word you would like to delete: ");
					word = scanner.next();
					this.deleteWord(word);
					break;		
			default:
					System.out.println("Try Again ! Enter Valid Number");
			}
		}	
		}


	public void createSocket() throws IOException {
		socket = new Socket(address, port);
	}

	public void queryWord(String word) throws IOException, JSONException {
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());
		//Create an object to send over TCP to server
		JSONObject json = new JSONObject();
		json.put("operation", "1");
		json.put("word", word);
		out.writeUTF(json.toString());
		System.out.println("Server Response: " + in.readUTF());
	}

	public void addWord(String word, String meaning) throws IOException {
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());

		//Creating the request object to pass over TCP to server
		JSONObject json = new JSONObject();
		json.put("operation", "2");
		json.put("word", word);
		json.put("meaning", meaning);
		out.writeUTF(json.toString());
		System.out.println("Server Response: " + in.readUTF());
	}

	public void deleteWord(String word) throws IOException {
		DataOutputStream out  = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());

		//Creating the JSON object to pass over TCP to server
		JSONObject json = new JSONObject();
		json.put("operation", "3");
		json.put("word", word);
		out.writeUTF(json.toString());
		System.out.println("Server Response: " + in.readUTF());
	}

	public static void main(String[] args) throws IOException, JSONException {
		Client client = new Client("127.0.0.1", 5000);
		client.request_server();
	}
}