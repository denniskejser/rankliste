package denniskejser.ranking.state;

import java.util.HashMap;

public class InMemoryState {
	public static InMemoryState INSTANCE = new InMemoryState();
	
	HashMap<String, League> stateMap = new HashMap<>();
	
	private InMemoryState() {
	
	}

	public void createLeagueState(String name){
		stateMap.put(name, new League(name));
	}
	public League getLeagueState(String name){
		return stateMap.get(name);
	}
}
