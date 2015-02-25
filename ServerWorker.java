import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerWorker implements Runnable{
	//THIS IS STILL THE SERVER
	
	Socket clientSock;
	
	public ServerWorker(Socket client){
		this.clientSock = client;
	}
	@Override
	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(this.clientSock.getInputStream()));
			ObjectInputStream inFromClient = new ObjectInputStream(this.clientSock.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
