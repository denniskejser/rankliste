package denniskejser.ranking.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	public List<League> getLeagues(){
		ArrayList<League> res =  new ArrayList<>(stateMap.values());
		return res;
	}
}
