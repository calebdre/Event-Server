


public class Start {

	public static void main(String[] args) {
		EventDispatcher e = new EventDispatcher();
		
		//
		// add events here
		ConnectedClientsEventHub connectedClients = new ConnectedClientsEventHub();
		//
		
		e.add(connectedClients);
		
		Server s = new Server(e);
		s.serve();
	}

}
