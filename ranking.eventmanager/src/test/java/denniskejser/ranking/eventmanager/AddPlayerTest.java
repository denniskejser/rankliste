package denniskejser.ranking.eventmanager;


import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import denniskejser.ranking.eventmanager.events.NewLeagueEvent;
import denniskejser.ranking.eventmanager.events.NewMatchEvent;
import denniskejser.ranking.eventmanager.events.NewPlayerEvent;
import denniskejser.ranking.state.InMemoryState;
import denniskejser.ranking.state.League;
import denniskejser.ranking.state.Player;

public class AddPlayerTest {
	@Test
	public void tableTennisTest() {
		EventManager manager = new EventManager();
		String l = "test";
		ArrayList<String> players = new ArrayList<>();
		players.add("Dennis");
		players.add("Mads H");
		players.add("Mads T");
		players.add("Jonas");
		players.add("Lars");
		players.add("Anders");
		players.add("Jørgen");
		players.add("Troels");
		
		manager.forward(new NewLeagueEvent(l));
		for (String player : players) {
			manager.forward(new NewPlayerEvent(l, player,1000));
		}
		for (int i = 0;i< 10; i++) {
			Collections.shuffle(players);
			manager.forward(new NewMatchEvent(l, players.get(0), players.get(1), "10-0"));
			manager.forward(new NewMatchEvent(l, players.get(2), players.get(3), "10-0"));			
			manager.forward(new NewMatchEvent(l, players.get(4), players.get(5), "10-0"));			
			manager.forward(new NewMatchEvent(l, players.get(6), players.get(7), "10-0"));			
		}
		League league = InMemoryState.INSTANCE.getLeagueState(l);
		for (Player p : league.getPlayersByRanking()) {
			System.out.println(p);
		}
	}
}
