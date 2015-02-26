import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{ 
	
	public static void main(String[] args){
		try {
			Socket sock = new Socket("127.0.0.1", 4444);
			PrintWriter outToServer = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}