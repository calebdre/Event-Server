import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class EventDispatcher {
	
	// this will store event names and the object that the event comes from. 
	// The name will be mapped to execute a method on the object of the same name.
	public HashMap<String, Object> events = new HashMap<String, Object>();

	public void add(String event, Object ob){
		events.put(event, ob);
	}
	
	public void add(EventHub hub){
		for(Method m: hub.getClass().getMethods()){
			this.add(m.getName(), hub);
		}
	}
	
	public void remove(String event){
		events.remove(event);
	}
	
	public void fire(String event, Object... args) throws EventDoesNotExistException{
		Object c = events.get(event);
		if(c == null) throw new EventDoesNotExistException();
		
		invoke(event, c, args);
		remove(event);
	}
	
	public void invoke(String method, Object cls, Object... args){
		try {
			cls.getClass().getMethod(method, getClasses(args)).invoke(cls, args);
			
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
	
	public static Class<?>[] getClasses(Object... array) {
	    if(array == null) {
	        return new Class<?>[0]; // return an empty array
	    }
	    Class<?>[] classes = new Class<?>[array.length];
	    for(int i = 0; i < array.length; i++) {
	        classes [i] = array[i].getClass();
	    }
	    return classes;
	}
}