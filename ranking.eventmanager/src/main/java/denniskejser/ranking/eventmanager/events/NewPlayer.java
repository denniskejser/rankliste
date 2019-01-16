package denniskejser.ranking.eventmanager.events;

public class NewPlayer implements Event {
	private String name;
	private int rating;
	
	public NewPlayer(String name) {
		super();
		this.name = name;
		rating = 1200;
	}
	public NewPlayer(String name, int rating) {
		super();
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
