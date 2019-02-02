package denniskejser.ranking.eventmanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import denniskejser.ranking.eventmanager.events.Event;
import denniskejser.ranking.managers.event.EventManager;
import denniskejser.ranking.parser.TournementFileParser;
import denniskejser.ranking.render.output.WebSiteProducer;

public class RankingRunner {
	

	public static void main(String[] args) {
	while (true) {
			Path path = Paths.get("C:\\turneringer\\bordtennisturnering.txt");
			TournementFileParser parser = new TournementFileParser();
			List<Event> events = parser.parse(path);

			EventManager manager = new EventManager();
			for (Event e : events) {
				manager.forward(e);
			}

			WebSiteProducer.produceWebsites();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

}
