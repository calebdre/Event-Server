import java.util.HashMap;
import java.util.Scanner;


public class EventDispatcher {
	
	private static HashMap<String, EventCommand> events = new HashMap<String, EventCommand>();

	public static void add(String event, EventCommand func){
		events.put(event, func);
	}
	
	public void fire(String event) throws EventDoesNotExistException{
		EventCommand c = events.get(event);
		if(c == null){ 
			throw new EventDoesNotExistException(); 
		}
		
		c.execute();
		this.remove(event);
	}
	
	public void remove(String event){
		events.remove(event);
	}
	
	public class EventDoesNotExistException extends Exception{
		public EventDoesNotExistException(){
			super("The event you tried to trigger does not exist.");
		}
	}
}
