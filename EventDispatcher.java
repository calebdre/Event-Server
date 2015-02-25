import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class EventDispatcher {
	
	// this will store event names and the object that the event comes from. 
	// The name will be mapped to execute a method on the object of the same name.
	private static HashMap<String, Object> events = new HashMap<String, Object>();

	public void add(String event, Object ob){
		events.put(event, ob);
	}
	
	public void add(EventHub hub){
		for(Method m: hub.getClass().getMethods()){
			this.add(m.getName(), hub.getClass());
		}
	}
	
	// the args object would be the identifier for the client, and then the socket
	public void fire(String event, List<Object> args) throws EventDoesNotExistException{
		Object c = events.get(event);
		if(c == null) throw new EventDoesNotExistException(); 
		
		try {
			// the second parameter is to identify methods with the same name
			Method m = c.getClass().getMethod(event,(Class<?>) null);
			m.invoke(c, args);
			
			this.remove(event);
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remove(String event){
		events.remove(event);
	}
	
}