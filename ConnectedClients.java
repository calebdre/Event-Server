import java.io.IOException;
import java.io.PrintWriter;
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
	
	public void ListConnectedClients(){
		for (int i = 0; i < clients.size(); i++) {
			System.out.println(clients.get(i).getRemoteSocketAddress());
		}
	}
	
	public void communicate(Socket client, String message){
		System.out.println("Client named " + client.getRemoteSocketAddress().toString() + " said "+ message);
		try {
			PrintWriter outToClient = new PrintWriter(client.getOutputStream(), true);
			outToClient.println("Thanks for sending that! You're cool.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> permenantEvents() {
		ArrayList<String> events = new ArrayList<String>();
		events.add("clientConnected");
		return events;
		
	}
	
}
