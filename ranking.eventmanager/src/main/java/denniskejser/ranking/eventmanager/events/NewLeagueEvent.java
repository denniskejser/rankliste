package denniskejser.ranking.eventmanager.events;

public class NewLeagueEvent implements Event{
	private String name;
	public NewLeagueEvent(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
