package denniskejser.ranking.eventmanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import denniskejser.ranking.eventmanager.events.Event;
import denniskejser.ranking.managers.event.EventManager;
import denniskejser.ranking.parser.TournementFileParser;
import denniskejser.ranking.state.InMemoryState;
import denniskejser.ranking.state.League;
import denniskejser.ranking.state.Match;
import denniskejser.ranking.state.Player;
import denniskejser.ranking.state.html.HtmlRender;

public class ParserTest {
	
	@Test
	public void test() {
		Path path = Paths.get("C:\\turneringer\\bordtennisturnering.txt");
		TournementFileParser parser = new TournementFileParser();
		List<Event> events = parser.parse(path);

		EventManager manager = new EventManager();
		for (Event e : events) {
			manager.forward(e);
		}

		for (League league : InMemoryState.INSTANCE.getLeagues()) {
			System.out.println("Matches");
			for (Match match : league.getMatches()) {
				System.out.println(HtmlRender.toHtml(match));
			}

			System.out.println("Ranking");
			List<Player> playersByRanking = league.getPlayersByRanking();
			for (int i = 0; i < playersByRanking.size(); i++) {
				System.out.println(HtmlRender.toHtml(playersByRanking.get(i), i+1));
			}
		}
	}

}
