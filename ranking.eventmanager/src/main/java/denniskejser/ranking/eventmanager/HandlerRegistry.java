package denniskejser.ranking.eventmanager;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import denniskejser.ranking.eventmanager.events.Event;
import denniskejser.ranking.eventmanager.handler.EventHandler;
import denniskejser.ranking.eventmanager.handler.NewLeagueEventHandler;
import denniskejser.ranking.eventmanager.handler.NewMatchEventHandler;
import denniskejser.ranking.eventmanager.handler.NewPlayerEventHandler;

public class HandlerRegistry {
	public static HandlerRegistry INSTANCE = new HandlerRegistry();
	
	private HashMap<String, EventHandler<?>>  handlers;
	private HandlerRegistry() {
		handlers = new HashMap<>();
		register(NewMatchEventHandler.class);
		register(NewPlayerEventHandler.class);
		register(NewLeagueEventHandler.class);
	}
	
	

	private <T extends EventHandler<?>>void register(Class<T> clazz) {
		try {
			String key = StringUtils.remove(clazz.getSimpleName(), "Handler");
			handlers.put(key, clazz.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Event> EventHandler<T> getHandler(T event){
		return (EventHandler<T>) handlers.get(event.getClass().getSimpleName());
	}
}
