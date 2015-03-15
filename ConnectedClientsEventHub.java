import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ConnectedClientsEventHub implements EventHub{
	
	private List<Socket> clients;
	
	ConnectedClientsEventHub(){
		clients = new ArrayList<Socket>();  
	}
	
	public void clientConnected(Socket client){
		clients.add(client);
		String message = "Client " + client.getRemoteSocketAddress().toString() + " has connected";
		System.out.println(message);
		sendToAll(message, client);
	}
	
	public void ListConnectedClients(){
		for (int i = 0; i < clients.size(); i++) {
			System.out.println(clients.get(i).getRemoteSocketAddress());
		}
	}
	
	public void communicate(Socket client, String message){
		System.out.println("Client named " + client.getRemoteSocketAddress().toString() + " said: "+ message);
		String messageToClients = client.getRemoteSocketAddress() + " said: " + message;
		sendToAll(messageToClients, client);
	}
	
	private void sendToAll(String message, Socket source){
		for(Socket client : clients){
			if(client.getRemoteSocketAddress().equals(source.getRemoteSocketAddress())) continue;
			try {
				PrintWriter t = new PrintWriter(client.getOutputStream(), true);
				t.println(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public ArrayList<String> permenantEvents() {
		ArrayList<String> events = new ArrayList<String>();
		events.add("clientConnected");
		return events;
		
	}
	
}
