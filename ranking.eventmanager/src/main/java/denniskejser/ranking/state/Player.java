package denniskejser.ranking.state;

public class Player {
	private String league;
	private String name;
	private int rating;
	
	private int winCount;
	private int lossCount;
	private int trend;

	public int getWinCount() {
		return winCount;
	}

	public void incrementWin() {
		this.winCount++;
		if (trend <0) {
			trend= 1;
		}
		else {
			trend++;
		}
	}

	public int getLossCount() {
		return lossCount;
	}

	public void incrementLoss() {
		this.lossCount++;
		if (trend >0) {
			trend= -1;
		}
		else {
			trend--;
		}
	}

	public int getTrend() {
		return trend;
	}


	public Player(String league, String name, int rating) {
		super();
		this.league =league;
		this.name = name;
		this.rating = rating;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "player: {" + 
					toJsonAttribute("league", league) + ", " +
					toJsonAttribute("name", name) + ", " +
					toJsonAttribute("rating", rating+"") + ", " +
					toJsonAttribute("winCount", winCount+"") + ", " +
					toJsonAttribute("lossCount", lossCount+"") + ", " +
					toJsonAttribute("trend", trend+"") + ", " +
				"}";
	}
	
	public String toJsonAttribute(String name, String value) {
		return "\"" + name + "\": \"" + value +"\""; 
	}
	
	
}
