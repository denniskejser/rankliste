package denniskejser.ranking.eventmanager.state;

import java.util.HashMap;

public class State {
	private HashMap<String, Player> players = new HashMap<>();

	public Player findPlayer(String player) {
		return players.get(player);
	}

	public void addPlayer(Player player) {
		players.put(player.getName(), player);
	}
	
}
