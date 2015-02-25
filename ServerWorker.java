import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
			PrintWriter outputToClient = null;
			try {
				inputFromClient = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
				outputToClient = new PrintWriter(clientSock.getOutputStream(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    String inputLine = "";
		     
		    // http://stackoverflow.com/questions/10777678/send-message-from-a-basic-server-to-a-specific-client
		    
		    try {
				while ((inputLine = inputFromClient.readLine()) != null) {
				     String[] p = inputLine.split("\\s");
				     
				     if(p.length > 2){
				    	 outputToClient.print("The command had too many arguments");
				    	 continue;
				     }
				     
				     String identifier = p[0];
				     String event = p[1];
				     
				     List<Object> args = new ArrayList<Object>();
				     args.add(identifier);
				     args.add(clientSock);
				     
					try {
						this.eventDispatcher.fire(event, args);
					} catch (EventDoesNotExistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				     
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
