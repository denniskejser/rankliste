package denniskejser.ranking.eventmanager.events;

public class NewPlayerEvent extends Event {
	private String name;
	private int rating;
	private String league;


	public NewPlayerEvent(String league, String name) {
		super();
		this.league=league;
		this.name = name;
		rating = 1000;
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
		return name.trim();
	}
	public String getLeague() {
		return league;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
