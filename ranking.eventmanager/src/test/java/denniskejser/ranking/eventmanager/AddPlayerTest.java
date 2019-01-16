package denniskejser.ranking.eventmanager;


import org.junit.Test;

import denniskejser.ranking.eventmanager.events.NewLeagueEvent;
import denniskejser.ranking.eventmanager.events.NewMatchEvent;
import denniskejser.ranking.eventmanager.events.NewPlayerEvent;

public class AddPlayerTest {
	@Test
	public void addPlayer() {
		EventManager manager = new EventManager();
		String l = "test";
		manager.forward(new NewLeagueEvent(l));
		manager.forward(new NewPlayerEvent(l, "Dennis"));
		manager.forward(new NewPlayerEvent(l, "Anne"));
		manager.forward(new NewPlayerEvent(l, "Mads H"));

		for (int i = 0; i<10; i++) {
			manager.forward(new NewMatchEvent(l, "Dennis", "Anne", "10-0"));
			manager.forward(new NewMatchEvent(l, "Dennis", "Mads H", "10-0"));
		}
		for (int i = 0; i<10; i++) {
			manager.forward(new NewMatchEvent(l, "Anne", "Dennis", "10-0"));
			manager.forward(new NewMatchEvent(l, "Mads H","Dennis","10-0"));
		}
	}
}
