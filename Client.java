import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{ 
	
	public static void main(String[] args){
		try {
			Socket sock = new Socket("127.0.0.1", 4444);
			PrintWriter outToServer = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			Scanner userInputScanner = new Scanner(System.in);
			
			System.out.println("Type in an event to trigger");
			
			while(true){
				String userInput = userInputScanner.nextLine();
				outToServer.println(userInput);
				System.out.println(inFromServer.readLine());
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}