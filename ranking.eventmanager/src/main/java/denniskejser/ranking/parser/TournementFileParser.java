package denniskejser.ranking.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import denniskejser.ranking.eventmanager.events.Event;
import denniskejser.ranking.eventmanager.events.NewLeagueEvent;
import denniskejser.ranking.eventmanager.events.NewMatchEvent;
import denniskejser.ranking.eventmanager.events.NewPlayerEvent;

public class TournementFileParser {

	public List<Event> parse(Path path) {
		ArrayList<Event> res = new ArrayList<Event>();
		try {
			File file = path.toFile();
			InputStreamReader is = new InputStreamReader(new FileInputStream(file), "UTF-8");
			try (BufferedReader br = new BufferedReader(is)) {
			    String line;
			    String league = null;
			    while ((line = br.readLine()) != null) {
					Collection<Event> e = toEvent(league, line);
					if (e != null) {
						res.addAll(e);
					} 
					if (e != null && !e.isEmpty()  && e.iterator().next() instanceof NewLeagueEvent) {
						league = ((NewLeagueEvent)e.iterator().next()).getName();
					}
			    }
			}			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	private Collection<Event> toEvent(String league, String line) {
		if (line.trim().equals(""))return null;
		String lineTrimmed = line.toLowerCase().trim();
		
		if (lineTrimmed.contains("liga:")) {
			String[] split = StringUtils.split(line, ":");
			if (split.length == 2) {
				return Arrays.asList(new NewLeagueEvent(split[1].trim()));
			}
		} else if (line.toLowerCase().startsWith("spillere")) {
			String[] split = StringUtils.split(line, ":");
			if (split.length == 2) {
				String[] players = StringUtils.split(split[1], ",");
				ArrayList<Event> newPlayers = new ArrayList<>();
				for (String player : players) {
					newPlayers.add(new NewPlayerEvent(league, player));
				}
				return newPlayers;
			}
		} else if (league != null) {	
			if (StringUtils.split(line, "-").length> 1) {
				return Arrays.asList(new NewMatchEvent(league, line));
				
			}
		}
		return null;
	}

}
