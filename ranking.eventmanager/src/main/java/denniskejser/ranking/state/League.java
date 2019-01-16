package denniskejser.ranking.state;

import java.util.HashMap;

public class League {
	private String league;
	
	public League(String league) {
		this.league=league;
	}
	private HashMap<String, Player> players = new HashMap<>();

	public Player findPlayer(String player) {
		return players.get(player);
	}
	public void addPlayer(Player player) {
		players.put(player.getName(), player);
	}
	
	public String getName() {
		return league;
	}
}
