package denniskejser.ranking.eventmanager;

import denniskejser.ranking.eventmanager.events.Event;
import denniskejser.ranking.eventmanager.events.NewMatchScore;
import denniskejser.ranking.eventmanager.events.NewPlayer;
import denniskejser.ranking.eventmanager.state.Player;
import denniskejser.ranking.eventmanager.state.State;

public class EventManager {
	State state = null;

	public EventManager() {
		state = new State();
	}
	
	public State forward(Event e) {
		if (e instanceof NewPlayer) {
			handle((NewPlayer) e);
		}
		if (e instanceof NewMatchScore) {
			handle((NewMatchScore) e);
		}
		return state;
	}
	
	private void handle(NewMatchScore e) {
		Player winner = state.findPlayer(e.getWinner());
		Player looser = state.findPlayer(e.getLooser());
		int diff = winner.getRating() - looser.getRating();

		int award = 0;
		int penalty =0;

		if (diff == 0) {
			award =7;
			penalty = -5;
		}
		if (diff > 0) {//ventet resultat
			if (diff < 25) {
				award =7;
				penalty = -5;
			}
			else if (diff < 50) {
				award =6;
				penalty = -4;
			}
			else if (diff < 75) {
				award =5;
				penalty = -3;
				
			}
			else if (diff < 100) {
				award =4;
				penalty = -3;
			}
			else if (diff < 200) {
				award =3;
				penalty = -3;
			}
			else if (diff < 200) {
				award =2;
				penalty = -2;
				
			}else {
				award =1;
				penalty = -1;
				
			}
		}else { //uventet resultat
			diff = Math.abs(diff);
			
			if (diff < 25) {
				award =7;
				penalty = -5;
			}
			else if (diff < 50) {
				award =12;
				penalty = -10;
			}
			else if (diff < 75) {
				award =16;
				penalty = -14;
				
			}
			else if (diff < 100) {
				award =19;
				penalty = -17;
			}
			else if (diff < 200) {
				award =22;
				penalty = -20;
			}
			else if (diff < 200) {
				award =22;
				penalty = -18;
				
			}else {
				award =22;
				penalty = -15;
			}
		}
		winner.setRating(winner.getRating() + award);
		looser.setRating(looser.getRating() + penalty);
		System.out.println(winner.getName() + " (" + winner.getRating() + ") was awarded " + award + " " + looser.getName() + " (" + looser.getRating() + ") was deducted " + penalty);
	}

	public State getState() {
		return state;
	}

	public void handle(NewPlayer player) {
		Player p = state.findPlayer(player.getName());
		if (p != null) throw new RuntimeException("Player name taken: " + player.getName());
		
		state.addPlayer(new Player(player.getName(), player.getRating()));
		
	}
}
