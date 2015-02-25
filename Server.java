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
	
	    
	    // keep a list of connected clients.
	    // http://stackoverflow.com/questions/13115784/sending-a-message-to-all-running-client-threads
	    
	    while(true){
			try {
				Socket clientSocket = serverSocket.accept();
				eventDispatcher.fire("clientConnected", clientSocket);
				new Thread(new ServerWorker(clientSocket, eventDispatcher)).start();
			} catch (IOException | EventDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}