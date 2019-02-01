package denniskejser.ranking.eventmanager.handler;

import denniskejser.ranking.eventmanager.events.Event;
import denniskejser.ranking.managers.event.EventManager;

public interface EventHandler<T extends Event> {

	public void handle(EventManager manager, T event);
	
}
