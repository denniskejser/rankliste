package denniskejser.ranking.eventmanager;

import denniskejser.ranking.eventmanager.events.Event;
import denniskejser.ranking.eventmanager.handler.EventHandler;

public class EventManager {

	public EventManager() {
	}
	
	public void forward(Event e) {
		EventHandler<Event> handler = HandlerRegistry.INSTANCE.getHandler(e);
		if (handler != null)handler.handle(this, e);
		else System.out.println("No handler for event : " + e.getClass().getSimpleName());
		
	}
	

}
