package denniskejser.ranking.eventmanager.events;

public class NewMatchScore implements Event{
	public String player1;
	private String player2;
	private String result;
	
	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public NewMatchScore(String player1, String player2, String result) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.result = result;
	}
	
	public String getWinner() {
		return player1;
	}
	
	public String getLooser() {
		return player2;
	}
	
	
}