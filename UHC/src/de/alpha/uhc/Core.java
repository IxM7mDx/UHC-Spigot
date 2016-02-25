package de.alpha.uhc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.alpha.border.Border;
import de.alpha.uhc.Listener.ChatListener;
import de.alpha.uhc.Listener.CraftListener;
import de.alpha.uhc.Listener.DeathListener;
import de.alpha.uhc.Listener.InGameListener;
import de.alpha.uhc.Listener.LobbyListener;
import de.alpha.uhc.Listener.MiningListener;
import de.alpha.uhc.Listener.PlayerJoinListener;
import de.alpha.uhc.Listener.SoupListener;
import de.alpha.uhc.commands.UHCCommand;
import de.alpha.uhc.files.MessageFileManager;
import de.alpha.uhc.files.OptionsFileManager;
import de.alpha.uhc.files.SpawnFileManager;
import de.alpha.uhc.utils.MapReset;
import de.alpha.uhc.utils.Spectator;
import de.alpha.uhc.utils.Timer;
import net.minetopix.mysqlapi.MySQLAPI;
import net.minetopix.mysqlapi.MySQLDataType;
import net.minetopix.mysqlapi.MySQLManager;

public class Core extends JavaPlugin {
	
	private static Core instance;
	public static String prefix;
	private OptionsFileManager ofm;
	private MessageFileManager mfm;
	private SpawnFileManager sfm;
	
	public static boolean isMySQLActive;
	
	
	private static ArrayList<Player> ig;
	private static ArrayList<Player> spectator;
	
	@Override
	public void onEnable() {
		instance = this;
		ofm = new OptionsFileManager();
		mfm = new MessageFileManager();
		sfm = new SpawnFileManager();
		ig = new ArrayList<Player>();
		spectator = new ArrayList<Player>();
		
		ofm.addOptions();
		ofm.loadOptions();
			
		mfm.addMessages();
		mfm.loadMessages();
			
		sfm.saveCfg();
		
		registerCommands();
		registerEvents();
		
		if(isMySQLActive == true) {
			MySQLAPI.initMySQLAPI(this);
			createTables();
		}
		
		if(Bukkit.getOnlinePlayers().size() != 0) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				all.setGameMode(GameMode.SURVIVAL);
			}
		}
		
		GState.setGameState(GState.LOBBY);
		new Border().border();
		new Timer().setCountdownTime();
		
		if(Timer.pc <= 1) {
			Bukkit.getConsoleSender().sendMessage(prefix + "�cUHC won't end until you reload or leave the Server. If it's only 1 Player.");
		}
		
		Bukkit.getConsoleSender().sendMessage(prefix + "�aUHC by AlphaHelix is now enabled!");
	}
	
	@Override
	public void onDisable() {
		
		if(isMySQLActive == true) {
			try {
				MySQLAPI.closeMySQLConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				Bukkit.getLogger().log(Level.WARNING, "The MySQL Connection wasn't closed.");
			}
		}
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.kickPlayer(prefix + InGameListener.kick);
		}
		MapReset.restore();
		Bukkit.getConsoleSender().sendMessage(prefix + "�cUHC by AlphaHelix is now disabled!");
	}
	
	private void registerCommands() {
		getCommand("uhc").setExecutor(new UHCCommand());
	}
	
	private void createTables() {
		
		MySQLManager.exCreateTableQry("UHC",
				MySQLManager.createColumn("Player", MySQLDataType.VARCHAR, 50),
				MySQLManager.createColumn("UUID", MySQLDataType.VARCHAR, 75),
				MySQLManager.createColumn("Kills", MySQLDataType.VARCHAR, 500),
				MySQLManager.createColumn("Deaths", MySQLDataType.VARCHAR, 500));
		
		
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new InGameListener(), this);
		Bukkit.getPluginManager().registerEvents(new MiningListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new LobbyListener(), this);
		Bukkit.getPluginManager().registerEvents(new CraftListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new SoupListener(), this);
		Bukkit.getPluginManager().registerEvents(new MapReset(), this);
		Bukkit.getPluginManager().registerEvents(new Spectator(), this);
	}
	
	public static ArrayList<Player> getInGamePlayers() {
		return ig;
	}
	
	public static void addInGamePlayer(Player p) {
		ig.add(p);
	}
	
	public static void removeInGamePlayer(Player p) {
		ig.remove(p);
	}
	
	public static ArrayList<Player> getSpecs() {
		return spectator;
	}
	
	public static void addSpec(Player p) {
		spectator.add(p);
	}
	
	public static void removeSpec(Player p) {
		spectator.remove(p);
	}
	
	public static Core getInstance() {
		return instance;
	}
	
	public static String getPrefix() {
		return prefix;
	}

}
