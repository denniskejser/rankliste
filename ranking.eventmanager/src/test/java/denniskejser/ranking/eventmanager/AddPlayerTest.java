package denniskejser.ranking.eventmanager;


import org.junit.Test;

import denniskejser.ranking.eventmanager.events.NewMatchScore;
import denniskejser.ranking.eventmanager.events.NewPlayer;

public class AddPlayerTest {
	@Test
	public void addPlayer() {
		EventManager manager = new EventManager();
		manager.forward(new NewPlayer("Dennis"));
		manager.forward(new NewPlayer("Anne"));
		manager.forward(new NewPlayer("Mads H"));
		
		
		for (int i = 0; i<20; i++) {
			manager.forward(new NewMatchScore("Dennis", "Anne", "10-0"));
			manager.forward(new NewMatchScore("Dennis", "Mads H", "10-0"));
		}
		for (int i = 0; i<25; i++) {
			manager.forward(new NewMatchScore("Anne", "Dennis", "10-0"));
			manager.forward(new NewMatchScore("Mads H","Dennis","10-0"));
		}
	}
}
