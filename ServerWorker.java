import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerWorker implements Runnable{
	//THIS IS STILL THE SERVER
	
	private Socket clientSock;
	private EventDispatcher eventDispatcher;
	
	public ServerWorker(Socket client, EventDispatcher eventDispatcher){
		clientSock = client;
		this.eventDispatcher = eventDispatcher;
	}
	@Override
	public void run() {
			BufferedReader inputFromClient = null;
			try {
				inputFromClient = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    String inputLine = "";
		     
		    // http://stackoverflow.com/questions/10777678/send-message-from-a-basic-server-to-a-specific-client
		    
		    try {
				while ((inputLine = inputFromClient.readLine()) != null) {
					// the string the client sends needs to be in format: event "param string info"
					// example: communicate "Hello world"
				     String event = inputLine.split("\\s")[0];
				     String param = inputLine.split("\"")[1];
				     
					this.eventDispatcher.fire(event, clientSock, param);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
