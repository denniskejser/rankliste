package denniskejser.ranking.render.output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import denniskejser.ranking.html.HtmlRender;
import denniskejser.ranking.state.InMemoryState;
import denniskejser.ranking.state.League;
import denniskejser.ranking.state.Match;
import denniskejser.ranking.state.Player;

public class WebSiteProducer {
	
	public static void main (String[] args) {
		System.out.println(readHeader());
		
	}
	public static String readHeader()  {
		InputStream inputStream = WebSiteProducer.class.getResourceAsStream("/css/header.css");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
			return br.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void produceWebsites() {
		
		Path path = Paths.get("C:\\turneringer\\websites");
		File dirFile = path.toFile();
		for (League league : InMemoryState.INSTANCE.getLeagues()) {
			File file = new File(dirFile, league.getName()+ ".html");
			String html = toHtmlFile(league);
			PrintWriter prw;
			try {
				prw = new PrintWriter (file);
			      prw.println(html);          
			      prw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String toHtmlFile(League league) {
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		builder.append(readHeader());
		builder.append("<body>");
		builder.append("<div class=\"row\">"
				+ "<div class=\"column\">");
		builder.append(ranking(league).toString());
		builder.append("</div>");
		builder.append("<div class=\"column\" align=\"center\">");
		builder.append(matches(league).toString());
		builder.append("</div>");
		
		builder.append("</div></body></html>");
		return builder.toString();
	}

	private static StringBuilder ranking(League league) {
		StringBuilder builder = new StringBuilder();
		builder.append("<h2>Rating</h2>");
		builder.append("<table id=\"defaultTable\">");
		builder.append("<th>Placering</th><th>Navn</th><th>Rating</th><th>Sejre</th><th>Nederlag</th><th>Streak</th><th>Sidste gevinst/fradag</th>");
		for (int i = 0; i < league.getPlayersByRanking().size(); i++) {
			Player player = league.getPlayersByRanking().get(i);
			builder.append(HtmlRender.toHtml(player, i+1));
		}
		builder.append("</table>");
		return builder;
	}
	private static StringBuilder matches(League league) {
		StringBuilder builder = new StringBuilder();
		ArrayList<Match> m = new ArrayList<Match>(league.getMatches());
		Collections.reverse(m);
		builder.append("<h2>Seneste kampe</h2>");
		builder.append("<table>");
		builder.append("<th>Vinder</th><th>Taber</th><th>Gevinst</th><th>Fradrag</th>");
		for (Match match : m ) {
			builder.append(HtmlRender.toHtml(match));
		}
		builder.append("</table>");

		return builder;
	}
	
}
