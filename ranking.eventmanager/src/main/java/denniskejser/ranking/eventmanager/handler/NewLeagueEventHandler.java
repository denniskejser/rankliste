package denniskejser.ranking.eventmanager.handler;

import denniskejser.ranking.eventmanager.events.NewLeagueEvent;
import denniskejser.ranking.managers.event.EventManager;
import denniskejser.ranking.state.InMemoryState;

public class NewLeagueEventHandler implements EventHandler<NewLeagueEvent>{
	@Override
	public void handle(EventManager manager, NewLeagueEvent event) {
		InMemoryState.INSTANCE.createLeagueState(event.getName());
	}

}
