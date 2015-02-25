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
	
	public void remove(String event){
		events.remove(event);
	}
	
	public void fire(String event, Object arg) throws EventDoesNotExistException{
		Object c = events.get(event);
		if(c == null) throw new EventDoesNotExistException();
		
		invoke(event, c.getClass(), arg);
		remove(event);
	}
	
	public void fire(String event, List<Object> args) throws EventDoesNotExistException{
		Object c = events.get(event);
		if(c == null) throw new EventDoesNotExistException(); 
		
		invoke(event, c.getClass(), args);
		
		remove(event);
	}
	
	public void invoke(String method, Class<?> cls, Object arg){
		try {
			
			cls.getMethod(method, (Class<?>) null).invoke(cls, arg);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void invoke(String method, Class<?> cls, List<Object> args){
		try {
			
			cls.getMethod(method, (Class<?>) null).invoke(cls, args);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}