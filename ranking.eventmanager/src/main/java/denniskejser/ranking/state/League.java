package denniskejser.ranking.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class League {
	private String league;

	public League(String league) {
		this.league = league;
	}

	private HashMap<String, Player> players = new HashMap<>();
	private List<Match> matches = new ArrayList<>();

	public List<Match> getMatches() {
		return matches;
	}

	public Player findPlayer(String player) {
		if (StringUtils.isEmpty(player))return null;
		String trimmedLowerCase = player.toLowerCase().trim();
		if (players.get(trimmedLowerCase) !=null)
			return players.get(trimmedLowerCase);
		
		int idx = StringUtils.lastIndexOf(player, " ");
		if (idx > -1) {
			return findPlayer(player.substring(0, idx));
		}
		return null;
	}

	public void addPlayer(Player player) {
		players.put(player.getName().toLowerCase().trim(), player);
	}

	public String getName() {
		return league;
	}

	public List<Player> getPlayersByRanking() {
		ArrayList<Player> res = new ArrayList<Player>(players.values());
		Collections.sort(res, new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				int c = Integer.valueOf(o2.getRating()).compareTo(Integer.valueOf(o1.getRating()));
				if (c == 0) {
					c = o2.getName().compareTo(o1.getName());
				}
				return c;
			}
		});
		return res;
	}

	public List<Player> findPlayers(String line, String seperator) {
		ArrayList<Player> res = new ArrayList<>();
		for (String s : StringUtils.split(line, seperator)) {
			Player p = findPlayer(s.trim());
			if (p != null)
				res.add(p);
		}
		return res;
	}

	public void addMatch(Match match) {
		matches.add(match);
	}

}
