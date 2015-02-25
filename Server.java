import java.lang.reflect.Array;
import java.net.*;
import java.io.*;

public class Server{
	
	public static void main(String[] args) throws Throwable{
		ServerSocket serverSocket = null;
		 
	    try {
	        serverSocket = new ServerSocket(4444);
	    } catch (IOException e) {
	        System.err.println("Could not listen on port: 4444.");
	        System.exit(1);
	    }
	
	    Socket clientSocket = null;
	    try {
	        clientSocket = serverSocket.accept();
	    } catch (IOException e) {
	        System.err.println("Accept failed.");
	        System.exit(1);
	    }
	
	    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    
	    String inputLine, outputLine = "";
	    EventDispatcher events = new EventDispatcher(); 
	    // http://stackoverflow.com/questions/10777678/send-message-from-a-basic-server-to-a-specific-client
	    // accept two things: 
	    out.println(outputLine);
	
	    while ((inputLine = in.readLine()) != null) {
	         String[] p = inputLine.split("\\s");
	         
	         if(p.length > 2) throw new Exception("Too many arguments passed to the server.");
	         
	         String identifier = p[0];
	         String event = p[1];
	         
	         //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	         
	    }
	    
	    out.close();
	    in.close();
	    clientSocket.close();
	    serverSocket.close();
	}
}