package de.alpha.uhc.files;

import de.alpha.uhc.Listener.InGameListener;
import de.alpha.uhc.Listener.LobbyListener;
import de.alpha.uhc.Listener.PlayerJoinListener;
import de.alpha.uhc.commands.UHCCommand;
import de.alpha.uhc.kits.GUI;
import de.alpha.uhc.manager.BorderManager;
import de.alpha.uhc.manager.ScoreboardManager;
import de.alpha.uhc.teams.TeamSel;
import de.alpha.uhc.utils.Timer;
import net.minetopix.library.main.file.SimpleFile;

public class MessageFileManager {
	
	
	public static SimpleFile getMSGFile() {
        return new SimpleFile("plugins/UHC", "messages.yml");
    }
	
	public void addMessages() {
		SimpleFile file = getMSGFile();
		
		file.setDefault("Commands.Warns.OnlyPlayers", "&cThis Command have to be executed by a Player.");
		file.setDefault("Commands.Warns.NoPermissions", "&cYou don't have Permission to execute this Command.");
		file.setDefault("Commands.Admin.SpawnSet", "&aYou have sucessfully set the Spawn.");
		file.setDefault("Commands.Admin.LobbySet", "&aYou have sucessfully set the Lobby.");
		
		file.setDefault("Announcements.Countdown", "&aGame starts in &7[time]&8 seconds");
		file.setDefault("Announcements.NotEnoughPlayers", "�cCountdown reloaded! Not enough Players online.");
		file.setDefault("Announcements.Peaceperiod.timer", "�aThe damage is enabled in &7[time] &8seconds");
		file.setDefault("Announcements.Peaceperiod.end", "&cThe Damage is now on. Be Careful!");
		file.setDefault("Announcements.Death", "&6[Player]&c had died. [PlayerCount]");
		file.setDefault("Announcements.Leave", "&6[Player]&c had left. [PlayerCount]");
		file.setDefault("Announcements.Join", "&6[Player]&a has joined. [PlayerCount]");
		file.setDefault("Announcements.Win", "&6[Player]&a has won UHC.");
		file.setDefault("Announcements.Border.Move", "&c The Border has moved!");
		file.setDefault("Announcements.Restart", "&cThe Server is now restarting to load UHC again!");
		
		file.setDefault("Warns.FullServer", "&cYou're not allowed to join. The Server is full.");
		
		file.setDefault("Compass.NoPlayerInRange", "&cThere is no Player in your Range!");
		file.setDefault("Compass.PlayerInRange", "&6[Player] &ais �7[distance] blocks &aaway from you.");
		
		file.setDefault("Join.Title", "&aHello [Player]");
		file.setDefault("Join.Subtitle", "&7and welcome to UHC");
		
		file.setDefault("Scoreboard.Lobby.Title", "[Player]'s �astats");
		file.setDefault("Scoreboard.Lobby.Kills", "&aYour Kills:");
		file.setDefault("Scoreboard.Lobby.Deaths", "&cYour Deaths:");
		file.setDefault("Scoreboard.Lobby.Coins", "&6Your Coins:");
		
		file.setDefault("Scoreboard.Ingame.Title", "[Player]");
		file.setDefault("Scoreboard.Ingame.Player Living", "&aLiving Players:");
		file.setDefault("Scoreboard.Ingame.Spectators", "&cSpectators:");
		file.setDefault("Scoreboard.Ingame.Selected Kit", "&6Kit:");
		
		file.setDefault("Kits.GUI.Title", "&7[&6Kits&7]");
		file.setDefault("Kits.GUI.Selected", "&aYou selected &6[Kit]");
		file.setDefault("Kits.GUI.Bought", "&aYou bought &6[Kit] for &c[Coins] Coins");
		file.setDefault("Kits.GUI.No Coins", "&aYou need more Coins");
		
		file.setDefault("Teams.GUI.Title", "&7[&aTeams&7]");
		
		file.setDefault("Reward", "&aYou got [Coins] Coins.");
	}
	
	public void loadMessages() {
		SimpleFile file = getMSGFile();
		
		TeamSel.title = file.getColorString("Teams.GUI.Title");
		
		LobbyListener.sel = file.getColorString("Kits.GUI.Selected");
		LobbyListener.bought = file.getColorString("Kits.GUI.Bought");
		LobbyListener.coinsneed = file.getColorString("Kits.GUI.No Coins");
		
		UHCCommand.noplayer = file.getColorString("Commands.Warns.OnlyPlayers");
		 UHCCommand.noperms = file.getColorString("Commands.Warns.NoPermissions");
		 UHCCommand.spawnset = file.getColorString("Commands.Admin.SpawnSet");
		 UHCCommand.lobbyset = file.getColorString("Commands.Admin.LobbySet");
		 Timer.countmsg = file.getColorString("Announcements.Countdown");
		 Timer.nep = file.getColorString("Announcements.NotEnoughPlayers");
		 Timer.gracemsg = file.getColorString("Announcements.Peaceperiod.timer");
		 Timer.end = file.getColorString("Announcements.Peaceperiod.end");
		InGameListener.death = file.getColorString("Announcements.Death");
		InGameListener.quit = file.getColorString("Announcements.Leave");
		 InGameListener.win = file.getColorString("Announcements.Win");
		 InGameListener.kick = file.getColorString("Announcements.Restart");
		 InGameListener.ntrack = file.getColorString("Compass.NoPlayerInRange");
		 InGameListener.track = file.getColorString("Compass.PlayerInRange");
		 InGameListener.rew = file.getColorString("Reward");
		 PlayerJoinListener.join = file.getColorString("Announcements.Join");
		 PlayerJoinListener.full = file.getColorString("Warns.FullServer");
		 PlayerJoinListener.title = file.getColorString("Join.Title");
		 PlayerJoinListener.subtitle = file.getColorString("Join.Subtitle");
		 BorderManager.moved = file.getColorString("Announcements.Border.Move");
		 
		 ScoreboardManager.lobbytitle = file.getColorString("Scoreboard.Lobby.Title");
		 ScoreboardManager.lobbyKills = file.getColorString("Scoreboard.Lobby.Kills");
		 ScoreboardManager.lobbyDeaths = file.getColorString("Scoreboard.Lobby.Deaths");
		 ScoreboardManager.lobbyCoins = file.getColorString("Scoreboard.Lobby.Coins");
		 
		 ScoreboardManager.ingametitle = file.getColorString("Scoreboard.Ingame.Title");
		 ScoreboardManager.ingamePlayersLiving = file.getColorString("Scoreboard.Ingame.Player Living");
		 ScoreboardManager.ingameSpectators = file.getColorString("Scoreboard.Ingame.Spectators");
		 ScoreboardManager.ingameKit = file.getColorString("Scoreboard.Ingame.Selected Kit");
		 
		 GUI.title = file.getColorString("Kits.GUI.Title");
		 
	}

}
