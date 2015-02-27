import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher {
	
	// this will store event names and the object that the event comes from. 
	// The name will be mapped to execute a method on the object of the same name.
	public HashMap<String, Object> events = new HashMap<String, Object>();
	public ArrayList<String> permenantEvents = new ArrayList<String>();

	public void add(String event, Object ob, boolean permenant){
		events.put(event, ob);
		permenantEvents.add(event);
	}
	
	public void add(EventHub hub){
		for(Method m: hub.getClass().getMethods()){
			boolean permenant = hub.permenantEvents().contains(m.getName());
			this.add(m.getName(), hub, permenant);
		}
	}
	
	public void remove(String event){
		events.remove(event);
	}
	
	public void fire(String event, Object... args){
		Object c = events.get(event);
		if(c != null){
			invoke(event, c, args);
			if(!permenantEvents.contains(event)) remove(event);
		}else{
			System.out.println("Event " + event + " does not exist");
		}
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
	
	public static Class<?>[] getClasses(Object... classList) {
	    if(classList == null) {
	        return new Class<?>[0]; // return an empty array
	    }
	    Class<?>[] classes = new Class<?>[classList.length];
	    for(int i = 0; i < classList.length; i++) {
	        classes [i] = classList[i].getClass();
	    }
	    return classes;
	}
}