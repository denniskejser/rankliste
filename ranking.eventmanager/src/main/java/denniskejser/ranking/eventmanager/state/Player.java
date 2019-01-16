package denniskejser.ranking.eventmanager.state;

public class Player {
	
	private String name;
	private int rating;
	
	private int winCount;
	private int lossCount;
	private int trend;

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	public int getLossCount() {
		return lossCount;
	}

	public void setLossCount(int lossCount) {
		this.lossCount = lossCount;
	}

	public int getTrend() {
		return trend;
	}

	public void setTrend(int trend) {
		this.trend = trend;
	}

	public Player(String name, int rating, int winCount, int lossCount, int trend) {
		super();
		this.name = name;
		this.rating = rating;
		this.winCount = winCount;
		this.lossCount = lossCount;
		this.trend = trend;
	}

	public Player(String name, int rating) {
		super();
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

	
	
}
