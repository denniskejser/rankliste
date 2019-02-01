package denniskejser.ranking.state.html;

import denniskejser.ranking.state.Match;
import denniskejser.ranking.state.Player;

public class HtmlRender {
	public static String toHtml(Match match) {
		StringBuilder b = new StringBuilder();
		b.append("<tr>");
		b.append("<td>" + match.getWinner().getName() + "</td>");
		b.append("<td>" + match.getLooser().getName() + "</td>");
		b.append("<td>" + match.getAward() + "</td>");
		b.append("<td>" + match.getPenalty() + "</td>");
		b.append("</tr>");
		return b.toString();
	}
	
	public static String toHtml(Player player, int placering) {
		StringBuilder b = new StringBuilder();
		b.append("<tr>");
		b.append("<td>" + placering + "</td>");
		b.append("<td>" + player.getName() + "</td>");
		b.append("<td>" + player.getRating() + "</td>");
		b.append("<td>" + player.getWinCount() + "</td>");
		b.append("<td>" + player.getLossCount() + "</td>");
		b.append("<td>" + player.getTrend() + "</td>");
		b.append("<td>" + player.getLastResult() + "</td>");
		b.append("</tr>");
		return b.toString();
	}
	
}
