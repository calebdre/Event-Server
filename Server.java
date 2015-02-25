import java.net.*;
import java.io.*;

public class Server{
	
	private EventDispatcher eventDispatcher;

	public Server(EventDispatcher e){
		eventDispatcher = e;
	}
	
	public void serve(){
		ServerSocket serverSocket = null;
		 
	    try {
	        serverSocket = new ServerSocket(4444);
	    } catch (IOException e) {
	        System.err.println("Could not listen on port: 4444.");
	        System.exit(1);
	    }
	
	    Socket clientSocket = null;
	    // keep a list of connected clients.
	    // http://stackoverflow.com/questions/13115784/sending-a-message-to-all-running-client-threads
	    ConnectedClients clients = new ConnectedClients();
	    while(true){
			clientSocket = serverSocket.accept();		
			clients.add(clientSocket);
	    }
	    
	    try {
	        
	        
	        clientSocket.close();
		    serverSocket.close();
	    } catch (IOException e) {
	        System.err.println("Accept failed.");
	        System.exit(1);
	    }
	}
}