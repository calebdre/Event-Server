import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient{ 
	
	public static void main(String[] args){
		ChatClient client = new ChatClient();
		client.connect();
	}
	
	Socket sock;
	BufferedReader inFromServer;
	PrintWriter outToServer;
	
	public void connect(){
		try {
			sock = new Socket("127.0.0.1", 4444);
			inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			outToServer = new PrintWriter(sock.getOutputStream(), true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Thread(new UserInputHandler(outToServer)).start();
		
		while(true){
			try {
				while(inFromServer.ready()){
					System.out.println(inFromServer.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class UserInputHandler implements Runnable{
		
		PrintWriter outToServer;
		
		public UserInputHandler(PrintWriter outToServer) {
			this.outToServer = outToServer;
		}

		@Override
		public void run() {
			System.out.println("Welcome to the chat! just start typing");
			
			while(true){
				Scanner userInputScanner = new Scanner(System.in);
				String userInput = userInputScanner.nextLine();
				outToServer.println("communicate " + "\"" + userInput + "\"");
				System.out.println("You: " + userInput);
			}
			
		}
		
	}
}