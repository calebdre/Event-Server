public class EventDoesNotExistException extends Exception{
		private static final long serialVersionUID = 1L;

		public EventDoesNotExistException(){
			super("The event you tried to trigger does not exist.");
		}
	}