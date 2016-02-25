package de.alpha.uhc.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import de.alpha.uhc.Core;
import de.alpha.uhc.utils.Stats;

public class ScoreboardManager {
	
	public static String lobbytitle;
	public static String lobbyKills;
	public static String lobbyDeaths;
	
	public static String ingametitle;
	public static String ingamePlayersLiving;
	public static String ingameSpectators;
	
	public static void setLobbyBoard(Player p) {
		Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective o = s.registerNewObjective("Lobby", "dummy");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		if(lobbytitle.contains("[Player]")) {
			String b = lobbytitle.replace("[Player]", p.getDisplayName());
			o.setDisplayName(b);
		}else {
			o.setDisplayName(lobbytitle);
		}

		{
			Score a = o.getScore(lobbyKills);
			a.setScore(new Stats(p).getKills());
		}
		
		{
			Score a = o.getScore(lobbyDeaths);
			a.setScore(new Stats(p).getDeaths());
		}
		
		
		p.setScoreboard(s);
		
	}
	
	public static void setInGameBoard(Player p) {
		Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective o = s.registerNewObjective("InGame", "dummy");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		if(ingametitle.contains("[Player]")) {
			String b = ingametitle.replace("[Player]", p.getDisplayName());
			o.setDisplayName(b);
		}else {
			o.setDisplayName(ingametitle);
		}
		
		{
			Score a = o.getScore(ingamePlayersLiving);
			a.setScore(Core.getInGamePlayers().size());
		}
		{
			Score a = o.getScore(ingameSpectators);
			a.setScore(Core.getSpecs().size());
		}
		
		
		p.setScoreboard(s);
		
	}
	
	

}
