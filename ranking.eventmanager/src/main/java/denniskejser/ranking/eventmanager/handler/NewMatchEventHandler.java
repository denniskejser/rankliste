package denniskejser.ranking.eventmanager.handler;

import java.util.List;

import denniskejser.ranking.eventmanager.events.NewMatchEvent;
import denniskejser.ranking.managers.event.EventManager;
import denniskejser.ranking.state.InMemoryState;
import denniskejser.ranking.state.League;
import denniskejser.ranking.state.Match;
import denniskejser.ranking.state.Player;

public class NewMatchEventHandler implements EventHandler<NewMatchEvent>{

	@Override
	public void handle(EventManager manager, NewMatchEvent e) {
		League state = InMemoryState.INSTANCE.getLeagueState(e.getLeague());
		if (state != null) {
			String unparsed = e.getUnparsed();
			if (unparsed != null) {
				List<Player> players = state.findPlayers(e.getUnparsed(), "-");
				if (players.size() == 2) {
					e.setPlayer1(players.get(0).getName());
					e.setPlayer2(players.get(1).getName());
					int idx = unparsed.toLowerCase().indexOf(players.get(1).getName().toLowerCase());
					if (idx+players.get(1).getName().length() < unparsed.length()) {
						String result = unparsed.substring(idx+players.get(1).getName().length() ).trim();
						e.setResult(result);
					}
				}else {
					return;
				}
			}
			Player winner = state.findPlayer(e.getWinner());
			Player looser = state.findPlayer(e.getLooser());
			int diff = winner.getRating() - looser.getRating();

			int award = 0;
			int penalty =0;

			if (diff == 0) {
				award =7;
				penalty = -5;
			}
			
			
			int lowestStep = 10;
			int secondStep = 20;
			int thirdStep = 30;
			int fourthStep = 40;
			int step5 = 50;
			int step6 = 60;
//			int lowestStep = 25;
//			int secondStep = 50;
//			int thirdStep = 75;
//			int fourthStep = 100;
//			int step5 = 200;
//			int step6 = 300;

			
			
			
			if (diff > 0) {//ventet resultat
				if (diff < lowestStep) {
					award =7;
					penalty = -5;
				}
				else if (diff < secondStep) {
					award =6;
					penalty = -4;
				} else {
					if (diff < thirdStep) {
						award =5;
						penalty = -3;
						
					}
					else if (diff < fourthStep) {
						award =4;
						penalty = -3;
					}
					else if (diff < step5) {
						award =3;
						penalty = -3;
					}
					else if (diff < step6) {
						award =2;
						penalty = -2;
					}else {
						award =1;
						penalty = -1;
					}
				}
			}else { //uventet resultat
				diff = Math.abs(diff);
				
				if (diff < lowestStep) {
					award =7;
					penalty = -5;
				}
				else if (diff < secondStep) {
					award =12;
					penalty = -10;
				}
				else if (diff < thirdStep) {
					award =16;
					penalty = -14;
				}
				else if (diff < fourthStep) {
					award =19;
					penalty = -17;
				}
				else if (diff < step5) {
					award =22;
					penalty = -20;
				}
				else if (diff < step6) {
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
			Match match = new Match(winner, looser, e.getResult(),award, penalty);
			winner.addMatch(match);
			looser.addMatch(match);
			state.addMatch(match);
		}
	}

}
