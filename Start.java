
public class Start {

	public static void main(String[] args) {
		EventDispatcher e = new EventDispatcher();
		
		//
		// add events here
		ConnectedClients connectedClients = new ConnectedClients();
		//
		
		e.add(connectedClients);
		
		Server s = new Server(e);
		s.serve();
	}

}
