package denniskejser.ranking.eventmanager.handler;

import denniskejser.ranking.eventmanager.EventManager;
import denniskejser.ranking.eventmanager.events.NewPlayerEvent;
import denniskejser.ranking.state.InMemoryState;
import denniskejser.ranking.state.League;
import denniskejser.ranking.state.Player;

public class NewPlayerEventHandler implements EventHandler<NewPlayerEvent>{
	@Override
	public void handle(EventManager manager, NewPlayerEvent event) {
		League state = InMemoryState.INSTANCE.getLeagueState(event.getLeague());
		if(state != null) {
			Player p = state.findPlayer(event.getName());
			if (p != null) throw new RuntimeException("Player name taken: " + event.getName());
			state.addPlayer(new Player(state.getName(), event.getName(), event.getRating()));
		}
	}

}
