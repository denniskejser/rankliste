package denniskejser.ranking.state;

public class Match {
	private Player winner;
	private Player looser;
	private String score;
	private int award;
	private int penalty;
	
	public Match(Player winner, Player looser, String score, int award, int penalty) {
		this.winner=winner;
		this.looser=looser;
		this.score=score;
		this.award=award;
		this.penalty=penalty;
	}

	public String toString() {
		return "match:" + winner.getName() + " (+" + award + ") vs." + looser.getName() + " (" + penalty + ")"; 
	}

	public int getAward() {
		return award;
	}


	public int getPenalty() {
		return penalty;
	}


	public Player getWinner() {
		return winner;
	}


	public Player getLooser() {
		return looser;
	}


	public String getScore() {
		return score;
	}
}
