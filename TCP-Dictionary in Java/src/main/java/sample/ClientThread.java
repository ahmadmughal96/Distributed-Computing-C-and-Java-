//---------------------------------Lab-05-------------------------------//
//NAME: AHMAD AMJAD MUGHAL----------------------------------------------//
//REG NO: 12w1672-------------------------------------------------------//
//CLASS: BSCS_6C--------------------------------------------------------//
package sample;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread{
	
	private Socket clientSocket;
	private ServerSocket serverSocket;
	private WordDictionary dictionary;
	
	public ClientThread(ServerSocket server, Socket client, WordDictionary dictionary) {
		this.clientSocket = client;
		this.serverSocket = server;
		this.dictionary = dictionary;
	}

	public void run() {
		try {
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			JSONParser parser = new JSONParser();
			JSONObject request = null;

			while(true){
				request = (JSONObject) parser.parse(in.readUTF());

				String op = (String) request.get("operation");
				String word = (String) request.get("word");
				String output;
				if(Integer.parseInt(op) ==  1)
				{
						output = searchWord(word);
						out.writeUTF(output);
				}
				else
				if(Integer.parseInt(op) ==  2)
				{
						String meaning = (String) request.get("meaning");
						output = addWord(word, meaning);
						out.writeUTF(output);
				}						
				else
				if(Integer.parseInt(op) ==  3)
				{
						output = deleteWord(word);
						out.writeUTF(output);
				}
				else
						out.writeUTF("Invalid operation");
				}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	public String searchWord(String word){
		if (this.dictionary.getWords().get(word) == null){
			return "NotFound:";
		}
		else{
			return this.dictionary.getWords().get(word).toString();
		}
	}

	public String addWord(String word, String meaning) throws IOException {
		if (this.dictionary.getWords().get(word) != null){
			return "AddError:";
		}
		this.dictionary.getWords().put(word, meaning);
		return "Success";
	}

	public String deleteWord(String word) throws IOException {
		if (this.dictionary.getWords().get(word) == null){
			return "DeleteError:";
		}
		this.dictionary.getWords().remove(word);
		return "Success";
	}
}
