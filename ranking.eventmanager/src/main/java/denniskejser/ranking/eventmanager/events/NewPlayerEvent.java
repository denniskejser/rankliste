package denniskejser.ranking.eventmanager.events;

public class NewPlayerEvent implements Event {
	private String name;
	private int rating;
	private String league;
	
	public String getLeague() {
		return league;
	}
	public NewPlayerEvent(String league, String name) {
		super();
		this.league=league;
		this.name = name;
		rating = 1200;
	}
	public NewPlayerEvent(String league,String name, int rating) {
		super();
		this.league=league;
		this.name = name;
		this.rating = rating;
	}
	public int getRating() {
		return rating;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
