package denniskejser.ranking.eventmanager.handler;

import denniskejser.ranking.eventmanager.EventManager;
import denniskejser.ranking.eventmanager.events.Event;

public interface EventHandler<T extends Event> {

	public void handle(EventManager manager, T event);
	
}
