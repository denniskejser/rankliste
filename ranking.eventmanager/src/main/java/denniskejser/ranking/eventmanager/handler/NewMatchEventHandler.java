package denniskejser.ranking.eventmanager.handler;

import denniskejser.ranking.eventmanager.EventManager;
import denniskejser.ranking.eventmanager.events.NewMatchEvent;
import denniskejser.ranking.state.InMemoryState;
import denniskejser.ranking.state.League;
import denniskejser.ranking.state.Player;

public class NewMatchEventHandler implements EventHandler<NewMatchEvent>{
	
	@Override
	public void handle(EventManager manager, NewMatchEvent e) {
		League state = InMemoryState.INSTANCE.getLeagueState(e.getLeague());
		if (state != null) {
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
			winner.incrementWin();
			looser.setRating(looser.getRating() + penalty);
			looser.incrementLoss();
			System.out.println(winner);
			System.out.println(looser);
		}
	}

}
