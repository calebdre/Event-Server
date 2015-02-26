import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ConnectedClients implements EventHub{
	
	private List<Socket> clients;
	
	ConnectedClients(){
		clients = new ArrayList<Socket>();  
	}
	
	public void clientConnected(Socket client){
		clients.add(client);
		System.out.println("Client " + client.getRemoteSocketAddress().toString() + " has connected");
	}
}
