
public class Start {

	public static void main(String[] args) {
		EventDispatcher e = new EventDispatcher();
		
		//
		// add events here
		//
		
		Server s = new Server(e);
		s.serve();
	}

}
